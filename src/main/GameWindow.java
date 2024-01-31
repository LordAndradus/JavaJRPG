package main;

import engine.input.InputHandler;
import engine.states.LoadingGameState;
import engine.states.State;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class GameWindow extends JPanel implements Runnable
{
    //Game Thread
    Thread gameThread;

    /**
     * Screen Settings
     * Supported resolutions
     *    0 x    0 <- NOT SUPPORTED, this is to make it easier for resizing. TODO: Remove when added to gui
     *  640 x  360 <-  25.00% (0.2500)
     *  854 x  480 <-  44.51% (0.4451)
     * 1280 x  720 <- Target  (1.0000)
     * 1366 x  768 <- 113.89% (1.1389)
     * 1600 x  900 <- 156.25% (1.5625)
     * 1920 x 1080 <- 225.00% (2.2500)
     * 2560 x 1440 <- 400.00% (4.0000)
     * 3840 x 2160 <- 900.00% (9.0000)
     */
    //Temporary
    int[][] supportedResolution = {{640, 360}, {854, 480}, {1280, 720}, {1366, 768}, {1600, 900}, {1920, 1080}, {2560, 1440}, {3840, 2160}, {0, 0}};
    public int screenWidth = 1920, screenHeight = 1080;
    public int originalWidth = 1280, originalHeight = 720;
    public double resolutionScale;
    public double targetFPS = 144;
    public double scale = 0;

    //State machine
    Stack<State> sm;

    //Input
    InputHandler ih = new InputHandler();

    public GameWindow()
    {
        resolutionScale = ((double) screenWidth / (double) originalWidth);
        System.err.println(String.format("Window has a scale of %.2f", resolutionScale));

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.WHITE);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);

        //Inputs
        this.addMouseListener(ih);
        this.addMouseWheelListener(ih);
        this.addKeyListener(ih);

        //Loads images really slowly if this is checked
        ImageIO.setUseCache(false);
    }

    public void setup()
    {
        sm = new Stack<>();
        sm.push(new LoadingGameState());
    }

    public void startGame()
    {
        setup();
        gameThread = new Thread(this);
        gameThread.start();

        Main.println(gameThread.getState());
    }

    //Using a variable refresh rate method
    @Override
    public void run()
    {
        boolean runLoop = true;
        long currentTime;
        long previousTime = System.nanoTime();
        double targetFPS = (1000000000.00 / this.targetFPS);
        double deltaTime;
        double deltaTotal = 0.00;

        while(runLoop)
        {
            if(exitThread())
            {
                System.err.println("Exiting the game");
                runLoop = false;
                continue;
            }

            currentTime = System.nanoTime();
            deltaTime = (currentTime - previousTime) / targetFPS;
            deltaTotal += deltaTime;

            if(deltaTotal >= 1.00 / targetFPS)
            {
                deltaTotal -= 1.00 / targetFPS;

                update();
                repaint();
            }

            previousTime = currentTime;
        }

        Main.closeGame();
    }

    public void update()
    {
        ih.updateMousePosition(this);

        try
        {
            if(!sm.isEmpty()) sm.peek().update(ih);
        }
        catch(Exception e)
        {
            System.err.println("There was a problem updating the current state::" + sm.peek().toString());
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        resetCanvas(g2);

        try
        {
            if(!sm.isEmpty()) sm.peek().render(g2);
        }
        catch(Exception e)
        {
            System.err.println("There was a problem rendering the state::" + sm.peek().toString());
            e.printStackTrace();
        }
    }

    public void resetCanvas(Graphics2D g2)
    {
        g2.setColor(this.getBackground());
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public void pushState(State s)
    {
        sm.push(s);
    }

    public void popState()
    {
        sm.pop();
    }

    public void clearStateMachine()
    {
        sm = new Stack<State>();
    }

    public boolean exitThread()
    {
        return sm.isEmpty();
    }

    public boolean listenForClose()
    {
        return gameThread.getState() == Thread.State.RUNNABLE;
    }

    public double scale()
    {
        return resolutionScale;
    }

    public void getNextResolution()
    {
        int i = 0;
        for(i = 0; i < supportedResolution.length; i++)
        {
            if(supportedResolution[i][0] == screenWidth) break;
        }

        i++;

        Main.println("Index: " + i + " | " + supportedResolution.length);
        if(i == supportedResolution.length - 1) i = 0;

        screenWidth = supportedResolution[i][0];
        screenHeight = supportedResolution[i][1];

        Main.resizeWindow(screenWidth, screenHeight);

        resolutionScale = ((double) screenWidth / (double) originalWidth);

        System.err.println("New scaling: " + String.format("%.2f", resolutionScale));

        changeScale();
    }

    public void changeScale()
    {
        sm.peek().changeScale();
    }
}

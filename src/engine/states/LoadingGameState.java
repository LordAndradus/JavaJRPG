package engine.states;

import engine.input.InputHandler;
import main.Main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LoadingGameState extends State
{
    {
        stateDescriptor = "Loads the game resources to a cache.";
    }

    loadingThread load;

    ArrayList<BufferedImage> loadAnimation;

    public LoadingGameState()
    {
        super();

        String filepath = "/LoadingScreen/frame";
        loadAnimation = new ArrayList<>(60);

        for(int i = 0; i < 60; i++)
        {
            try
            {
                loadAnimation.add(ImageIO.read(getClass().getResourceAsStream(filepath + String.format("%02d.png", i))));
            }
            catch(Exception e)
            {
                System.err.println("Resources does not exist: " + filepath + String.format("%02d.png", i));
                System.exit(0);
            }
        }

        load = new loadingThread();

        System.err.println("Loading Game.");
    }

    @Override
    public void update(InputHandler ih)
    {
        if(load.loadedFlag)
        {
            //Pop this state and add the Main Menu
            System.err.println("Loading finished. Starting game.");
            Main.gw.clearStateMachine();
            Main.gw.pushState(new MainMenuState());
        }
    }

    int frameCounter = 0;
    @Override
    public void render(Graphics2D g2)
    {
        g2.drawImage(loadAnimation.get(frameCounter), 0, 0, null);

        frameCounter++;
        if(frameCounter >= 60) frameCounter = 0;
    }

    @Override
    public void stateUpdate(InputHandler ih)
    {

    }

    @Override
    public void buttonFunction()
    {

    }

    @Override
    public void stateRender(Graphics2D g2)
    {

    }

    class loadingThread implements Runnable
    {
        Thread load;

        boolean loadedFlag = false;

        public loadingThread()
        {
            load = new Thread(this);
            load.start();
        }

        @Override
        public void run()
        {
            //Load stuff

            loadedFlag = true;
        }
    }
}

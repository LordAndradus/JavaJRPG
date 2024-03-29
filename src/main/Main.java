package main;

import debug.ParseCommandLine;
import engine.input.InputHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main
{
    private static boolean debugMode = true;
    private static JFrame window;
    public static GameWindow gw;

    public static void main(String args[])
    {
        System.setProperty("sun.java2d.uiScale", "1");
        System.setProperty("sun.java2d.opengl", "true");

        if(debugMode)
        {
            //Load arguments
            if(args.length >= 2)
            {
                for(int i = 0; i < args.length; i++) println("Passed command: " + args[i]);

                for(int i = 0; i < args.length; i++)
                {
                    ParseCommandLine.parseCommand(args[i]);
                }
            }
        }

        window = new JFrame();
        gw = new GameWindow();
        packWindow();

        System.err.println("Size of Frame: [w=" + window.getSize().width + ", h=" + window.getSize().height + "]");
        System.err.println("Size of Frame: [w=" + gw.getSize().width + ", h=" + gw.getSize().height + "]");

        gw.startGame();

        window.addWindowListener(new WindowAdapter()
        {
           public void windowClosing(WindowEvent e)
           {
               //Save Close
               closeGame();
           }
        });
    }

    private static void packWindow()
    {
        window.getContentPane().removeAll();
        window.setResizable(false);
        window.setTitle("Simple 2D JRPG - Name subject to change");

        gw.setDoubleBuffered(true);
        window.add(gw);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void closeGame()
    {
        System.err.println("Game has ended.");
        System.exit(0);
    }

    public static void resizeWindow(int width, int height)
    {
        gw.setSize(new Dimension(width, height));
        window.setSize(new Dimension(width + 32, height + 88));

        System.err.println("New size : [w=" + width + ", h=" + height + "]");
    }

    public static InputHandler getInputListener()
    {
        return gw.ih;
    }

    public static void println(Object x)
    {
        System.out.println(x);
    }
}

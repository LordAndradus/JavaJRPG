package engine.states;

import engine.GUI.Button.MenuButton;
import engine.input.InputHandler;

import java.awt.*;

public class MainMenuState extends State
{
    {
        stateDescriptor = "The main menu of the game.";
    }

    public MainMenuState()
    {
        super();

        layers.add(ux);

        ux.addButton("Start", new MenuButton("Start", 100, 100, 300, 100));
        ux.addButton("Quit", new MenuButton("Quit", 100, 300, 300, 100));
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
}

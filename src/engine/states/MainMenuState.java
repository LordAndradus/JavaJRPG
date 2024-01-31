package engine.states;

import engine.GUI.Button.MenuButton;
import engine.input.InputHandler;
import main.Main;

import java.awt.*;

public class MainMenuState extends State
{
    {
        stateDescriptor = "The main menu of the game.";
    }

    public MainMenuState()
    {
        super();

        //TODO: Adding itself will recursively add itself. Figure out how to avoid this while keeping the idea of layers.
        layers.add(ux);

        ux.addButton("Start", new MenuButton("Start", 100, 100, 300, 100));
        ux.addButton("Quit", new MenuButton("Quit", 100, 300, 300, 100));
        ux.addButton("Resolution", new MenuButton("Resolution", 900, 50, 300, 100));
    }

    @Override
    public void stateUpdate(InputHandler ih)
    {

    }

    @Override
    public void buttonFunction()
    {
        if(getButton("Start").leftClicked()) Main.gw.pushState(new LoadingGameState());
        if(getButton("Quit").anyClicked()) Main.gw.clearStateMachine();
        if(getButton("Resolution").leftClicked()) Main.gw.getNextResolution();
    }

    @Override
    public void stateRender(Graphics2D g2)
    {
        g2.setColor(Color.BLACK);
        g2.drawString(Main.getInputListener().mousePosToString(), Main.getInputListener().getMousePos().x, Main.getInputListener().getMousePos().y);
    }
}

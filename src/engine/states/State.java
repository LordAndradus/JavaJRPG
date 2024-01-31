package engine.states;

import engine.GUI.Button.Button;
import engine.GUI.GUI;
import engine.input.InputHandler;
import main.Drawable;
import main.Main;

import java.awt.*;
import java.util.ArrayList;

public abstract class State extends Drawable
{
    //State stuff
    String stateName = "";
    String stateDescriptor = "";

    ArrayList<Drawable> layers;
    GUI ux;

    public State()
    {
        stateName = this.getClass().getSimpleName();

        layers = new ArrayList<>();
        ux = new GUI();

        //DebugMessenger.message("Start of State: " + stateName + "\n" + stateDescriptor;
        Main.println("Start of state: " + stateName);
    }

    public void update(InputHandler ih)
    {
        for(int i = 0; i < layers.size(); i++) layers.get(i).update(ih);

        buttonFunction();
    }
    public void render(Graphics2D g2)
    {
        for(int i = 0; i < layers.size(); i++) layers.get(i).render(g2);
    }

    public Button getButton(String key)
    {
        return ux.getButtons().get(key);
    }

    public abstract void stateUpdate(InputHandler ih);
    public abstract void buttonFunction();
    public abstract void stateRender(Graphics2D g2);

    public String toString()
    {
        return stateName + " - " + stateDescriptor;
    }
}

package engine.GUI;

import engine.GUI.Button.Button;
import engine.GUI.Slider.Slider;
import engine.input.InputHandler;
import main.Drawable;

import java.awt.*;
import java.util.HashMap;

public class GUI extends Drawable
{
    HashMap<String, Button> buttons;
    HashMap<String, Slider> sliders;
    HashMap<String, TextField> textFields;

    public GUI()
    {
        buttons = new HashMap<>();
        sliders = new HashMap<>();
        textFields = new HashMap<>();
    }

    public HashMap<String, Button> getButtons()
    {
        return buttons;
    }

    public HashMap<String, Slider> getSliders()
    {
        return sliders;
    }

    public HashMap<String, TextField> getTextFields()
    {
        return textFields;
    }
    public void addButton(String key, Button b)
    {
        buttons.put(key, b);
    }

    @Override
    public void update(InputHandler ih)
    {
        for(String key : buttons.keySet()) buttons.get(key).update(ih);
        //for(String key : sliders.keySet()) sliders.get(key).update(ih);
        //for(String key : textFields.keySet()) textFields.get(key).update(ih);
    }

    @Override
    public void render(Graphics2D g2)
    {
        for(String key : buttons.keySet()) buttons.get(key).render(g2);
        //for(String key : sliders.keySet()) sliders.get(key).render(g2);
        //for(String key : textFields.keySet()) textFields.get(key).render(g2);
    }
}

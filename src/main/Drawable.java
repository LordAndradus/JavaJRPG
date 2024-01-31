package main;

import engine.input.InputHandler;

import java.awt.*;

public abstract class Drawable
{
    public abstract void update(InputHandler ih);
    public abstract void render(Graphics2D g2);
}

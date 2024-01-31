package engine.GUI.Button;

import engine.input.InputHandler;
import main.Main;

import java.awt.*;

public abstract class Button
{
    Color transparent = new Color(0, 0, 0, 0);
    //Color buttonIdle = transparent;
    Color buttonIdle = Color.black;
    Color buttonHover = transparent;
    Color buttonActive = transparent;

    Color textBorder = Color.BLACK;
    Color textIdle = new Color(70, 70, 70);
    Color textHover = new Color(200, 200, 200);
    Color textActive = new Color(20, 20, 20);

    String text = "Test";
    int textX = 0;
    int textY = 0;
    String name = "";

    Rectangle body;
    Rectangle border;

    //State machine
    state status = state.idle;
    public enum state
    {
        idle,
        hover,
        activeLeft,
        activeRight,
        activeMiddle
    }

    //Click side
    click mouseClick = click.none;
    public enum click
    {
        left,
        right,
        middle,
        none
    }

    public Button(String text, int x, int y, int width, int height)
    {
        x = (int) (x * Main.gw.scale());
        y = (int) (y * Main.gw.scale());
        width = (int) (width * Main.gw.scale());
        height = (int) (height * Main.gw.scale());

        this.text = text;

        System.err.println("Adjusted. [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]");

        border = new Rectangle(x - 2, y - 2, width + 4, height + 4);
        body = new Rectangle(x, y, width, height);

        textX = x + 50;
        textY = y + 50;
    }

    public void resize()
    {
        System.out.println("Screen width = " + Main.gw.getSize().width);
        System.out.println("X=" + border.x + " | " + (int) (border.x * Main.gw.scale()));


        border.x = (int) (border.x * Main.gw.scale());
        border.y = (int) (border.y * Main.gw.scale());
        border.width = (int) (border.width * Main.gw.scale());
        border.height = (int) (border.height * Main.gw.scale());

        body.x = (int) (body.x * Main.gw.scale());
        body.y = (int) (body.y * Main.gw.scale());
        body.width = (int) (body.width * Main.gw.scale());
        body.height = (int) (body.height * Main.gw.scale());
    }

    //Default functionality
    int count = 0;
    public void update(InputHandler ih)
    {
        if(mouseClick != click.none) mouseClick = click.none;

        updateState(ih);
    }

    public boolean leftClicked()
    {
        return mouseClick == click.left;
    }

    public boolean rightClicked()
    {
        return mouseClick == click.right;
    }

    public boolean middleClicked()
    {
        return mouseClick == click.middle;
    }

    public boolean eitherClicked()
    {
        return mouseClick == click.left || mouseClick == click.right;
    }

    public boolean anyClicked()
    {
        return mouseClick != click.none;
    }

    public void updateState(InputHandler ih)
    {
        //If active, then keep that status until not being clicked anymore, or if the mouse moves away
        if(body.contains(ih.getMousePos()))
        {
            if(status == state.activeLeft)
            {
                if(ih.mouseLeftClick) return;
                else mouseClick = click.left;
            }

            if(status == state.activeRight)
            {
                if(ih.mouseRightClick) return;
                else mouseClick = click.right;
            }

            if(status == state.activeMiddle)
            {
                if(ih.mouseMiddleClick) return;
                else mouseClick = click.right;
            }
        }

        status = state.idle;
        if(body.contains(ih.getMousePos()))
        {
            status = state.hover;

            if(ih.mouseLeftClick) status = state.activeLeft;
            if(ih.mouseRightClick) status = state.activeRight;
            if(ih.mouseMiddleClick) status = state.activeMiddle;
        }
    }

    public void render(Graphics2D g2)
    {
        switch(status)
        {
            case state.idle:
                g2.setColor(buttonIdle);
                drawRect(g2, border);
                g2.setColor(textIdle);
                break;
            case state.hover:
                g2.setColor(buttonHover);
                drawRect(g2, border);
                g2.setColor(textHover);
                break;
            case state.activeLeft:
                g2.setColor(buttonActive);
                drawRect(g2, border);
                g2.setColor(textActive);
                break;
            case state.activeRight:
                g2.setColor(buttonActive);
                drawRect(g2, border);
                g2.setColor(textActive);
                break;
        }

        g2.drawString(text, textX, textY);
    }

    private void drawRect(Graphics2D g2, Rectangle r)
    {
        g2.drawRect(r.x, r.y, r.width, r.height);
    }
}

package engine.GUI.Button;

import engine.input.InputHandler;
import main.Main;

import java.awt.*;

public abstract class Button
{
    Color transparent = new Color(0, 0, 0, 0);
    //Color buttonIdle = transparent;
    Color buttonIdle = transparent;
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
        activeRight
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
        x = (int) (x * Main.gw.resolutionScale);
        y = (int) (y * Main.gw.resolutionScale);
        this.text = text;

        System.err.println("Adjusted. [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + "]");

        border = new Rectangle(x - 2, y - 2, width + 4, height + 4);
        body = new Rectangle(x, y, width, height);

        textX = x + 50;
        textY = y + 50;
    }

    //Default functionality
    int count = 0;
    public void update(InputHandler ih)
    {
        count++;
        if(count >= 50000)
        {
            count -= 50000;
            Main.println(status);
        }

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

    public void updateState(InputHandler ih)
    {
        //If active, then keep that status until not being clicked anymore, or if the mouse moves away
        if(body.contains(ih.mousePosition))
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
        }

        status = state.idle;
        if(body.contains(ih.mousePosition))
        {
            status = state.hover;

            if(ih.mouseLeftClick) status = state.activeLeft;
            if(ih.mouseRightClick) status = state.activeRight;
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

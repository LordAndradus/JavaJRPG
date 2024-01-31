package engine.input;

import main.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputHandler implements MouseWheelListener, MouseListener, KeyListener
{
    //Supported keys

    //Mouse inputs
    public Point mousePosition;
    public boolean mouseLeftClick, mouseRightClick;

    MouseClick current = MouseClick.idle;
    public enum MouseClick
    {
        idle,
        leftClick,
        rightClick,
        debounceLeft,
        debounceRight
    }

    public InputHandler()
    {
        mousePosition = new Point();
    }

    public void updateMousePosition()
    {
        mousePosition.x = MouseInfo.getPointerInfo().getLocation().x;
        mousePosition.y = MouseInfo.getPointerInfo().getLocation().y;
    }

    public void updateMousePosition(GameWindow gw)
    {
        mousePosition.x = MouseInfo.getPointerInfo().getLocation().x - gw.getLocationOnScreen().x;
        mousePosition.y = MouseInfo.getPointerInfo().getLocation().y - gw.getLocationOnScreen().y;
    }

    public int getMouseX()
    {
        return mousePosition.x;
    }

    public int getMouseY()
    {
        return mousePosition.y;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(SwingUtilities.isLeftMouseButton(e))
        {
            mouseLeftClick = false;
        }

        if(SwingUtilities.isRightMouseButton(e))
        {
            mouseRightClick = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if(SwingUtilities.isLeftMouseButton(e))
        {
            mouseLeftClick = true;
        }

        if(SwingUtilities.isRightMouseButton(e))
        {
            mouseRightClick = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if(SwingUtilities.isLeftMouseButton(e))
        {
            mouseLeftClick = false;
        }

        if(SwingUtilities.isRightMouseButton(e))
        {
            mouseRightClick = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {

    }
}

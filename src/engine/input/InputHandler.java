package engine.input;

import main.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputHandler implements MouseWheelListener, MouseListener, KeyListener
{
    //Supported keys

    //Mouse inputs
    private Point mousePosition;
    public boolean mouseLeftClick, mouseRightClick, mouseMiddleClick;

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

    public Point getMousePos()
    {
        return mousePosition;
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

    public String mousePosToString()
    {
        return "(" + mousePosition.x + ", " + mousePosition.y + ")";
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

    /**
     * Booleans for simultaneous checks (IE Check if Left & Right)
     * TODO: An enumerator for strict checks (IE ONLY left click, etc)
     */
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

        if(SwingUtilities.isMiddleMouseButton(e))
        {
            mouseMiddleClick = false;
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

        if(SwingUtilities.isMiddleMouseButton(e))
        {
            mouseMiddleClick = true;
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

        if(SwingUtilities.isMiddleMouseButton(e))
        {
            mouseMiddleClick = false;
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

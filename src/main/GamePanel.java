package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private int dx = 100, dy = 100;
    
    public GamePanel(){

        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    public void changedx(int val){
        this.dx += val;
        repaint();
    }

    public void changedy(int val){
        this.dy += val;
        repaint();
    }

    public void setRectPos(int x, int y){
        this.dx = x;
        this.dy = y;
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.fillRect(dx, dy, 200, 50);
    }

}

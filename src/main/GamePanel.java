package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float dx = 100, dy = 100;
    private float xdir = 0.6f, ydir = 0.6f;
    private Color color = new Color(150, 20, 90);
    private Random random = new Random();
    
    public GamePanel(){

        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    public void changedx(int val){
        this.dx += val;
    }

    public void changedy(int val){
        this.dy += val;
    }

    public void setRectPos(int x, int y){
        this.dx = x;
        this.dy = y;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        updateRect();

        g.setColor(color);
        g.fillRect((int)dx, (int)dy, 200, 50);
    }

    private void updateRect(){
        dx += xdir;
        if(dx > 400 || dx < 0){
            xdir *= -1;
            color = getRDMColor();
        }
        dy += ydir;
        if(dy > 400 || dy < 0){
            ydir *= -1;
            color = getRDMColor();
        }

    }

    private Color getRDMColor(){
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        return new Color(r, g, b);
    }
}

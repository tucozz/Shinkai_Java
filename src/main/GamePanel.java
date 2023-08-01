package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float dx = 100, dy = 100;
    private BufferedImage img, subImg;
    
    public GamePanel(){

        importImg();

        mouseInputs = new MouseInputs(this);
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void importImg(){
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280,800);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
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

        subImg = img.getSubimage(1*64, 8*40, 64, 40);
        g.drawImage(subImg, (int)dx, (int)dy, 128, 80, null);

    }


}

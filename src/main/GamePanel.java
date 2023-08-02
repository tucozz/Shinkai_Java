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
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float dx = 100, dy = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int player_action = IDLE;
    private int player_direction = -1;
    private boolean moving = false;
    
    public GamePanel(){

        importImg();
        LoadAnimations();

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
        } finally {
            try {
                is.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void LoadAnimations(){
        animations = new BufferedImage[9][6];

        for(int i = 0; i < animations.length; i++)
            for(int j = 0; j < animations[i].length; j++)
                animations[i][j] = img.getSubimage(j*64, i*40, 64, 40);

    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280,800);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
    }

    public void setDirection(int direction){
        this.player_direction = direction;
        moving = true;
    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }

    public void updateAnimationsTick(Graphics g){
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
           if(aniIndex >= GetSpriteAmount(player_action)){
                aniIndex = 0;
            }
        }
    }

    public void paintComponent(Graphics g){
        requestFocus(true);

        super.paintComponent(g);

        updateAnimationsTick(g);

        setAnimation();
        updatePos();

        g.drawImage(animations[player_action][aniIndex], (int)dx, (int)dy, 256, 160, null);

    }

    private void updatePos() {

        if(moving){
            switch(player_direction){
                case LEFT:
                    dx -= 5;
                    break;
                case UP:
                    dy -= 5;
                    break;
                case RIGHT:
                    dx += 5;
                    break;
                case DOWN:
                    dy += 5;
                    break;
            }
        }

    }

    private void setAnimation() {

        if(moving)
            player_action = RUNNING;
        else
            player_action = IDLE;

    }


}

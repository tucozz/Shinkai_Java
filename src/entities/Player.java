package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 30;
    private int player_action = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private float playerSpeed = 2.0f; 

    public Player(float x, float y) {
        super(x, y);
        LoadAnimations();
        //TODO Auto-generated constructor stub
    }

    public void update(){
        updateAnimationsTick();
        setAnimation();
        updatePos();
    }

    public void render(Graphics g){
        g.drawImage(animations[player_action][aniIndex], (int)x, (int)y, 256, 160, null);
    }

    public void updateAnimationsTick(){
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
           if(aniIndex >= GetSpriteAmount(player_action)){
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    private void setAnimation() {

        int startAni = player_action;

        if(moving)
            player_action = RUNNING;
        else
            player_action = IDLE;

        if(attacking)
            player_action = ATTACK_1;

        if(startAni != player_action)
            resetAniTick();

    }

    private void resetAniTick(){
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {

        moving = false;

        if(left && !right){
            x -= playerSpeed;
            moving = true;
        }else if(right && !left){
            x += playerSpeed;
            moving = true;
        }

        if(up && !down){
            y -= playerSpeed;
            moving = true;
        } else if(down && !up){
            y += playerSpeed;
            moving = true;
        }

    }

    private void LoadAnimations(){
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");

        try {
            BufferedImage img = ImageIO.read(is);

            animations = new BufferedImage[9][6];
            for(int i = 0; i < animations.length; i++)
                for(int j = 0; j < animations[i].length; j++)
                    animations[i][j] = img.getSubimage(j*64, i*40, 64, 40);

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

    public void resetDirBooleans(){
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setAttack(boolean attacking){
        this.attacking = attacking;
    }

    public boolean isLeft(){
        return left;
    }

    public void setLeft(boolean left){
        this.left = left;
    }

    public boolean isRight(){
        return right;
    }

    public void setRight(boolean right){
        this.right = right;
    }

    public boolean isUp(){
        return up;
    }

    public void setUp(boolean up){
        this.up = up;
    }

    public boolean isDown(){
        return down;
    }

    public void setDown(boolean down){
        this.down = down;
    }
    
}

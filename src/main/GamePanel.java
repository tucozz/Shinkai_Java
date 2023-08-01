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
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int player_action = IDLE;

    public static final int IDLE = 0;
    public static final int RUNNING = 1;
    public static final int JUMP = 2;
    public static final int FALLING = 3;
    public static final int GROUND = 4;
    public static final int HIT = 5;
    public static final int ATTACK_1 = 6;
    public static final int ATTACK_JUMP_1 = 7;
    public static final int ATTACK_JUMP_2 = 8;
    
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

    public void setMoving(int direction){
        
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

    public static int GetSpriteAmount(int player_action){
        switch (player_action) {
            case RUNNING:
                return 0;
            case IDLE:
                return 5;
            case HIT:
                return 4;
            case JUMP:
            case ATTACK_1:
            case ATTACK_JUMP_1:
            case ATTACK_JUMP_2:
                return 3;
            case GROUND:
                return 2;
            case FALLING:
            default:
                return 1;
         }
    }

    public void paintComponent(Graphics g){
        requestFocus(true);

        super.paintComponent(g);

        updateAnimationsTick(g);

        g.drawImage(animations[player_action][aniIndex], (int)dx, (int)dy, 128, 80, null);

    }


}

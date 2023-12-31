package main;

import java.awt.Graphics;

import entities.Player;

public class Game implements Runnable{
    
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Player player;

    public Game(){
        initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void initClasses(){
        player = new Player(200, 200);
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        player.update();
    }

    public void render(Graphics g){
        player.render(g);
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        
        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long LastCheck = System.currentTimeMillis();

        double du = 0;
        double df = 0;

        while(true){
            long currentTime = System.nanoTime();

            du += (currentTime - previousTime) / timePerUpdate;
            df += (currentTime - previousTime) / timePerFrame;

            previousTime = currentTime;

            if(du >= 1){
                update();
                updates++;
                du--;
            }

            if(df >= 1){
                gamePanel.repaint();
                frames++;
                df--;
            }

            if(System.currentTimeMillis() - LastCheck >= 1000){
                LastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
            }
        }

    }

    public void windowFocusLost(){
        player.resetDirBooleans();
    }

    public Player getPlayer(){
        return player;
    }

}

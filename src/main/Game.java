package main;

public class Game implements Runnable{
    
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;

    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        long LastFrame = System.nanoTime();
        long now = System.nanoTime();

        int frames = 0;
        long LastCheck = System.currentTimeMillis();

        while(true){

            now = System.nanoTime();
            if(now - LastFrame >= timePerFrame){
                gamePanel.repaint();
                LastFrame = now;
                frames++;
            }

            if(System.currentTimeMillis() - LastCheck >= 1000){
                LastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

    }

}

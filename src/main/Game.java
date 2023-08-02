package main;

public class Game implements Runnable{
    
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

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

    public void update(){
        gamePanel.updateGame();
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

}

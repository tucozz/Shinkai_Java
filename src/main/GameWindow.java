package main;

import javax.swing.JFrame;

public class GameWindow extends JFrame {
    
    public GameWindow(GamePanel gamePanel){

        JFrame jframe = new JFrame();

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setVisible(true);

    }
}

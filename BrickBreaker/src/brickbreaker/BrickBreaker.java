package brickbreaker;

import java.io.IOException;

import javax.swing.JFrame;


public class BrickBreaker {

static int WIDTH=1200;
static int HEIGHT=800;
    public static void main(String[] args) throws IOException {
       JFrame frame = new JFrame ("Brick Breaker");
       frame.setSize(WIDTH, HEIGHT);
       frame.setLocationRelativeTo(null);
       frame.add(new Settings());
       frame.setVisible(true);
       frame.setResizable(false);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
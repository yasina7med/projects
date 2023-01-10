package flappyDoge;

import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JPanel;

public class Render extends JPanel {


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            flappyDoge.flappydoge.repaint(g);
        } catch (IOException e) {
           
            e.printStackTrace();
        }
    }
}
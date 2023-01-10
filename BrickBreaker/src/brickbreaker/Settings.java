package brickbreaker;

import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Rectangle;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

public class Settings extends JPanel implements ActionListener, MouseInputListener {
    private int ballx;
    private int bally;
    private int controlx;
    private int controly;
    private Bricks bricks;
    private Timer timer;
    private int speedX, speedY;
    private boolean game;
    private int[] values;
    private Image Lava;
    private int row, col;
    public int level;
    public boolean loss;

    private void init() {
        ballx = BrickBreaker.WIDTH / 2 - 15;
        bally = BrickBreaker.HEIGHT / 2 + 220;
        controlx = BrickBreaker.WIDTH / 2 - 50;
        controly = BrickBreaker.HEIGHT / 2 + 250;
        bricks = new Bricks(row++, col++);
    }

    public Settings() {

        row = 7;
        col = 7;
        ballx = BrickBreaker.WIDTH / 2 - 15;
        bally = BrickBreaker.HEIGHT / 2 + 220;
        controlx = BrickBreaker.WIDTH / 2;
        controly = BrickBreaker.HEIGHT / 2 + 250;
        bricks = new Bricks(row, col);

        level = 1;
        speedX = 6;
        speedY = 6;

        timer = new Timer(1000 / 64, this);
        timer.start();

        try {
            Lava = ImageIO.read(new File(".\\BrickBreaker\\src\\imgs\\lavaball.png"));
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, BrickBreaker.WIDTH, BrickBreaker.HEIGHT);

        bricks.draw((Graphics2D) g);

        g.setColor(Color.WHITE);
        g.fillRect(controlx, controly, 140, 30);
        g.setColor(Color.black);
        g.drawRect(controlx, controly, 140, 30);

        addMouseMotionListener(this);
        addMouseListener(this);

        // g.setColor(Color.red);
        // g.fillOval(ballx, bally, 30, 30);
        // g.setColor(Color.BLACK);
        // g.drawOval(ballx, bally, 30, 30);
        g.drawImage(Lava, ballx, bally, 30, 30, null);

        Font f = new Font("Arial", Font.BOLD, 50);
        g.setColor(Color.CYAN);
        g.setFont(f);

        if (bally >= BrickBreaker.HEIGHT) {

            g.drawString("كسربك خربله خرب الله خرب محمد خرب الله " + level, BrickBreaker.WIDTH / 2 - 300,
                    BrickBreaker.HEIGHT / 2);
            game = false;
            loss = true;
            row = 7;
            col = 7;
            level = 1;
            speedY = 6;
        }
        if (game && !loss) {
            g.drawString("level: " + level, 40, 40);
        }
        if (!game) {
            g.drawString("دوس دكمة ماوس", BrickBreaker.WIDTH / 2 - 150, BrickBreaker.HEIGHT / 2 + 100);
        }
        if (bricks.isFinish()) {
            g.drawString("!عفية", BrickBreaker.WIDTH / 2 - 90, BrickBreaker.HEIGHT / 2);
            game = false;
            level++;

        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (game) {
            if (ballx <= 0 || ballx >= BrickBreaker.WIDTH - 50) {
                speedX *= -1;
            }
            if (bally <= 0) {
                speedY *= -1;
            }
            // left control
            if (new Rectangle(ballx, bally, 30, 30).intersects(new Rectangle(controlx, controly, 70, 30))) {
                speedY *= -1;
                if (speedX > 0) {
                    speedX *= -1;
                    bally -= 5;
                }
            }
            // right
            if (new Rectangle(ballx, bally, 30, 30).intersects(new Rectangle(controlx + 70, controly, 70, 30))) {
                speedY *= -1;
                if (speedX < 0) {
                    speedX *= -1;
                    bally -= 5;
                }
            }
            values = bricks.collision(ballx, bally, speedX, speedY);
            speedX = values[0];
            speedY = values[1];

            ballx += speedX;
            bally -= speedY;
            repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!game) {
            game = true;
            loss = false;
            init();

        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        controlx = e.getX();
        // controly = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
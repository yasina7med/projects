package flappyDoge;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class flappyDoge implements ActionListener, MouseListener, KeyListener, ImageObserver {

    public static flappyDoge flappydoge;

    public final int WIDTH = 1200, HEIGHT = 800;

    public Render render;

    public Rectangle bird;

    public int ticks, yMotion, score;
    public Image doge;
    public Image pipes;
    public Random rand;

    public ArrayList<Rectangle> columns;
    // public ArrayList<Rectangle> columns;
    public boolean gameOver, started;

    public flappyDoge() {
        JFrame jFrame = new JFrame();
        Timer timer = new Timer(20, this);

        render = new Render();
        rand = new Random();

        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.add(render);
        jFrame.addMouseListener(this);
        jFrame.addKeyListener(this);
        jFrame.setTitle("Flappy bird");

        bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 30, 30);
        columns = new ArrayList<Rectangle>();
        
        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);

        timer.start();
    }

    public void addColumn(boolean start) {
        int space = 300;
        int width = 100;
        int height = 50 + rand.nextInt(300);
        if (start) {
            columns.add(new Rectangle(WIDTH + width + columns.size() * 300, HEIGHT - height - 120, width, height));
            columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
        } else {
            columns.add(new Rectangle(columns.get(columns.size() - 1).x + 600, HEIGHT - height - 120, width, height));
            columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width, HEIGHT - height - space));
        }
    }

    public void renderColumn(Graphics g, Rectangle column) throws IOException {
        pipes = ImageIO.read(new File(".\\flappybird\\src\\imgs\\pipes.png"));
        g.drawImage(pipes,column.x, column.y, column.width, column.height,this);
    }

    public void jump() {
        if (gameOver) {
            bird = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 30, 30);
            columns.clear();
            yMotion = 0;
            score = 0;

            addColumn(true);
            addColumn(true);
            addColumn(true);
            addColumn(true);

            gameOver = false;
        }
        if (!started) {
            started = true;
        } else if (!gameOver) {
            if (yMotion > 0) {
                yMotion = 0;
            }
            yMotion -= 10;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int speed = 10;
        
        ticks++;

        if (started) {
            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);

                column.x -= speed;
            }

            if (ticks % 2 == 0 && yMotion < 15) {
                yMotion += 2;
            }

            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);

                if (column.x + column.width < 0) {
                    columns.remove(column);

                    if (column.y == 0) {
                        addColumn(false);
                    }
                }
            }

            bird.y += yMotion;

            for (Rectangle column : columns) {
                if (column.y == 0 && bird.x + bird.width / 2 > column.x + column.width / 2 - speed
                        && bird.x + bird.width / 2 < column.x + column.width / 2 + speed) {
                    score++;
                }

                if (column.intersects(bird)) {
                    gameOver = true;

                    if (bird.x <= column.x) {
                        bird.x = column.x - bird.width;

                    } else {
                        if (column.y != 0) {
                            bird.y = column.y - bird.height;
                        } else if (bird.y < column.height) {
                            bird.y = column.height;
                        }
                    }
                }
            }

            if (bird.y > HEIGHT - 120 || bird.y < 0) {
                gameOver = true;
            }

            if (bird.y + yMotion >= HEIGHT - 120) {
                bird.y = HEIGHT - 120 - bird.height;
                gameOver = true;
            }
        }
        render.repaint();
    }

    public void repaint(Graphics g) throws IOException {
        // BACKGROUND
        g.setColor(Color.cyan);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(new Color(255, 173, 0));
        g.fillRect(0, HEIGHT - 120, WIDTH, 130);

        g.setColor(Color.green);
        g.fillRect(0, HEIGHT - 120, WIDTH, 30);

        g.setColor(Color.white);
        g.fillRoundRect(50, 50, 90, 90, 90, 90);

        // bird color

        doge = ImageIO.read(new File(".\\flappybird\\src\\imgs\\doge.png"));
        g.drawImage(doge, bird.x, bird.y, this);
        // g.setColor(Color.red);
        // g.fillRect(bird.x, bird.y, bird.width, bird.height);

        for (Rectangle column : columns) {
            renderColumn(g, column);
        }
        g.setColor(Color.black);
        g.setFont(new Font("Arial", 1, 100));
        if (!started) {
            g.drawString("CLICK or press SPACE to play", 75, HEIGHT / 2 - 50);
        }
        if (gameOver) {
            g.drawString("Game Over!", 100, HEIGHT / 2 - 50);
        }
        if (!gameOver && started) {
            g.drawString(String.valueOf(score/2), WIDTH / 2, 100);
        }
    }

    public static void main(String[] args) {
        flappydoge = new flappyDoge();

    }

    @Override
    public void mouseClicked(MouseEvent e) {

       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        jump();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            jump();
        }
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    

    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        // TODO Auto-generated method stub
        return false;
    }

}
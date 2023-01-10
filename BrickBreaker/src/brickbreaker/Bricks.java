package brickbreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.awt.Rectangle;

/**
 *
 * @author ksm hl 7asba
 */
public class Bricks {

    public int brickX, brickY;
    private int clength, rlength;
    private Color[] colors = { Color.RED, Color.YELLOW, Color.WHITE,Color.BLACK,Color.MAGENTA, Color.GREEN, Color.CYAN, Color.GREEN, Color.PINK };
    private int[][] cells;


    private Rectangle ballRect, brickRect;
    private boolean finish;

    public Bricks(int row, int col) {

        brickX = 1000 / col;
        brickY = 300 / row;

        rlength = row;
        clength = col;

        cells = new int[row][col];
        initArray(cells);
    }

    private void initArray(int[][] cells) {
        for (int[] cell : cells) {
            Arrays.fill(cell, 1);
        }

    }

    public void setValue(int i, int j, int value) {

        this.cells[i][j] = value;
    }

    public void drawBrick(Graphics2D graph, int i, int j) {
        graph.setColor(colors[j % colors.length]);
        graph.fillRect(80 + brickX * i, 50 + brickY * j, brickX, brickY);
        graph.setColor(Color.BLACK);
        graph.setStroke(new BasicStroke(3));
        graph.drawRect(80 + brickX * i, 50 + brickY * j, brickX, brickY);

    }

    public void draw(Graphics2D graph) {
    finish = true;
        for (int i = 0; i < clength; i++) {
            for (int j = 0; j < rlength; j++) {
                if (cells[j][i] != 0) {
                    finish = false;
                    drawBrick(graph, i, j);
                }
            }

        }

    }

    int[] collision(int ballx, int bally, int speedX, int speedY) {

        ballRect = new Rectangle(ballx, bally, 30, 30);

        Main: for (int i = 0; i < clength; i++) {
            for (int j = 0; j < rlength; j++) {
                brickRect = new Rectangle(80 + brickX * i, 50 + brickY * j, brickX, brickY);

                if (cells[j][i] != 0 && ballRect.intersects(brickRect)) {
                    setValue(j, i, 0);

                    if (ballx + 15 <= brickRect.x || ballx + 15 >= brickRect.x + brickRect.width) 
                        speedX *= -1;
                    
                    if (bally + 15 <= brickRect.y || bally + 15 >= brickRect.y + brickRect.height) 
                        speedY *= -1;
                        
                    break Main;
                }
            }
        }
        return new int[] { speedX, speedY ,};
    }



      public boolean isFinish(){
          return finish;
      }



}
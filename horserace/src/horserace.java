import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.UUID;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class horserace {
    JFrame frame;
    JProgressBar p1 = new JProgressBar(0, 100);
    JProgressBar p2 = new JProgressBar(0, 100);
    JProgressBar p3 = new JProgressBar(0, 100);
    JProgressBar p4 = new JProgressBar(0, 100);
    JLabel msg = new JLabel("");
    static boolean runRaceButtonPressed = false;
    static boolean resetRacePressed = true;
    static int winningHorse = 0;
    static boolean winner = false;

    public synchronized void finish(int i) {
        msg.setVisible(true);
        msg.setText("Horse " + winningHorse + "wins!");
        if (i == 100) {
            winner = true;
            System.out.println("Horse #" + winningHorse + "wins");
        }
        frame.getContentPane().add(msg);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    horserace race = new horserace();
                    race.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public horserace() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        p1.setStringPainted(true);
        p1.setForeground(Color.black);
        p1.setBounds(150, 119, 259, 34);
        frame.getContentPane().add(p1);

        p2.setStringPainted(true);
        p2.setForeground(Color.cyan);
        p2.setBounds(150, 159, 259, 34);
        frame.getContentPane().add(p2);

        p3.setStringPainted(true);
        p3.setForeground(Color.blue);
        p3.setBounds(150, 199, 259, 34);
        frame.getContentPane().add(p3);

        p4.setStringPainted(true);
        p4.setForeground(Color.red);
        p4.setBounds(150, 239, 259, 34);
        frame.getContentPane().add(p4);

        msg.setBounds(85, 100, 410, 14);
        msg.setVisible(false);
        frame.getContentPane().add(msg);

        JButton BStart = new JButton("Start Race");
        BStart.setFont(new Font("Tahoma", Font.PLAIN, 18));
        BStart.addActionListener(new RunRace());
        BStart.setBounds(50, 287, 150, 40);
        frame.getContentPane().add(BStart);

        JButton BReset = new JButton("Reset Race");
        BReset.setFont(new Font("Tahoma", Font.PLAIN, 18));
        BReset.addActionListener(new ResetRace());
        BReset.setBounds(205, 287, 150, 40);
        frame.getContentPane().add(BReset);

        JButton BQuit = new JButton("Exit the race");
        BQuit.setFont(new Font("Tahoma", Font.PLAIN, 18));
        BQuit.addActionListener(new Quitrace());
        BQuit.setBounds(360, 287, 150, 40);
        frame.getContentPane().add(BQuit);

    }

    class RunRace implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            if (!runRaceButtonPressed) {
                msg.setVisible(false);
                resetRacePressed = false;
                runRaceButtonPressed = true;
                P1 p1 = new P1();
                p1.start();
                P2 p2 = new P2();
                p2.start();
                P3 p3 = new P3();
                p3.start();
                P4 p4 = new P4();
                p4.start();
            }
        }
    }

    class ResetRace implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            if (!resetRacePressed) {
                msg.setVisible(false);
                resetRacePressed = true;
                runRaceButtonPressed = false;
                winner = false;
                P1 p1 = new P1();
                p1.reset();
                P2 p2 = new P2();
                p2.reset();
                P3 p3 = new P3();
                p3.reset();
                P4 p4 = new P4();
                p4.reset();
            }
        }
    }

    class Quitrace implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
        }
    }

    class P1 extends Thread {
        public void reset() {
            p1.setValue(0);
            p1.repaint();
        }

        public void run() {
            for (int i = 0; i < 101; i++) {
                if (winner) {
                    break;
                }
                p1.setValue(i);
                p1.repaint();
                if (i == 100) {
                    winningHorse = 1;
                    finish(i);
                }
                try {
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 50);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        }
    }

    class P2 extends Thread {
        public void reset() {
            p2.setValue(0);
            p2.repaint();
        }

        public void run() {
            for (int i = 0; i < 101; i++) {
                if (winner) {
                    break;
                }
                p2.setValue(i);
                p2.repaint();
                if (i == 100) {
                    winningHorse = 2;
                    finish(i);
                }
                try {
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 50);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        }
    }

    class P3 extends Thread {
        public void reset() {
            p3.setValue(0);
            p3.repaint();
        }

        public void run() {
            for (int i = 0; i < 101; i++) {
                if (winner) {
                    break;
                }
                p3.setValue(i);
                p3.repaint();
                if (i == 100) {
                    winningHorse = 3;
                    finish(i);
                }
                try {
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 50);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        }
    }

    class P4 extends Thread {
        public void reset() {
            p4.setValue(0);
            p4.repaint();
        }

        public void run() {
            for (int i = 0; i < 101; i++) {
                if (winner) {
                    break;
                }
                p4.setValue(i);
                p4.repaint();
                if (i == 100) {
                    winningHorse = 4;
                    finish(i);
                }
                try {
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()) % 50);
                } catch (InterruptedException err) {
                    err.printStackTrace();
                }
            }
        }
    }
}
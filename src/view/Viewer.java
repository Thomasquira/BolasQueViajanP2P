/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import model.Ball;

/**
 *
 * @author thomas
 */
public class Viewer extends JPanel implements Runnable {
    private final View view;
    private final Thread thread;

    public Viewer(View view) {
        this.view = view;
        this.thread = new Thread(this);
    }

    public void start() {
        thread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (Ball ball : view.getController().getAllBalls()) {
            ball.render(g2);
        }
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}

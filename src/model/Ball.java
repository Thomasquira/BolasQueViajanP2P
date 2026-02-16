/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import Communications.Connections.Channel;

/**
 *
 * @author thomas
 */
public class Ball implements Runnable, Serializable {

    private int x, y;
    private int speedX, speedY;
    private final int diameter;

    private Color COLOR;
    private final Model model;
    private transient Thread thread;

    private transient Channel channel;

    public Ball(Model model) {
        this.model = model;

        this.diameter = 15 + (int) (Math.random() * 50);

        this.x = (int) (Math.random() * (model.getViewerWidth() - diameter));
        this.y = (int) (Math.random() * (model.getViewerHeight() - diameter));

        this.COLOR = new Color((int) (Math.random() * 0x1000000));

        this.speedX = 3 + (int) (Math.random() * 5);
        this.speedY = 3 + (int) (Math.random() * 5);
        if (Math.random() > 0.5) {
            speedX *= -1;
        }
        if (Math.random() > 0.5) {
            speedY *= -1;
        }

        this.thread = new Thread(this);
        this.thread.start();
    }

    public void setDatosDeFuera(int x, int y, int sx, int sy, Color c) {
        this.x = x;
        this.y = y;
        this.speedX = sx;
        this.speedY = sy;
        this.COLOR = c;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public Color getColor() {
        return COLOR;
    }

    @Override
    public void run() {
        while (true) {
            try {
                x += speedX;
                y += speedY;

                if (x <= 0) {
                    model.mandarBola(this);
                    break;
                }

                if (x + diameter >= model.getViewerWidth()) {
                    model.mandarBola(this);
                    break;
                }
                if (y <= 0) {
                    y = 0;
                    speedY = -speedY;
                }
                if (y + diameter >= model.getViewerHeight()) {
                    y = model.getViewerHeight() - diameter;
                    speedY = -speedY;
                }

                Thread.sleep(16);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void render(Graphics2D g) {
        g.setColor(COLOR);
        g.fillOval(x, y, diameter, diameter);
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }
}

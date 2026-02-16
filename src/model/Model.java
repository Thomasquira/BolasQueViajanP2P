/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import DTO.BallDTO;
import controller.Controller;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *
 * @author thomas
 */
public class Model {

    private final Controller controller;
    private CopyOnWriteArrayList<Ball> ballList;

    public Model(Controller controller) {
        this.controller = controller;
        this.ballList = new CopyOnWriteArrayList<>();
    }

    public void recibirBola(BallDTO balldto) {
        Ball b = new Ball(this);
        b.setDatosDeFuera(balldto.x, balldto.y, balldto.speedX, balldto.speedY, balldto.color, balldto.diameter);
        ballList.add(b);
    }

    public void mandarBola(Ball b) {
        int nuevaX = (b.getX() >= getViewerWidth() - b.getDiameter()) ? 5 : getViewerWidth() - b.getDiameter();

        BallDTO ballDto = new BallDTO(
                nuevaX,
                b.getY(),
                b.getSpeedX(),
                b.getSpeedY(),
                b.getColor(),
                b.getDiameter()
        );

        if (bolasqueviajanp2p.MasterController.communicationsController.getConnection() != null) {
            controller.EnviarBola(ballDto);
            ballList.remove(b);
        } else {
            System.out.println("No hay conexión, no se envía la bola.");
        }
    }

    public void addBall() {
        Ball ball = new Ball(this);
        ballList.add(ball);
    }

    public CopyOnWriteArrayList<Ball> getAllBalls() {
        return ballList;
    }

    public int getViewerWidth() {
        return controller.getViewerWidth();
    }

    public int getViewerHeight() {
        return controller.getViewerHeight();
    }

    public void removeBalls() {
        ballList.clear();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DTO.BallDTO;
import bolasqueviajanp2p.MasterController;
import java.util.concurrent.CopyOnWriteArrayList;
import model.Ball;
import model.Model;
import view.View;

/**
 *
 * @author thomas
 */
public class Controller {
    private final Model model;
    private final View view;

    public Controller() {
        this.model = new Model(this);
        this.view = new View(this);
    }

    public void addBall() {
        model.addBall();
    }
    
     public void removeBalls() {
        model.removeBalls();
    }

    public CopyOnWriteArrayList<Ball> getAllBalls() {
        return model.getAllBalls();
    }

    public int getViewerWidth() {
        return view.getViewerWidth();
    }

    public int getViewerHeight() {
        return view.getViewerHeight();
    }

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }
    
    public void EnviarBola(BallDTO ballDTO) {
       MasterController.enviar(ballDTO);
    }
   
    
    public void recibirBola(BallDTO ballDTO) {
        model.recibirBola(ballDTO);
    }
}
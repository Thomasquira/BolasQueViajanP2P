/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.JFrame;
import controller.Controller;
import java.awt.BorderLayout;

/**
 *
 * @author thomas
 */
public class View extends JFrame {

    private final Controller controller;
    private final Viewer viewer;
    private final ControlPanel controlPanel;

    public View(Controller controller) {
        this.controller = controller;
        setTitle("Bolas ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(850, 600);

        this.viewer = new Viewer(this);
        this.controlPanel = new ControlPanel(this);

        add(viewer, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.WEST);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public Controller getController() {
        return controller;
    }

    public int getViewerWidth() {
        return viewer.getWidth();
    }

    public int getViewerHeight() {
        return viewer.getHeight();
    }

    public void startViewer() {
        viewer.start();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 *
 * @author thomas
 */
public class ControlPanel extends JPanel {
    public ControlPanel(View view) {
        setBackground(Color.LIGHT_GRAY);
        JButton btnGenerar = new JButton("Generar Bola");
        JButton btnBorrar = new JButton("Borrar todo");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        btnGenerar.addActionListener(e -> {
            view.getController().addBall();
        });
        
        btnBorrar.addActionListener(e -> {
        view.getController().removeBalls();
    });

        add(btnGenerar);
         add(Box.createVerticalStrut(10));
        add(btnBorrar);
    }
}

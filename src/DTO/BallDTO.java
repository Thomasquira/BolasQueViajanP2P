/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author thomas
 */
public class BallDTO implements Serializable {
    public int x, y;
    public int speedX, speedY;
    public Color color;

    public BallDTO(int x, int y, int speedX, int speedY, Color color) {
        this.x = x;
        this.y = y;
        this.speedX = speedX;
        this.speedY = speedY;
        this.color = color;
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Pc
 */
public interface EntityClouds {
    
    public void tick();
    public void render(Graphics g);
    
    public Rectangle getBounds();
    public double getX();
    public void setX(double x);
    public double getY();
    public void setY(double y);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Rectangle;

/**
 *
 * @author Pc
 */
public class GameObject {
    
    private double x,y;

    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public Rectangle getBounds(int width,int height){
        return new Rectangle((int)x,(int)y,width,height);
    }
    
    //GETTERS & SETTERS
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
    
    
}

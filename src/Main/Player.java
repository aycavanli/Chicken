/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entities.EntityC;
import Entities.EntityD;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Player extends GameObject implements EntityD{    
        
    private double velX=0;
    private double velY=0;

    private Textures tex;
    private Game game;
    
    public Player(double x, double y,Textures tex,Game game) {
        super(x, y);
        this.tex=tex;
        this.game=game;
    }
    public void tick(){
        
        //  FOR SWIFT SPEED
        setX(getX()+velX);
        setY(getY()+velY);
        
        //  SCREEN BOUNDS CONTROLLING
        if(getX()<=0)
            setX(0);
        if(getX()>=Game.WIDTH*Game.SCALE-16)
            setX(Game.WIDTH*Game.SCALE-16);
        if(getY()<0)
            setY(0);
        if(getY()>=Game.HEIGHT*Game.SCALE-30)
            setY(Game.HEIGHT*Game.SCALE-30);
        
        //  HEALTH BAR DECREASING
        for(int i=0;i<game.geteCs().size();i++){
            EntityC tempEntityC = game.geteCs().get(i);
            
            if(Physics.Collision(this, tempEntityC)){
                game.getC().removeEntity(tempEntityC);
                game.setHealth(game.getHealth()-10);
            }
        }
    }
    
     public void render(Graphics g){
        g.drawImage(tex.chicken, (int)getX(),(int)getY(), null);
    
    }
    public Rectangle getBounds(){
        return new Rectangle((int)getX(),(int)getY(),32,32);
    }
    //GETTERS & SETTERS

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public double getVelX() {
        return velX;
    }

    public double getVelY() {
        return velY;
    }
       
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entities.EntityA;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Eggs extends GameObject implements EntityA{
   
    private Textures tex;
    private Game game;
    private String face;
    private int speed;
    
    public Eggs(double x, double y,Textures tex,Game game,String face) {
        super(x, y);
        this.tex=tex;     
        this.game=game;
        this.face=face;
        this.speed=2;
    }
    
    public void tick(){
        
        if(face=="right"){
            setX(getX()+speed);
            if(getX() >=Game.WIDTH*Game.SCALE)
                game.getC().removeEntity(this);
        }
        
        else if(face=="left" ){
            setX(getX()-speed); 
            if(getX()<=-16)
                game.getC().removeEntity(this);
        }
        
    }
    
    public Rectangle getBounds(){
        return new Rectangle((int)getX(),(int)getY(),32,32);
    }
    public void render(Graphics g){
        g.drawImage(tex.eggs, (int)getX(), (int) getY() , null);
    }
    
}

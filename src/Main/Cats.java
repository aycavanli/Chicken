/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entities.EntityC;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Pc
 */
public class Cats extends GameObject implements EntityC {
    
    private Textures tex;
    private Game game;
    
    Random r = new Random();
    private int speed = r.nextInt(3)+1;
    
    public Cats(double x, double y,Textures tex,Game game) {
        super(x, y);
        this.tex=tex;
        this.game=game;
    }
    
    public void tick(){
        
        setY(getY()-speed);
        
        if(getY()<=0){ 
           speed=r.nextInt(3)+1;           
           setY(Game.HEIGHT*Game.SCALE);
           setX(r.nextInt(Game.WIDTH*Game.SCALE));       
        }
        
    }
    public Rectangle getBounds(){
        return new Rectangle((int)getX(),(int)getY(),32,32);
    }
    public void render(Graphics g){
        g.drawImage(tex.cats, (int)getX(), (int)getY(), null);
    }
}

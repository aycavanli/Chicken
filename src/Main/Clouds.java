/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entities.EntityClouds;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Pc
 */
public class Clouds extends GameObject implements EntityClouds{
    
    private Textures tex;
    private Game game;   
    private Random r=new Random();
    
    public Clouds(double x, double y, Textures tex,Game game) {
        super(x, y);
        this.tex = tex;
        this.game=game;
    }
    
    public void tick(){
       setY(getY()-0.5); 
       
       if(getY()<=-30){         
           setY(Game.HEIGHT*Game.SCALE);
           setX(r.nextInt(Game.WIDTH*Game.SCALE));       
        }
    }
    public Rectangle getBounds(){
        return new Rectangle((int)getX(),(int)getY(),32,32);
    }
    public void render(Graphics g){
        g.drawImage(tex.clouds, (int)getX(), (int)getY(), null);
    }
    
    
    
}

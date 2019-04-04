/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entities.EntityA;
import Entities.EntityB;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

/**
 *
 * @author Pc
 */
public class Basket extends GameObject implements EntityB{

    private Textures tex;
    private Controller c;
    private Game game;   
    private double speed;

    public Basket(double x, double y, Textures tex,Controller c,Game game) {
        super(x, y);
        this.tex = tex;
        this.game=game;
        this.c=c;     
        this.speed=0.5;
    }
    
    public void tick(){
       setY(getY()-speed);
       
       if(getY()<=-30){                  
            setY(Game.HEIGHT*Game.SCALE);  
        }
        for(int i =0;i<game.geteAs().size();i++){
            
            EntityA tempEntityA=game.geteAs().get(i);
            
            if(Physics.Collision(this,tempEntityA)){                
                game.getC().removeEntity(this);
                game.getC().removeEntity(tempEntityA);
                game.setBasket_killed(game.getBasket_killed()+1);
            }
        }
       
    }
    public Rectangle getBounds(){
        return new Rectangle((int)getX(),(int)getY(),32,32);
    }
    public void render(Graphics g){
        g.drawImage(tex.basket, (int)getX(), (int)getY(), null);
    }
        
}

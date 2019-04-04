/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.image.BufferedImage;

/**
 *
 * @author Pc
 */
public class Textures {
    
    public BufferedImage chicken,eggs,cats,basket,clouds; 
    private SpriteSheet ss;

    public Textures(Game game) {
        ss = new SpriteSheet(game.getSpriteSheet());
        
        getTextures();
    }
    
    private void getTextures(){
        
        chicken=ss.grabImage(1, 1, 32, 32);
        eggs=ss.grabImage(2, 1, 25, 32);
        cats=ss.grabImage(3, 1, 60, 32);
        basket=ss.grabImage(5, 1, 32, 32);      
        clouds=ss.grabImage(7, 1, 35, 32);
    }
    
}

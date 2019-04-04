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
public class SpriteSheet {
    
    private BufferedImage image;
    
    public SpriteSheet(BufferedImage ss){
        this.image=ss;
    }
    public BufferedImage grabImage(int col, int row,int width,int height){
        BufferedImage img = image.getSubimage((col*32)-32, (row*32)-32, width, height);
        return img;
    }
    
        
}

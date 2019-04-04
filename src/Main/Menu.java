/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author Pc
 */
public class Menu {
    
    public Rectangle playButton = new Rectangle(Game.WIDTH/8 +120,150,100,50);
    public Rectangle quitButton = new Rectangle(Game.WIDTH/8 +120,250,100,50);
    
    public void render(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        
        Font fnt0=new Font("arial",Font.BOLD,60);
        g.setFont(fnt0);
        g.setColor(Color.red);
        g.drawString("CHICKEN GAME", Game.WIDTH*Game.SCALE/2, Game.HEIGHT*Game.SCALE/4);
        
        Font fnt1=new Font("arial",Font.BOLD,30);
        g.setFont(fnt1);
        g.drawString("PLAY", playButton.x+10, playButton.y+35);
        g.drawString("QUIT", quitButton.x+10, quitButton.y+35);
        g2d.draw(playButton);
        g2d.draw(quitButton);
        
    }
    
}

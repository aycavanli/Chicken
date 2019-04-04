/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Pc
 */
public class MouseInput implements MouseListener{
    Game game;
    
    public MouseInput(Game game) {
        this.game = game;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        
        int mx=e.getX();
        int my=e.getY();
        //public Rectangle playButton = new Rectangle(Game.WIDTH/8 +120,150,100,50);
        //public Rectangle quitButton = new Rectangle(Game.WIDTH/8 +120,250,100,50);
        //  PLAY BUTTON
        if(mx>=Game.WIDTH/8+120 && mx<=Game.WIDTH/8+220){
            //  PRESSED PLAY BUTTON
            if(my>=150&&my<=200){
                game.setState(game.getStateGame());
            }
        }
        //  QUIT BUTTON
        if(mx>=Game.WIDTH/8+120 && mx<=Game.WIDTH/8+220){
            //  PRESSED QUIT BUTTON
            if(my>=250&&my<=300){
                System.exit(1);
            }
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entities.EntityA;
import Entities.EntityB;
import Entities.EntityC;
import Entities.EntityD;
import java.util.LinkedList;

/**
 *
 * @author Pc
 */
public class Physics {
    
    public static boolean Collision(EntityA entA ,EntityB entB){
        
        if(entA.getBounds().intersects(entB.getBounds()))
            return true;
        else
            return false;
    }
    public static boolean Collision(EntityB entB ,EntityA entA){
        
        if(entB.getBounds().intersects(entA.getBounds()))
            return true;
        else
            return false;
    }
    public static boolean Collision(EntityC entC ,EntityD entD){
        
        if(entC.getBounds().intersects(entD.getBounds())){
            return true;
        }                     

        return false;
    }
    public static boolean Collision(EntityD entD ,EntityC entC){
        
         if(entD.getBounds().intersects(entC.getBounds()))
            return true;
        else
            return false;
    
    }
}

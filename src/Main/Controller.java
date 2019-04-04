/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Entities.EntityA;
import Entities.EntityB;
import Entities.EntityC;
import Entities.EntityClouds;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Pc
 */
public class Controller {
    
    private LinkedList<EntityA> eAs= new LinkedList<EntityA>();
    private LinkedList<EntityB> eBs= new LinkedList<EntityB>();
    private LinkedList<EntityC> eCs= new LinkedList<EntityC>();
    private LinkedList<EntityClouds> eClouds= new LinkedList<EntityClouds>();
 
    Random r = new Random();
    EntityA entA;
    EntityB entB;
    EntityC entC;
    EntityClouds entClouds;
    private Textures tex;
    private Game game;
    
    public Controller(Textures tex,Game game) {
        this.tex=tex;
        this.game=game;
        
    }
        
    public void tick(){
       for(int i=0;i<eAs.size();i++){
            entA=eAs.get(i);            
            entA.tick();
        }
       for(int i=0;i<eBs.size();i++){
            entB=eBs.get(i);            
            entB.tick();
        }
       for(int i=0;i<eCs.size();i++){
            entC=eCs.get(i);            
            entC.tick();
        }
       for(int i=0;i<eClouds.size();i++){
            entClouds=eClouds.get(i);            
            entClouds.tick();
        }
       
    }
    
    public void render(Graphics g){
        for(int i=0;i<eAs.size();i++){
            entA=eAs.get(i);            
            entA.render(g);
        }
       for(int i=0;i<eBs.size();i++){
            entB=eBs.get(i);            
            entB.render(g);
        }
       for(int i=0;i<eCs.size();i++){
            entC=eCs.get(i);            
            entC.render(g);
        }
        for(int i=0;i<eClouds.size();i++){
            entClouds=eClouds.get(i);            
            entClouds.render(g);
        }
    } 
    
    //  CREATE AN OBJECT
    public void createCats(int enemy_count){
        for(int i=0;i<enemy_count;i++){
            addEntity(new Cats(r.nextInt(Game.WIDTH*Game.SCALE), r.nextInt(Game.HEIGHT*Game.SCALE-30), tex,game));
        }
    }
    public void createBaskets(){
        int increase=Game.HEIGHT*Game.SCALE/game.getBasket_count();
        for(int i=0;i<(Game.HEIGHT*Game.SCALE);i+=increase){
            addEntity(new Basket(0, i, tex,this,game));
            addEntity(new Basket(Game.WIDTH*Game.SCALE-28, i, tex,this,game));
        }
    }
    public void createClouds(){
        int increase=Game.HEIGHT*Game.SCALE/16;
        for(int i=0;i<(Game.HEIGHT*Game.SCALE);i+=increase){
            addEntity(new Clouds(r.nextInt(Game.WIDTH*Game.SCALE), i, tex,game));
        }
    }
    
    //  ADD & REMOVE LINKEDLIST
    public void addEntity(EntityA block){
        eAs.add(block);
    }
    public void removeEntity(EntityA block){
        eAs.remove(block);
    }
    public void addEntity(EntityB block){
        eBs.add(block);
    }
    public void removeEntity(EntityB block){
        eBs.remove(block);
    }
    public void addEntity(EntityC block){
        eCs.add(block);
    }
    public void removeEntity(EntityC block){
        eCs.remove(block);
    }
    public void addEntity(EntityClouds block){
        eClouds.add(block);
    }
    public void removeEntity(EntityClouds block){
        eClouds.remove(block);
    }
    
    //  GETTERS & SETTERS

    public LinkedList<EntityA> geteAs() {
        return eAs;
    }

    public void seteAs(LinkedList<EntityA> eAs) {
        this.eAs = eAs;
    }

    public LinkedList<EntityB> geteBs() {
        return eBs;
    }

    public void seteBs(LinkedList<EntityB> eBs) {
        this.eBs = eBs;
    }

    public LinkedList<EntityC> geteCs() {
        return eCs;
    }

    public void seteCs(LinkedList<EntityC> eCs) {
        this.eCs = eCs;
    }

    public LinkedList<EntityClouds> geteClouds() {
        return eClouds;
    }

    public void seteClouds(LinkedList<EntityClouds> eClouds) {
        this.eClouds = eClouds;
    }
    
    
    
}

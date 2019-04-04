
package Main;

import Entities.EntityA;
import Entities.EntityB;
import Entities.EntityC;
import Entities.EntityClouds;
import Entities.EntityD;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
    
    public static final  long serialVersionUID=1L;
    public static final int WIDTH = 320;
    public static final int HEIGHT = WIDTH /12*9;
    public static final int SCALE=3;
    public final String TITLE="~CHICKEN GAME~";
        
    private boolean running=false;
    private Thread thread;
    
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private BufferedImage spriteSheet = null;
    private BufferedImage game_background=null;
    private BufferedImage menu_background=null;
    private BufferedImage lose_background=null;
    
    private boolean is_shooting = false;
    private String which_face="right";
    
    private int health=200;
    private int level=1;
    
    private int cats_count=2;
    private int basket_count=6;
    private int basket_killed=0;
    private int succesfull_eggs=0;
    
    private Player p;
    private Controller c;
    private Textures tex;
    private Menu menu;
    
    private LinkedList<EntityA> eAs;
    private LinkedList<EntityB> eBs;
    private LinkedList<EntityC> eCs;
    private LinkedList<EntityClouds> eClouds;
    
    private enum STATE{
        MENU,
        GAME,
        LOSE,
        LEVEL,
        HELP
    };
    private STATE state= STATE.MENU;
    
    
    public void init(){
        requestFocus();
        
        BufferedImageLoader loader= new BufferedImageLoader();
        try {
            spriteSheet = loader.loadImage("/sprite_sheet.png");
            game_background = loader.loadImage("/background.jpg");
            menu_background = loader.loadImage("/menu_background.jpg");
            lose_background = loader.loadImage("/lose_background.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        addKeyListener(new KeyInput(this));
        addMouseListener(new MouseInput(this));
        tex= new Textures(this);
        
        menu=new Menu();
        p = new Player(200, 200,tex,this);
        c= new Controller(tex,this);
        c.createCats(cats_count);
        c.createBaskets();
        c.createClouds();
        
        eAs=c.geteAs();
        eBs=c.geteBs();
        eCs=c.geteCs();
        eClouds=c.geteClouds();
    }
        
    private synchronized void start(){
        if(running)
            return;
        
        running=true;
        thread = new Thread(this);
        thread.start();
    }
    private synchronized void stop() {
        if(!running)
            return;
        
        running=false;
        try{
            thread.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
       
        System.exit(1);
    }
    
    @Override
    public void run() {
        init();
        long lastTime = System.nanoTime();
        final double amountOfTicks=60.0;
        double ns=100000000/amountOfTicks;
        double delta=0;
        int updates=0;
        int frames=0;
        long timer=System.currentTimeMillis();
        
        while(running){
            long now=System.nanoTime();
            delta+=(now-lastTime)/ns;
            lastTime=now;
            if(delta>=1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            
            if(System.currentTimeMillis()- timer>1000){
                timer+=1000;
                System.out.println(updates +" Ticks, Fps " + frames );
                updates=0;
                frames=0;
            }
        }
        stop();
    }
    private void tick(){
        if(state ==STATE.GAME){
            p.tick();
            c.tick();
        }
               
        if(basket_killed>=basket_count){
            basket_count+=2;
            basket_killed=0;
            cats_count+=2;
            c.createCats(cats_count);
            c.createBaskets();
            level++;
        }
        
        if(health<=0){
            state=STATE.LOSE;
        }
    }
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        
        if(bs==null){
            
            createBufferStrategy(3);
            return;
        }
        Graphics g= bs.getDrawGraphics();
        /////////////
        if(state == STATE.MENU){
            g.drawImage(image, 0, 0, getWidth(),getHeight(),this);
            g.drawImage(menu_background,0, 0, null);
            menu.render(g);
        }
        else if(state==STATE.LOSE){
            g.drawImage(lose_background,0, 0, null);
        }
        else if(state==STATE.GAME){
            
            g.drawImage(image, 0, 0, getWidth(),getHeight(),this);
            g.drawImage(game_background,0, 0, null);
            p.render(g);
            c.render(g);
        
            //  HEALTH BAR
            g.setColor(Color.gray);
            g.fillRect(5, 5, 200,50);
            g.setColor(Color.green);
            g.fillRect(5, 5, health,50);
            g.setColor(Color.white);
            g.drawRect(5, 5, 200,50);
            Font fnt0=new Font("arial",Font.BOLD,20);
            g.setFont(fnt0);
            g.setColor(Color.red);
            g.drawString("HEALTH IS "+health, 5, 55);

            //  LEVEL BAR
            g.setColor(Color.gray);
            g.fillRect(Game.WIDTH*Game.SCALE-200, 5, 200,50);
            g.setColor(Color.blue);
            g.fillRect(Game.WIDTH*Game.SCALE-200, 5, level*10,50);
            g.setColor(Color.white);
            g.drawRect(Game.WIDTH*Game.SCALE-200, 5, 200,50);
            g.setFont(fnt0);
            g.setColor(Color.red);
            g.drawString("LEVEL IS "+level, Game.WIDTH*Game.SCALE-200, 55);
        }
        /////////////
        g.dispose();
        bs.show();
        
    }
    public void keyPressed(KeyEvent e){
        int key= e.getKeyCode();
        
        if(state==STATE.GAME){
            
            if(key==KeyEvent.VK_RIGHT){
                p.setVelX(3);
                which_face="right";
            }
            else if(key==KeyEvent.VK_LEFT){
                p.setVelX(-3);
                which_face="left";
            }
            else if(key==KeyEvent.VK_DOWN){
                 p.setVelY(3);
            }
            else if(key==KeyEvent.VK_UP){
                p.setVelY(-3);
            }
            else if(key == KeyEvent.VK_SPACE && !is_shooting){
               is_shooting=true;
               c.addEntity(new Eggs(p.getX(),p.getY(),tex,this,which_face));
            }
        }    
    }
    public void keyReleased(KeyEvent e){
        int key= e.getKeyCode();
        
        if(key==KeyEvent.VK_RIGHT){
            p.setVelX(0);
        }
        else if(key==KeyEvent.VK_LEFT){
             p.setVelX(0);
        }
        else if(key==KeyEvent.VK_DOWN){
             p.setVelY(0);
        }
        else if(key==KeyEvent.VK_UP){
            p.setVelY(0);
        }
        else if(key == KeyEvent.VK_SPACE){
            is_shooting=false;
        }
        
    }
    
    public static void main(String[] args) {
        Game game= new Game();
        
        game.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        game.setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
        game.setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
     
        JFrame frame= new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        game.start();
    }
    //  GETTERS & SETTERS
    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public void setSpriteSheet(BufferedImage spriteSheet) {
        this.spriteSheet = spriteSheet;
    }

    public int getCats_count() {
        return cats_count;
    }

    public void setCats_count(int cats_count) {
        this.cats_count = cats_count;
    }

    public int getSuccesfull_eggs() {
        return succesfull_eggs;
    }

    public void setSuccesfull_eggs(int succesfull_eggs) {
        this.succesfull_eggs = succesfull_eggs;
    }

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
    
    public Player getP() {
        return p;
    }

    public void setP(Player p) {
        this.p = p;
    }

    public boolean isIs_shooting() {
        return is_shooting;
    }

    public void setIs_shooting(boolean is_shooting) {
        this.is_shooting = is_shooting;
    }

    public String getWhich_face() {
        return which_face;
    }

    public void setWhich_face(String which_face) {
        this.which_face = which_face;
    }

    public Controller getC() {
        return c;
    }

    public void setC(Controller c) {
        this.c = c;
    }
  
    public int getBasket_killed() {
        return basket_killed;
    }

    public void setBasket_killed(int basket_killed) {
        this.basket_killed = basket_killed;
    }

    public int getBasket_count() {
        return basket_count;
    }

    public void setBasket_count(int basket_count) {
        this.basket_count = basket_count;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public STATE getStateGame() {
        return state.GAME;
    }
    public STATE getStateMenu() {
        return state.MENU;
    }
    public STATE getStateLose() {
        return state.LOSE;
    }

    public void setState(STATE state) {
        this.state = state;
    }
    
    
    
}

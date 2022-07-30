package game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import java.util.ArrayList;
import nhanvat.Entity;
import nhanvat.EntityManager;
import nhanvat.Nguoichoi;
import hieuung.Image;
import dieukhien.KeyManager;
import dieukhien.MouseManager;
import trangthai.GameState;
import trangthai.Gameover;
import trangthai.Gamewin;
import trangthai.MenuState;
import trangthai.State;

public class Game implements Runnable {
    
    private Display display;
    private int width, height;
    private String title;
    private Nguoichoi player;
    private EntityManager entityManager;
    private ArrayList<Entity> entity;
    private int check_entity =0;
    private int map = 0;
    private boolean die_player;
    private int level = 0;
    private boolean running = false;
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;

    public State gameState;
    public MenuState menuState;
  
    private KeyManager keyManager;
    private MouseManager mouseManager;
    
    private Handler handler;
    private Gameover gameover;
    private Gamewin gamewin;
    
    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }
    
    private void init(String path) {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Image.init();
        
        handler = new Handler(this);
        player = new Nguoichoi(handler, 0, 0);
        gameState = new GameState(handler, player, path);
        menuState = new MenuState(handler);
        
        if(map==0) {
        	State.setState(menuState);
        }
        
        else {
        	State.setState(gameState);
        }   
    }
    
    
    private void tick() {
        keyManager.tick();
        State.getState().tick();
        entityManager = handler.getBando().getEntityManager();
        entity = entityManager.getEntities();
        check_entity = entity.size();
    }
    
    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);
        //Draw Here
        State.getState().render(g);        
        //End Drawing!
        bs.show();
        g.dispose();
    }
    
    @Override
    public void run() {
    	long last = 0;
    	String[] path=new String[3];
    	path[0]="data/world1.txt";
    	path[1]="data/world2.txt";
    	path[2]="data/world3.txt";
    	
    	while(true) {
            init(path[map]);
            int fps = 60;
            double timePerTick = 1000000000 / fps;
            double delta = 0;
            long now;
            long lastTime = System.nanoTime();
            long timer = 0;
            int ticks = 0;
            running=true;
            
            while(running) {
                now = System.nanoTime();
                delta += (now - lastTime) / timePerTick;
                timer += now - lastTime;
                lastTime = now;
                
                if(delta >= 1) {
                    tick();
                    render();
                    ticks++;
                    delta--; 
                }
                
                if(die_player){
                    map++;
                    running = false;
                    die_player = false;
                    System.out.println(map);
                    last = System.currentTimeMillis();
                    gameOver();
                    stop();
                    Launcher laun = new Launcher();
                    map = 0;
                }else if(check_entity == 1) { 
                    keyManager.refresh();
                    map++;
                    running = false;
                    System.out.println(map);
                    last = System.currentTimeMillis();
                    if(map == 3) {
                        gameWin();
                        stop();
                        Launcher laun = new Launcher();
               		try {
                            Thread.sleep(600);
			} catch (InterruptedException e) {
                            e.printStackTrace();
			}
                        map = 0;
                        running = false;
              
                    }
                        
                    System.out.println("--------");
                    check_entity = 0;
                    display.close();
                    break;
                }
                 
            }
    	} 
    }
    
    public void gameWin() {
    	map=0;
    	display.close();
    	try {
            gamewin=new Gamewin(handler);
            try{
                Thread.sleep(3000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
             }
		} catch (IOException e) {
			e.printStackTrace();
		}
    	gamewin.close();
    }
    public void gameOver() {
    	map=0;
    	display.close();
    	try {
			gameover=new Gameover(handler);
			try{
                Thread.sleep(3000);
                }
            catch(InterruptedException e){
                 e.printStackTrace();
                }
		} catch (IOException e) {
			e.printStackTrace();
		}
    	gameover.close();
    }
    
    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager() {
        return mouseManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Display getDisplay() {
        return display;
    }

    public Nguoichoi getPlayer() {
        return player;
    }
    
    public void setDie_player(boolean die_player){
        this.die_player=die_player;
    }

    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }
    
    
    public synchronized void start() {
        if(running)
            return;
        running = true;        
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop() {
        if(!running)
            return;
        running = false;
        try{
                Thread.sleep(600);
            }
            catch(InterruptedException e){
                e.printStackTrace();
             }
    }
    
    public boolean isRunning() {
	return running;
    }


}

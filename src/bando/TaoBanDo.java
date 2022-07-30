package bando;

import java.awt.Graphics;
import game.Handler;
import nhanvat.EntityManager;
import nhanvat.Nguoichoi;
import nhanvat.Quaivat;
import nhanvat.Quaivat1;


public class TaoBanDo {
    
    private Handler handler;
    private int width, height; //kich thuoc ban do
    private int X, Y; //vi tri ban dau cua player 
    private int numberOfMonster;
    private int[][] tile;
    private int[] monsters;
    private EntityManager entityManager;    
    
    private void loadWorld(String path) {
        String file = ReadFile.readFile(path);
        String[] tachso = file.split("\\s+");
        width = ReadFile.parseInt(tachso[0]);
        height = ReadFile.parseInt(tachso[1]);
        
        //Player
        X = ReadFile.parseInt(tachso[2]);
        Y = ReadFile.parseInt(tachso[3]);
        
        //Monster
        numberOfMonster = ReadFile.parseInt(tachso[4]);
        monsters = new int[numberOfMonster*3];
        for (int i = 0; i < monsters.length; i++) {
                monsters[i] = ReadFile.parseInt(tachso[i + 5]);
        }
        
        //Tile
        tile = new int[width][height];
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++){
                tile[x][y] = ReadFile.parseInt(tachso[(x + y * width) + 5 + monsters.length]);
            }
        }
    }
    
    public TaoBanDo(Handler handler, Nguoichoi player, String path) {
        this.handler = handler;
        entityManager = new EntityManager(handler, player);
        loadWorld(path);
        
        // add Monters
        for(int i = 0; i < monsters.length; i +=3) {
            if(monsters[i] == 1)
                entityManager.addEntity(new Quaivat(handler, monsters[i+1], monsters[i+2]));
            else if(monsters[i] == 2)
                entityManager.addEntity(new Quaivat1(handler, monsters[i+1], monsters[i+2]));
        }
        
        //add Player
        entityManager.getPlayer().setX(X);
        entityManager.getPlayer().setY(Y);
    }
    
    public void tick() {  
        entityManager.tick();
    }
    
    public void render(Graphics g) {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                getTile(x,y).render(g,(x * Diahinh.TILEWIDTH ), (y * Diahinh.TILEHEIGHT));
            }
        }
        entityManager.render(g); 
    }
   
    public Diahinh getTile(int x, int y) {
        return Diahinh.tile[this.tile[x][y]];
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public int[][] getTile() {
        return tile;
    }

    public int getNumberOfMonster() {
        return numberOfMonster;
    }

    public void setNumberOfMonster(int numberOfMonster) {
        this.numberOfMonster += numberOfMonster;
    }
}

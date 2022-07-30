package nhanvat;

import java.awt.Graphics;
import game.Handler;
import hieuung.Animation;
import hieuung.Image;

public class Quaivat extends Vatthe {
    
    protected static final int[] ZOMBIE_SPEED = {1, 3};
    private static final int[] BONUS = {50, 100};
    protected Animation animDown, animUp, animLeft, animRight, currentImage;     
    
    public Quaivat(Handler handler, float x, float y) {
        super(handler, x, y, Vatthe.DEFAULT_CREATURE_WIDTH, Vatthe.DEFAULT_CREATURE_HEIGHT);
        level = handler.getGame().getLevel();
        bounds.x = 1;
        bounds.y = 1;
        bounds.width = 28;
        bounds.height = 28;
        
        //Animation
        currentImage = new Animation(200, Image.zombie_down);
    }
    
    @Override
    public void tick() {
        //Animation
        currentImage.tick();
        //Move
        move();
    }
    
    public void attack() {
    	
    }
    
    @Override
    public void die() {
        handler.getBando().setNumberOfMonster(-1);    
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(currentImage.getCurrentFrame(), (int) x, (int) y, width, height, null);
    }
    
    @Override
    public void move() {
    }
    
    @Override
    public void moveX(){
        
    }

    @Override
    public void moveY(){
        
    }
    
    @Override
    public void setLevel(int level) {
        speed = ZOMBIE_SPEED[level];
        health = DEFAULT_HEALTH[level];
        this.level = level;
    }
    
    @Override
    public boolean isLeft() {
        return currentImage==animLeft;
    }
    
    @Override
    public boolean isUp() {
        return currentImage==animUp;
    }
    
    @Override
    public boolean isRight() {
        return currentImage==animRight;
    }
    
    @Override
    public boolean isDown() {
        return currentImage==animDown;
    }
    
}

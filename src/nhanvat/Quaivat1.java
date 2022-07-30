package nhanvat;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import game.Handler;
import hieuung.Animation;
import hieuung.Image;
import bando.Diahinh;

public class Quaivat1 extends Quaivat {
    private static final int[] LIMIT = {3,5};
    private static final int[] BONUS = {100, 150};

    private int xStart, yStart;
    
    private long lastAttackTimer, attackCooldown = 400, attackTimer = attackCooldown;
   
    private ArrayList<Vukhi> bullets;
    
    public Quaivat1(Handler handler, float x, float y) {
        super(handler, x, y);
        //Animation
        animDown = new Animation(200, Image.zombie1_down);
        animUp = new Animation(200, Image.zombie1_up);
        animLeft = new Animation(200, Image.zombie1_left);
        animRight = new Animation(200, Image.zombie1_right);
        currentImage = animLeft;
        setMove();
        // Toa do khoi tao
        xStart = (int) (x / Diahinh.TILEWIDTH);
        yStart = (int) (y / Diahinh.TILEHEIGHT);
        
        //Bullet               
        bullets = new ArrayList<Vukhi>();
    }
    
    @Override
    public void tick() {
        //Animation
        currentImage.tick();
        //Movement
        move();
        //Attack
        attack();
        
        Iterator<Vukhi> it = bullets.iterator();
        while(it.hasNext()){
            Vukhi b = it.next();
            b.tick();
            if(!b.isActive())
                it.remove();
        }
    }
    
    @Override
    public void attack() {
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
            return;
      
        if(isUp()){
                bullets.add(new Vukhi(handler,this, x + width / 4, y));
               
        } else if(isDown()) {
            bullets.add(new Vukhi(handler,this, x + width /4, y + height));
                
        }else if(isLeft()) {
            bullets.add(new Vukhi(handler,this, x - width / 2, y + height / 4));
                
        }else if(isRight()) {
            bullets.add(new Vukhi(handler,this, x + width, y + height / 4));
        }
        
        attackTimer = 0;
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(getCurrentAnimationFrame(), (int) (x), (int) (y), width, height, null);
        bullets.forEach((b) -> {
            if(b.isActive())
                b.render(g);
        });
    }
    
    private BufferedImage getCurrentAnimationFrame() {
        if(xMove < 0){
            currentImage = animLeft;
        } else if (xMove > 0) {
            currentImage = animRight;
        } else if (yMove < 0) {
            currentImage = animUp;
        } else if (yMove > 0) {
            currentImage = animDown;
        }
    return currentImage.getCurrentFrame();
        
    }
    
    public void setMove() {
        Random rd = new Random();
        int i = rd.nextInt(2);
        if(i==1) {
            xMove = speed;
            yMove = 0;
        }
        else{
            xMove = 0;
            yMove = speed;
        }
            
    }
    
    @Override
    public void move() {
        moveX();
        moveY();
    }
    
    @Override
    public void moveX(){
        if(xMove > 0){ //Tiếp tục di chuyển sang trái nếu không chạm vào tile và đi trong khoảng cố định 
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Diahinh.TILEWIDTH;
            if((Math.abs(tx - xStart) < LIMIT[level]) && (!collisionWithTile(tx, (int) (y + bounds.y) / Diahinh.TILEHEIGHT) &&
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Diahinh.TILEHEIGHT))){
                x += xMove;
            }else if(Math.abs(tx - xStart) >= LIMIT[level]) {
                xMove = -xMove;
            }
            else{ //Di chuyển đến sát bound của tile và quay lai 
                x = tx * Diahinh.TILEWIDTH - bounds.x - bounds.width - 1;
                xMove = -xMove;
            }
        } else if (xMove < 0) { // Đi sang trái 
            int tx = (int) (x + xMove + bounds.x) / Diahinh.TILEWIDTH;
            if((Math.abs(tx - xStart) < LIMIT[level]) && (!collisionWithTile(tx, (int) (y + bounds.y) / Diahinh.TILEHEIGHT) &&
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Diahinh.TILEHEIGHT))){
                x += xMove;
            } else if(Math.abs(tx - xStart) >= LIMIT[level]) {
                xMove = -xMove;
            }
            else {
                x = tx * Diahinh.TILEWIDTH + Diahinh.TILEWIDTH - bounds.x;
                xMove = -xMove;
            }
        }
    }

    @Override
    public void moveY(){
        if(yMove < 0) {
            int ty = (int) (y + yMove + bounds.y) / Diahinh.TILEHEIGHT;
            
            if((Math.abs(ty - yStart) < LIMIT[level]) && (!collisionWithTile((int) (x + bounds.x) / Diahinh.TILEWIDTH, ty)  &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Diahinh.TILEWIDTH, ty))){
                y += yMove;
            } else if(Math.abs(ty - yStart) >= LIMIT[level]) {
                yMove = -yMove;
            } 
            else {
                y = ty * Diahinh.TILEHEIGHT + Diahinh.TILEHEIGHT - bounds.y;
                yMove = -yMove;

            }
        } else if(yMove > 0){
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Diahinh.TILEHEIGHT;
            
            if((Math.abs(ty - yStart) < LIMIT[level]) && (!collisionWithTile((int) (x + bounds.x) / Diahinh.TILEWIDTH, ty)  &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Diahinh.TILEWIDTH, ty))){
                y += yMove;
            } else if(Math.abs(ty - yStart) >= LIMIT[level]) {
                yMove = -yMove;
            }
            else {
                y = ty * Diahinh.TILEHEIGHT - bounds.y - bounds.height;
                yMove = -yMove;

            }
        }
    }
    

}

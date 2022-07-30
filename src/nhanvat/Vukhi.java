package nhanvat;

import java.awt.Graphics;
import java.awt.Rectangle;
import game.Handler;
import hieuung.Image;
import bando.Diahinh;

public class Vukhi extends Vatthe {
    
    public static final float[] BULLET_SPEED = {2.0f, 4.0f};
    private Vatthe owner;
    private float X,Y;
    public Vukhi(Handler handler, Vatthe owner, float x, float y) {
        super(handler, x, y, Vatthe.DEFAULT_CREATURE_WIDTH, Vatthe.DEFAULT_CREATURE_HEIGHT);
        level = this.handler.getGame().getLevel();
        this.owner = owner;
        X=x;
        Y=y;
        if(this.owner instanceof Nguoichoi){
            speed = BULLET_SPEED[0];

        } else{
            speed = BULLET_SPEED[level];
       
        } 
        
        setMove();
        // Create bound to check collision
        bounds.x = 3;
        bounds.y = 3;
        bounds.width = width / 4;
        bounds.height = height / 4;
    }
    
    @Override
    public void tick() {
        move();
        checkAttacks();
    }
    
    @Override
    public void render(Graphics g) {
        g.drawImage(Image.bullet, (int) (x), 
                (int) (y), width / 4, height / 4, null);
        
    }
    
    public void checkAttacks() {
        Rectangle cb = getCollisionBounds(0, 0);
        if(this.owner instanceof Quaivat) {
            for(Entity e : handler.getBando().getEntityManager().getEntities()) {
                if(e.getCollisionBounds(0, 0).intersects(cb)){
                    if(e.equals(handler.getBando().getEntityManager().getPlayer())) {
                        e.hurt(1);
                        this.setActive(false);
                        return;
                    }
                }
            }
        }else if(this.owner instanceof Nguoichoi) {
            for(Entity e : handler.getBando().getEntityManager().getEntities()) {
                if(e.equals(this) || e.equals(this.owner))
                    continue;
                if(e.getCollisionBounds(0, 0).intersects(cb)){
                        e.hurt(1);
                        this.setActive(false);
                        return;
                    }
                }
            }
        }

    @Override
    public void die()  {
        
    }
    
    @Override
    public void move() {
        moveX();
        moveY();
    }
    
    public void setMove() {
        // Kiem tra hướng cua player de khoi tao xMove va yMove
        
        if(owner.isRight()){
            xMove = speed;
            yMove = 0;
        } else if(owner.isLeft()){
            xMove = -speed;
            yMove = 0;
        }
        else if(owner.isUp()){
            yMove = -speed;
            xMove = 0;
        }
        else if(owner.isDown()){
            yMove = speed;
            xMove = 0;
        }
    }
    
    @Override
    public void moveX(){
    	if(Math.abs(X-x)>150) {
    		active=false;
    	}
    	else if(xMove > 0){ //Tiếp tục di chuyển sang phải nếu không chạm vào tile 
            int tx = (int) (x + xMove + bounds.x + bounds.width) / Diahinh.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Diahinh.TILEHEIGHT) &&
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Diahinh.TILEHEIGHT)){
                x += xMove;
            } else{ //Di chuyển đến sát bound của tile và biến mất 
                x = tx * Diahinh.TILEWIDTH - bounds.x - bounds.width + 1;
                active = false;
            }
        } else if (xMove < 0) { //Moving left
            int tx = (int) (x + xMove + bounds.x) / Diahinh.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + bounds.y) / Diahinh.TILEHEIGHT) &&
               !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Diahinh.TILEHEIGHT)){
                x += xMove;
            } else {
                x = tx * Diahinh.TILEWIDTH + Diahinh.TILEWIDTH - bounds.x - 1;
                active = false;
            }
        }
    }

    @Override
    public void moveY(){
    	if(Math.abs(Y-y)>150) {
    		active=false;
    	}
    	else if(yMove < 0) {
            int ty = (int) (y + yMove + bounds.y) / Diahinh.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x + bounds.x) / Diahinh.TILEWIDTH, ty)  &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Diahinh.TILEWIDTH, ty)){
                y += yMove;
            } else {
                y = ty * Diahinh.TILEHEIGHT + Diahinh.TILEHEIGHT - bounds.y + 1;
                active = false;

            }
        } else if(yMove > 0){
            int ty = (int) (y + yMove + bounds.y + bounds.height) / Diahinh.TILEHEIGHT;
            
            if(!collisionWithTile((int) (x + bounds.x) / Diahinh.TILEWIDTH, ty)  &&
               !collisionWithTile((int) (x + bounds.x + bounds.width) / Diahinh.TILEWIDTH, ty)){
                y += yMove;
            } else {
                y = ty * Diahinh.TILEHEIGHT - bounds.y - bounds.height - 1;
                active = false;

            }
        }
    }
    
    @Override
    public void setLevel(int level) {
        speed = BULLET_SPEED[level];
        this.level = level;
    }
}

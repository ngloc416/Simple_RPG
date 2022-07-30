package bando;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Diahinh {
    
    public static Diahinh[] tile = new Diahinh[6];
    public static Diahinh co = new Co(0);
    public static Diahinh dat = new Dat(1);
    public static Diahinh da = new Da(2);
    public static Diahinh hoa = new Hoa(3);
    public static Diahinh cay = new Cay(4);
    public static Diahinh nuoc = new Nuoc(5);

    public static final int TILEWIDTH = 32, TILEHEIGHT = 32;
    
    protected BufferedImage texture;
    protected int id;
    protected boolean solid;
    
    public Diahinh(BufferedImage texture, int id) {
        this.texture = texture;
        this.id = id;
        tile[id] = this;
    }
    
    public void tick() {  
    }
    
    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }
    
    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }
    
    public int getId() {
        return id;
    }

}

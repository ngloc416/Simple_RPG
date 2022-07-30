package trangthai;

import java.awt.Graphics;
import game.Handler;
import nhanvat.Nguoichoi;
import bando.TaoBanDo;

public class GameState extends State {
    
    private TaoBanDo bando;
    private Nguoichoi player;

    public GameState(Handler handler, Nguoichoi player, String path) {
        super(handler);
        this.player = player;
        bando = new TaoBanDo(handler, player, path);
        handler.setBando(bando);
    }

    @Override
    public void tick() {
        bando.tick();
        
    }
    
    @Override
    public void render(Graphics g) {
        bando.render(g);
        
    }

    public Nguoichoi getPlayer() {
        return player;
    }
    
    public TaoBanDo getBando() {
		return bando;
    }

    public void setBando(TaoBanDo bando) {
        this.bando = bando;
    }   

    
}

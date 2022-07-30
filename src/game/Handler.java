package game;

import dieukhien.KeyManager;
import dieukhien.MouseManager;
import bando.TaoBanDo;

public class Handler {
    
    private Game game;
    private TaoBanDo bando;
    
    public Handler(Game game) {
        this.game = game;
    }
    
    public int getWidth() {
        return game.getWidth();
    }
    
    public int getHeight() {
        return game.getHeight();
    }

    public void setBando(TaoBanDo bando) {
        this.bando = bando;
    }

    public TaoBanDo getBando() {
        return bando;
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }
    
    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    public Game getGame() {
        return game;
    }
    
}

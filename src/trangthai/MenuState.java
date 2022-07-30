package trangthai;

import java.awt.Graphics;
import java.io.IOException;

import game.Handler;
import hieuung.Image;
import dieukhien.UIImageButton;
import dieukhien.UIManager;

public class MenuState extends State {

    private UIManager uiManager;
    private ChooseState c;
    private HowToPlayState h;
    
    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);
        
        uiManager.addObject(new UIImageButton(98, 390, 200, 100, Image.start_button, () -> {
            c = new ChooseState(handler);
        }));
        
        uiManager.addObject(new UIImageButton(396, 390, 200, 100, Image.how_to_play_button, () ->{ 
            try {
		h=new HowToPlayState(handler);
            } catch (IOException e) {
            }
        }));
        
        uiManager.addObject(new UIImageButton(694, 390, 200, 100, Image.quit_button, () ->{ 
            System.exit(0);
        }));
    }

    @Override
    public void tick() {
        uiManager.tick();        
       
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Image.bg,0,0,992,576,null);
        uiManager.render(g);
    }

    public UIManager getUiManager() {
        return uiManager;
    }
    
    
    
}


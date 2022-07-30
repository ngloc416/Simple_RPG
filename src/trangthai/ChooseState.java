package trangthai;

import java.awt.event.ActionEvent;
import java.util.Iterator;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import game.Handler;
import nhanvat.Entity;

public class ChooseState extends JFrame{
    private Handler handler;  
    private JRadioButton rb1,rb2;    
    private JButton play;  
    public ChooseState(Handler handler) {
        this.handler = handler;        
        final JLabel lv = new JLabel("Choose your level:");  
        lv.setBounds(75, 50, 150, 20); 
        rb1 = new JRadioButton("Easy");    
        rb1.setBounds(100,100,100,30); 
        rb1.addActionListener((ActionEvent e) -> {
            if(rb1.isSelected()){
                this.handler.getGame().setLevel(0);
                Iterator<Entity> it = this.handler.getBando().getEntityManager().getEntities().iterator();
                while(it.hasNext()){
                    Entity entity = it.next();
                    entity.setLevel(0);
                }
            }
        });
        
        rb2 = new JRadioButton("Hard");    
        rb2.setBounds(100,150,100,30);
        rb2.addActionListener((ActionEvent e) -> {
            if(rb2.isSelected()){
                this.handler.getGame().setLevel(1);
                Iterator<Entity> it = this.handler.getBando().getEntityManager().getEntities().iterator();
                while(it.hasNext()){
                    Entity entity = it.next();
                    entity.setLevel(1);
                }
            }
        });
        
        ButtonGroup bg = new ButtonGroup();    
        bg.add(rb1);bg.add(rb2);    
        play = new JButton("Play");    
        play.setBounds(100,200,80,30);    
        play.addActionListener((ActionEvent e) -> {
            handler.getMouseManager().setUiManager(null);
            State.setState(handler.getGame().gameState);
            dispose();  
        });
        
        add(rb1);
        add(rb2);
        add(play);   
        add(lv);
        setSize(300,300);    
        setLayout(null); 
        setLocationRelativeTo(null);
        setVisible(true);    
    }    

    
    
}

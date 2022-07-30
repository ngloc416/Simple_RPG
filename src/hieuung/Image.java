package hieuung;

import java.awt.image.BufferedImage;

public class Image {
    private static final int width = 32, height = 32;
    public static BufferedImage dirt, grass, stone, tree, flower, water, bullet, bg; 
    public static BufferedImage[] player_down, player_up, player_left, player_right;
    public static BufferedImage[] zombie_down, zombie_up, zombie_left, zombie_right;
    public static BufferedImage[] zombie1_down, zombie1_up, zombie1_left, zombie1_right;
    public static BufferedImage start_button, how_to_play_button, quit_button;
    
    public static void init() {
        BufferedImage sheet = ImageLoader.loadImage("data/sheet.png");
        BufferedImage floor = ImageLoader.loadImage("data/floortileset.png");

        start_button = ImageLoader.loadImage("data/playbutton.png");
        how_to_play_button = ImageLoader.loadImage("data/guidebutton.png");
        quit_button = ImageLoader.loadImage("data/exitbutton.png");
        bg = ImageLoader.loadImage("data/background.jpg");

        player_down = new BufferedImage[2];
        player_up = new BufferedImage[2];
        player_left = new BufferedImage[2];
        player_right = new BufferedImage[2];

        player_down[0] = sheet.getSubimage(0, 0, width, height);
        player_down[1] = sheet.getSubimage(width , 0, width, height);
        player_up[0] = sheet.getSubimage(width * 2, 0, width, height);
        player_up[1] = sheet.getSubimage(width * 3, 0, width, height);
        player_left[0] = sheet.getSubimage(width * 2, height, width, height);
        player_left[1] = sheet.getSubimage(width * 3, height, width, height);
        player_right[0] = sheet.getSubimage(width, height, width, height);
        player_right[1] = sheet.getSubimage(0, height, width, height);
        
        
        zombie_down = new BufferedImage[2];
        zombie_up = new BufferedImage[2];
	zombie_left = new BufferedImage[2];
	zombie_right = new BufferedImage[2];
	
        zombie_down[0] = sheet.getSubimage(0, height * 2, width, height);
	zombie_down[1] = sheet.getSubimage(width , height * 2, width, height);
        zombie_up[0] = sheet.getSubimage(width * 2, height * 2, width, height);
	zombie_up[1] = sheet.getSubimage(width * 3, height * 2, width, height);
	zombie_right[0] = sheet.getSubimage(0, height * 3, width, height);
	zombie_right[1] = sheet.getSubimage(width, height * 3, width, height);
	zombie_left[0] = sheet.getSubimage(width * 2, height * 3, width, height);
	zombie_left[1] = sheet.getSubimage(width * 3, height * 3, width, height);
		
		
	zombie1_down = new BufferedImage[2];
	zombie1_up = new BufferedImage[2];
	zombie1_left = new BufferedImage[2];
	zombie1_right = new BufferedImage[2];
		
	zombie1_down[0] = sheet.getSubimage(0, height * 5, width, height);
	zombie1_down[1] = sheet.getSubimage(width , height * 5, width, height);
        zombie1_up[0] = sheet.getSubimage(width * 2, height * 5, width, height);
	zombie1_up[1] = sheet.getSubimage(width * 3, height * 5, width, height);
	zombie1_right[0] = sheet.getSubimage(0, height * 6, width, height);
	zombie1_right[1] = sheet.getSubimage(width, height * 6, width, height);
	zombie1_left[0] = sheet.getSubimage(width * 2, height * 6, width, height);
	zombie1_left[1] = sheet.getSubimage(width * 3, height * 6, width, height);
	
	tree = floor.getSubimage(width * 4, 0, width, height);
        dirt = floor.getSubimage(width*3, 0, width, height);
        grass = floor.getSubimage(width, 0, width, height);
        stone = floor.getSubimage(0, 0, width, height);
        flower = floor.getSubimage(width*5, 0 , width , height);
        water = floor.getSubimage(width * 2, 0, width, height);
        bullet = sheet.getSubimage(0, height *4, width, height);
    }
}

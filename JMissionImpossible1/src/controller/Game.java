package controller;

import java.awt.Graphics;

import view.GamePanel;
import view.GameWindow;
import view.Gamestate;
import view.Menu;
import view.Playing;

public class Game implements Runnable {
    private Thread gameThread;
    private final GamePanel gamePanel;
    private final GameWindow gameWindow;
    private final int FPS = 60; // cambia qui il target

    private Playing playing;
    private Menu menu;
    
    public final static int Tile_Size = 32;
    public final static float Scale = 1.0f;
    public final static int Tiles_Width = 26;
    public final static int Tiles_Height = 14;
    public final static int Tiles_Final_Size = (int) (Tile_Size * Scale);
    public final static int Game_Width = Tiles_Final_Size * Tiles_Width;
    public final static int Game_Height = Tiles_Final_Size * Tiles_Height;
    
    
    public Game() {
    	initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);

        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow(); // dopo che la window è visibile

        
        
        start();
       
    }

    public void update() {
    	
    	switch(Gamestate.state) {
		case MENU:
			menu.update();
			break;
		case PLAYING:
			playing.update();
			break;
		default:
			break;
    	
    	}
    }
    
    private void initClasses() {
    	menu = new Menu(this);
    	playing = new Playing(this);
	}
    
    public void render(Graphics g) {
    	
    	switch(Gamestate.state) {
		case MENU:
			menu.draw(g);;
			break;
		case PLAYING:
			playing.draw(g);
			break;
		default:
			break;
    	
    	}
    	
    }
    
    
	private void start() {
        gameThread = new Thread(this, "GameLoop");
        gameThread.setDaemon(true);
        gameThread.start();
    }

    @Override
    public void run() {
        final long timePerFrame = 1_000_000_000L / FPS; // 120 -> 8.333... ms
        long lastFrame = System.nanoTime();

        long now = System.nanoTime();
        
        long lastFpsTs = System.currentTimeMillis();
        while (true) {
        	now = System.nanoTime();
            if(now - lastFrame >= timePerFrame) {
            	update();
            	gamePanel.repaint();
            	lastFrame = now;
            	
            }
 
		   
		    if (System.currentTimeMillis() - lastFpsTs >= 1000) {
		        
		        lastFpsTs =System.currentTimeMillis();
		    }
        }
    }
    

    public void windowFocusLost() {
    	if(Gamestate.state == Gamestate.PLAYING) {
    		playing.getPlayer().resetBooleans();
    	}
    }
    
    public Menu getMenu() {
    	return menu;
    }
    
    public Playing getPlaying() {
    	return playing;
    }
}

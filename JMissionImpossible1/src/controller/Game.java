package controller;

import java.awt.Graphics;

import model.LevelManager;
import model.Player;
import view.GamePanel;
import view.GameWindow;

public class Game implements Runnable {
    private Thread gameThread;
    private final GamePanel gamePanel;
    private final GameWindow gameWindow;
    private final int FPS = 60; // cambia qui il target

    public final static int Tile_Size = 32;
    public final static float Scale = 1.0f;
    public final static int Tiles_Width = 26;
    public final static int Tiles_Height = 14;
    public final static int Tiles_Final_Size = (int) (Tile_Size * Scale);
    public final static int Game_Width = Tiles_Final_Size * Tiles_Width;
    public final static int Game_Height = Tiles_Final_Size * Tiles_Height;
    
    private Player player;
    private LevelManager levelManager;
    public Game() {
    	initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);

        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow(); // dopo che la window è visibile

        
        
        start();
       
    }

    public void update() {
    	player.update();
    	levelManager.update();
    }
    
    private void initClasses() {
    	levelManager = new LevelManager(this);
		player = new Player(64.0f,386.0f);
		player.loadLvData(levelManager.getCurrentLevel().getLevelData());
	}
    public void render(Graphics g) {
    	levelManager.draw(g);
    	player.render(g);
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
    
    public Player getPlayer() {
    	return player;
    }
    
    public void windowFocusLost() {
    	player.resetBooleans();
    }
}

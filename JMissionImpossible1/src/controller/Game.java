package controller;

import view.GamePanel;
import view.GameWindow;

public class Game implements Runnable {
    private Thread gameThread;
    private final GamePanel gamePanel;
    private final GameWindow gameWindow;
    private final int FPS = 60; // cambia qui il target

    public Game() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);

        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow(); // dopo che la window è visibile

        start();
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
            	gamePanel.repaint();
            	lastFrame = now;
            	
            }
 
		   
		    if (System.currentTimeMillis() - lastFpsTs >= 1000) {
		        
		        lastFpsTs =System.currentTimeMillis();
		    }
        }
    }
}

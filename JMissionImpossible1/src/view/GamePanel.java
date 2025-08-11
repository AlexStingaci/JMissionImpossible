package view;

import java.awt.Graphics;


import javax.swing.JPanel;
import controller.KeyboardInput;

public class GamePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int xDelta = 0, yDelta = 0;
	private int xDir = 1, yDir = 1;
	private int paintsThisSecond = 0;
	private long lastFpsTs = System.nanoTime();
	public GamePanel() {
		addKeyListener(new KeyboardInput(this));
		
	}
	
	public void changeX(int value) {
		this.xDelta += value;
		
	}
	public void changeY(int value) {
		this.yDelta += value;
	
	}

	public void tick() {
	    
	    updateRectangle();
	   
	}

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    
	    updateRectangle();
		g.fillRect(xDelta, yDelta, 200, 50);
		
	    paintsThisSecond++;
	    long now = System.nanoTime();
	    if (now - lastFpsTs >= 1_000_000_000L) {
	        System.out.println("Render FPS: " + paintsThisSecond);
	        paintsThisSecond = 0;
	        lastFpsTs += 1_000_000_000L;
	    }
	    
	}
	private void updateRectangle() {
		xDelta += xDir;
		if(xDelta > 400 || xDelta < 0) {
			xDir*=-1;
		}
		yDelta += yDir;
		if(yDelta > 400 || yDelta < 0) {
			yDir*=-1;
		}

	}
}

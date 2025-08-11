package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.GamePanel;
import static utilities.Costants.Directions.*;
public class KeyboardInput implements KeyListener{
	private GamePanel gamePanel;
	public KeyboardInput(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			break;
		case KeyEvent.VK_A:
			gamePanel.setDirection(LEFT);
			break;
		case KeyEvent.VK_S:
			break;
		case KeyEvent.VK_D:
			gamePanel.setDirection(RIGHT);
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			break;
		case KeyEvent.VK_A:
			gamePanel.setMoving(false);
			break;
		case KeyEvent.VK_S:
			break;
		case KeyEvent.VK_D:
			gamePanel.setMoving(false);
			break;
		}
		
	}
	
}

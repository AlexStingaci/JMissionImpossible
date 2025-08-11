package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.GamePanel;

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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			gamePanel.changeY(-5);
			break;
		case KeyEvent.VK_A:
			gamePanel.changeX(-5);
			break;
		case KeyEvent.VK_S:
			gamePanel.changeY(+5);
			break;
		case KeyEvent.VK_D:
			gamePanel.changeX(+5);
			break;
		}
		
	}
	
}

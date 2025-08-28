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
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			break;
		case KeyEvent.VK_A:
			gamePanel.getGame().getPlayer().setLeft(true);
			break;
		case KeyEvent.VK_S:
			break;
		case KeyEvent.VK_D:
			gamePanel.getGame().getPlayer().setRight(true);
			break;
		case KeyEvent.VK_SPACE:
			gamePanel.getGame().getPlayer().setJump(true);
			break;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			break;
		case KeyEvent.VK_A:
			gamePanel.getGame().getPlayer().setLeft(false);
			break;
		case KeyEvent.VK_S:
			break;
		case KeyEvent.VK_D:
			gamePanel.getGame().getPlayer().setRight(false);
			break;
		case KeyEvent.VK_SPACE:
			gamePanel.getGame().getPlayer().setJump(false);
			break;
		}
		
	}
	
}

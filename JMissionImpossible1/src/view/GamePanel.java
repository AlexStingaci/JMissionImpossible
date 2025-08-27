package view;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import controller.Game;
import controller.KeyboardInput;

import static controller.Game.Game_Height;
import static controller.Game.Game_Width;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Game game;
	public GamePanel(Game game) {
		this.game = game;
		addKeyListener(new KeyboardInput(this));
		setPanelSize();
		
	}
	
	private void setPanelSize() {
		Dimension size = new Dimension(Game_Width,Game_Height);
		setPreferredSize(size);

		
	}

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    
	    game.render(g);
	}
	
	public Game getGame() {
		return game;
	}


}

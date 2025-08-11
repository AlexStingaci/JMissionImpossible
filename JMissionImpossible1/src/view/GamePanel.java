package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import controller.KeyboardInput;

import static utilities.Costants.PlayerCostants.*;
import static utilities.Costants.Directions.*;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int x = 0, y = 0;
	private int aniTick, aniIndex, aniSpeed = 5;
	private BufferedImage[][] animations;
	private int action = IDLE_RIGHT;
	private int playerDirection = -1;
	private boolean moving = false;
	public GamePanel() {

		loadAnimations();
		addKeyListener(new KeyboardInput(this));
		setPanelSize();
		
	}
	
	private void loadAnimations() {

		
		animations = new BufferedImage[9][18];
		
		animations[0][0]  = loadImages("/idle/right1.png");
		animations[1][0]  = loadImages("/idle/left1.png");
		
		animations[2][0]  = loadImages("/search/search.png");
		
		for(int i=0;i<14;i++) {
			animations[3][i] = loadImages("/run/left/left"+(i+1)+".png");
			animations[4][i] = loadImages("/run/right/right"+(i+1)+".png");
		}
		
		for(int i=0;i<18;i++) {
			animations[5][i] = loadImages("/death/left/death"+(i+1)+".png");
			animations[6][i] = loadImages("/death/right/death"+(i+1)+".png");
		}
		
		for(int i=0;i<13;i++) {
			animations[7][i] = loadImages("/jump/left/jump"+(i+1)+".png");
			animations[8][i] = loadImages("/jump/right/jump"+(i+1)+".png");
		}
		
		
			
	}

	private BufferedImage loadImages(String resourcePath) {
	    try (InputStream is = getClass().getResourceAsStream(resourcePath)) {
	        if (is == null) throw new IllegalArgumentException("Risorsa non trovata: " + resourcePath);
	        return ImageIO.read(is);
	    } catch (Exception e) {
	        throw new RuntimeException("Errore nel caricare " + resourcePath, e);
	    }
	}


	private void setPanelSize() {
		Dimension size = new Dimension(1280,800);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		
	}
	public void setDirection(int direction) {
		this.playerDirection = direction;
		moving = true;
	}
	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    
	    updateAnimationTick();
	    setAnimation();
	    updatePos();
	    g.drawImage(animations[action][aniIndex],x,y,32,32,null);
	}

	private void updatePos() {
		
		if(moving) {
			switch(playerDirection) {
			case LEFT:
				x -=5;
				break;
			case RIGHT:
				x +=5;
				break;
			}
		}
		
	}

	private void setAnimation() {
		
		if(moving) {
			if(playerDirection == LEFT) {
				action = RUN_LEFT;
			}else {
				action = RUN_RIGHT;
			}
		}
		else {
			if(playerDirection == LEFT) {
				action = IDLE_LEFT;
			}else {
				action = IDLE_RIGHT;
			}
		}
		
	}

	private void updateAnimationTick() {
		aniTick++;
		if(aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if(aniIndex >= GetSpriteAction(action))
				aniIndex = 0;
		}
		
	}
}

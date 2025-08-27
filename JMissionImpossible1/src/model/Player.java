package model;

import static utilities.Costants.Directions.LEFT;
import static utilities.Costants.Directions.RIGHT;
import static utilities.Costants.PlayerCostants.GetSpriteAction;
import static utilities.Costants.PlayerCostants.IDLE_LEFT;
import static utilities.Costants.PlayerCostants.IDLE_RIGHT;
import static utilities.Costants.PlayerCostants.RUN_LEFT;
import static utilities.Costants.PlayerCostants.RUN_RIGHT;
import static utilities.HelpMethods.CanMoveHere;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import controller.Game;
import utilities.LoadSave;

public class Player extends Entity{
	
	private int action = IDLE_RIGHT;
	private boolean left, right;
	private boolean moving = false;
	private int aniTick, aniIndex, aniSpeed = 5;
	private BufferedImage[][] animations;
	private int playerDirection = RIGHT;
	private int[][] lvData;
	private float xDrawOffset = 3 * Game.Scale;
	private float yDrawOffset = 2 * Game.Scale;

	public Player(float x, float y) { 
		super(x,y);
		loadAnimations();
		initHitbox(x, y,18*Game.Scale,25*Game.Scale);
	}

	

	public void update() {
		updatePos();
		updateAnimationTick();
	    setAnimation();
	}
	public void render(Graphics g) {
		 g.drawImage(animations[action][aniIndex],(int) (hitBox.x- xDrawOffset),(int)  (hitBox.y - yDrawOffset),Game.Tiles_Final_Size,Game.Tiles_Final_Size,null);
		 drawHitbox(g);
	}
	
	private void loadAnimations() {

		animations = new BufferedImage[9][18];
		
		animations[0][0]  = LoadSave.loadImages("/idle/right1.png");
		animations[1][0]  = LoadSave.loadImages("/idle/left1.png");
		
		animations[2][0]  = LoadSave.loadImages("/search/search.png");
		
		for(int i=0;i<14;i++) {
			animations[3][i] = LoadSave.loadImages("/run/left/left"+(i+1)+".png");
			animations[4][i] = LoadSave.loadImages("/run/right/right"+(i+1)+".png");
		}
		
		for(int i=0;i<18;i++) {
			animations[5][i] = LoadSave.loadImages("/death/left/death"+(i+1)+".png");
			animations[6][i] = LoadSave.loadImages("/death/right/death"+(i+1)+".png");
		}
		
		for(int i=0;i<13;i++) {
			animations[7][i] = LoadSave.loadImages("/jump/left/jump"+(i+1)+".png");
			animations[8][i] = LoadSave.loadImages("/jump/right/jump"+(i+1)+".png");
		}
		
			
	}
	
	public void loadLvData(int [][]lvData) {
		this.lvData = lvData;
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
	
	private void updatePos() {
		moving = false;
		
		if(!left&& !right) {
			return;
		}
		
		float xSpeed = 0, ySpeed = 0;
		
		if(left && !right) {
			xSpeed -= 5.0f;
			playerDirection = LEFT;

		}else if(right && !left) {
			xSpeed+= 5.0f;
			playerDirection = RIGHT;
			
		}
		
		if(CanMoveHere(hitBox.x+xSpeed,hitBox.y+ySpeed,hitBox.width,hitBox.height,lvData)){
			hitBox.x += xSpeed;
			hitBox.y += ySpeed;
			moving = true;
		}
		
	}
	public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public void resetBooleans() {
		left = false;
		right = false;
	}
	
}

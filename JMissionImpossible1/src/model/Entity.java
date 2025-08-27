package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;


public abstract class Entity {
	protected float x,y;
	protected Rectangle2D.Float hitBox;
	public Entity(float x,float y) {
		this.x = x;
		this.y = y;
	}
	
	protected  void initHitbox(float x, float y, float width, float height) {
		hitBox = new Rectangle2D.Float(x,y,width,height);
	}
	

	protected void drawHitbox(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect((int)hitBox.x,(int)hitBox.y,(int)hitBox.width,(int)hitBox.height);
	}
	
	public Rectangle2D.Float getHitbox() {
		return hitBox;
	}
	
	
}

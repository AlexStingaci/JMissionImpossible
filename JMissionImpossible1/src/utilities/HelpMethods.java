package utilities;

import java.awt.geom.Rectangle2D;

import controller.Game;

public class HelpMethods {
	
	public static boolean CanMoveHere(float x,float y,float width,float height, int[][] lvData) {
		
		if(!isSolid(x,y,lvData)) {
			if(!isSolid(x+width,y+height,lvData)) {
				if(!isSolid(x+width,y,lvData)) {
					if(!isSolid(x,y+height,lvData)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private static boolean isSolid(float x, float y, int[][] lvData) {
		
		if(x < 0 || x >= Game.Game_Width) return true;
		
		if(y < 0 || y >= Game.Game_Height) return true;
		
		float xIndex = x / Game.Tiles_Final_Size;
		float yIndex = y / Game.Tiles_Final_Size;
		
		int value = lvData[(int) yIndex][(int) xIndex];
		
		if(value != 0) return true;
		
		return false;
	}
	
	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitBox, float xSpeed) {
		
		int currentTile = (int) (hitBox.x /Game.Tiles_Final_Size);
		if(xSpeed > 0 ) {
			int tileXPos = currentTile * Game.Tiles_Final_Size;
			int xOffset = (int)(Game.Tiles_Final_Size - hitBox.width);
			return tileXPos + xOffset - 1;
		}else {
			return currentTile * Game.Tiles_Final_Size;
		}
	}
	
	public static float GetEntityYPosUnderRoofOrFloor(Rectangle2D.Float hitBox, float airSpeed) {
		int currentTile = (int) (hitBox.y /Game.Tiles_Final_Size);
		if(airSpeed > 0 ) {
			int tileYPos = currentTile * Game.Tiles_Final_Size;
			int yOffset = (int)(Game.Tiles_Final_Size - hitBox.height);
			return tileYPos + yOffset - 1;
		}else {
			return currentTile * Game.Tiles_Final_Size;
		}
	}
	
	public static boolean IsEntityOnFloor(Rectangle2D.Float hitBox, int[][] lvData) {
		
		if(!isSolid(hitBox.x,hitBox.y+hitBox.height+1,lvData))
			if(!isSolid(hitBox.x+hitBox.width,hitBox.y+hitBox.height+1,lvData))
				return false;
		return true;
	}
	
	
}

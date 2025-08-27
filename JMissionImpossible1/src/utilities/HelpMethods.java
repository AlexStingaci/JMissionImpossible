package utilities;

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
	
}

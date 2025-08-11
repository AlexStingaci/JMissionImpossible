package utilities;

public class Costants {
	public static class Directions{
		public static final int LEFT = 0;
		public static final int RIGHT = 1;
	}
	public static class PlayerCostants{
		public static final int IDLE_RIGHT = 0;
		public static final int IDLE_LEFT = 1;
		public static final int SEARCH = 2;
		public static final int RUN_LEFT = 3;
		public static final int RUN_RIGHT = 4;
		public static final int DEATH_LEFT = 5;
		public static final int DEATH_RIGHT = 6;
		public static final int JUMP_LEFT = 7;
		public static final int JUMP_RIGHT = 8;
		
		public static int GetSpriteAction(int action) {
			switch(action) {
			case RUN_LEFT:
				return 14;
			case RUN_RIGHT:
				return 14;
			case DEATH_LEFT:
				return 18;
			case DEATH_RIGHT:
				return 18;
			case JUMP_LEFT:
				return 13;
			case JUMP_RIGHT:
				return 13;
			default:
				return 1;
			}
		}
	}
}

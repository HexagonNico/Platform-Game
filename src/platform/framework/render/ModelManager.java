package platform.framework.render;

import java.util.HashMap;

public class ModelManager {

	private static final HashMap<String, Model> models = new HashMap<>();
	
	public static final String PLAYER = "player";
	public static final String BLOCK = "block";
	public static final String ARROW = "arrow";
	public static final String PLAYER_JUMP = "player_jump";
	public static final String PLAYER_FALL = "player_fall";
	public static final String PLAYER_FALL_LONG = "player_fall_long";
	public static final String PLAYER_WALK1 = "player_walk1";
	public static final String PLAYER_WALK2 = "player_walk2";
	public static final String PLAYER_DEATH1 = "player_death1";
	public static final String PLAYER_DEATH2 = "player_death2";
	public static final String ENEMY1 = "enemy1";
	public static final String ENEMY1_WALK1 = "enemy1_walk1";
	public static final String ENEMY1_WALK2 = "enemy1_walk2";
	public static final String SPIKES_UP = "spikes_up";
	public static final String SPIKES_LEFT = "spikes_left";
	public static final String SPIKES_DOWN = "spikes_down";
	public static final String SPIKES_RIGHT = "spikes_right";
	public static final String EXIT_DOOR = "exit_door";
	public static final String LIFE = "life";
	public static final String E = "letter_e";
	public static final String X = "letter_x";
	public static final String T = "letter_t";
	public static final String N = "letter_n";
	public static final String D = "letter_d";
	public static final String GRAPE = "grape";
	public static final String MELON = "melon";
	public static final String STRAWBERRY = "strawberry";
	public static final String APPLE = "apple";
	
	public static void init() {
		System.out.println("[Resources][ModelManager]: Initialization...");
		
		models.put(PLAYER, new Model(60, 60, PLAYER));
		models.put(BLOCK, new Model(40, 40, BLOCK));
		models.put(ARROW, new Model(30, 30, ARROW));
		models.put(PLAYER_JUMP, new Model(60, 60, PLAYER_JUMP));
		models.put(PLAYER_FALL, new Model(60, 60, PLAYER_FALL));
		models.put(PLAYER_FALL_LONG, new Model(60, 60, PLAYER_FALL_LONG));
		models.put(PLAYER_WALK1, new Model(60, 60, PLAYER_WALK1));
		models.put(PLAYER_WALK2, new Model(60, 60, PLAYER_WALK2));
		models.put(PLAYER_DEATH1, new Model(60, 60, PLAYER_DEATH1));
		models.put(PLAYER_DEATH2, new Model(60, 60, PLAYER_DEATH2));
		models.put(ENEMY1, new Model(60, 60, ENEMY1));
		models.put(ENEMY1_WALK1, new Model(60, 60, ENEMY1_WALK1));
		models.put(ENEMY1_WALK2, new Model(60, 60, ENEMY1_WALK2));
		models.put(SPIKES_UP, new Model(40, 40, SPIKES_UP));
		models.put(SPIKES_LEFT, new Model(40, 40, SPIKES_LEFT));
		models.put(SPIKES_DOWN, new Model(40, 40, SPIKES_DOWN));
		models.put(SPIKES_RIGHT, new Model(40, 40, SPIKES_RIGHT));
		models.put(EXIT_DOOR, new Model(60, 60, EXIT_DOOR));
		models.put(LIFE, new Model(30, 30, LIFE));
		models.put(E, new Model(30, 30, E));
		models.put(X, new Model(30, 30, X));
		models.put(T, new Model(30, 30, T));
		models.put(N, new Model(30, 30, N));
		models.put(D, new Model(30, 30, D));
		models.put(GRAPE, new Model(40, 40, GRAPE));
		models.put(MELON, new Model(40, 40, MELON));
		models.put(STRAWBERRY, new Model(40, 40, STRAWBERRY));
		models.put(APPLE, new Model(40, 40, APPLE));
	}
	
	public static Model model(String key) {
		return models.get(key);
	}
}

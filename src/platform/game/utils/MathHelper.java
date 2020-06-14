package platform.game.utils;

import java.util.Random;

public class MathHelper {

	public static int randomInt(int upperBound) {
		Random rand = new Random();
		return rand.nextInt(upperBound);
	}
}

package platform.game.entities;

import platform.framework.render.ModelManager;
import platform.game.utils.Direction;

public class Projectile extends Entity {

	private int lifetime;
	
	/**Creates a projectile
	 * @param posX - Starting pos X
	 * @param posY - Starting pos Y
	 * @param facing - Direction where the player is moving
	 */
	public Projectile(int posX, int posY, Direction facing) {
		super(posX, posY, ModelManager.model(ModelManager.ARROW));
		this.facing = facing;
		this.lifetime = 0;
		this.fallSpeed = fallSpeed/2;
	}
	
	@Override
	public void move() {
		if(this.facing == Direction.RIGHT) {
			this.posX += 10;
		} else {
			this.posX -= 10;
		}
		this.lifetime++;
	}
	
	/**Checks if this projectile has been on the screen for more than 50 ticks*/
	public boolean shouldDespawn() {
		return this.lifetime > 50;
	}
	
	/**Sets lifetime to 50 ticks so that projectile can be despawned*/
	public void setShouldDespawn() {
		this.lifetime = 50;
	}
}

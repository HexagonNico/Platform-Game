package platform.game.entities;

import platform.framework.gamestates.GameStateManager;
import platform.framework.render.Animation;
import platform.framework.render.ModelManager;
import platform.game.utils.Direction;

public final class Player extends Entity {

	private int shotDelay;
	
	private int deathTime;
	
	private Animation walking;
	private Animation death;
	
	public Player(int posX, int posY) {
		super(posX, posY, ModelManager.model(ModelManager.PLAYER));
		
		this.shotDelay = 0;
		
		this.deathTime = 0;
		
		this.walking = new Animation(10, ModelManager.model(ModelManager.PLAYER_WALK1), ModelManager.model(ModelManager.PLAYER), ModelManager.model(ModelManager.PLAYER_WALK2), ModelManager.model(ModelManager.PLAYER));
		this.death = new Animation(10, ModelManager.model(ModelManager.PLAYER_DEATH1), ModelManager.model(ModelManager.PLAYER_DEATH2));
	}
	
	/**Creates a projectile at certain poition depending on player's facing*/
	public Projectile shotArrow() {
		if(shotDelay < 0) {
			this.shotDelay = 30;
			if(this.facing == Direction.RIGHT) {
				return new Projectile(this.posX + this.getModel().width +2, this.posY + this.getModel().height/2, facing);
			} else {
				return new Projectile(this.posX - ModelManager.model(ModelManager.ARROW).width -2, this.posY + this.getModel().height/2, facing);
			}
		}
		return null;
	}
	
	@Override
	public void move() {
		if(!this.dead)
			super.move();
		
		this.shotDelay--;
		if(this.shotDelay == -30) {
			this.shotDelay = -1;
		}
		
		if(this.dead) {
			this.deathTime++;
		}
		
		changeAnimations();
	}
	
	public boolean shouldRespawn() {
		return deathTime >= 75;
	}
	
	private void changeAnimations() {
		if(this.fallSpeed < 0 && this.isInAir) {
			this.setModel(ModelManager.model(ModelManager.PLAYER_JUMP));
		}
		else if(this.fallSpeed >= 0 && this.fallSpeed < 20 && this.isInAir) {
			this.setModel(ModelManager.model(ModelManager.PLAYER_FALL));
		}
		else if(this.fallSpeed >= 20 && this.isInAir) {
			this.setModel(ModelManager.model(ModelManager.PLAYER_FALL_LONG));
		}
		else {
			if(this.left || this.right) {
				this.setModel(walking.getCurrentFrame());
			} else {
				this.setModel(ModelManager.model(ModelManager.PLAYER));
			}
		}
		
		if(this.dead) {
			this.setModel(death.getCurrentFrame());
		}
	}
	
	@Override
	public void onEntityCollision(Entity entity, GameStateManager gsm) {
		if(entity instanceof Enemy) {
			this.setDead(true);
		}
	}
}

package platform.game.entities;

import platform.framework.gamestates.GameStateManager;
import platform.framework.render.Animation;
import platform.framework.render.ModelManager;
import platform.game.states.LevelOneState;
import platform.game.utils.Direction;

public class Enemy extends Entity {

	private Animation walking;
	
	public Enemy(int posX, int posY) {
		super(posX, posY, ModelManager.model(ModelManager.ENEMY1));
		this.left = true;
		this.movementSpeed = 2;
		
		this.walking = new Animation(10, ModelManager.model(ModelManager.ENEMY1_WALK1), ModelManager.model(ModelManager.ENEMY1), ModelManager.model(ModelManager.ENEMY1_WALK2), ModelManager.model(ModelManager.ENEMY1));
	}
	
	@Override
	public Direction checkCollision(StaticEntity block, GameStateManager gsm) {
		switch(super.checkCollision(block, gsm)) {
		case LEFT:
			this.left = true;
			this.right = false;
			return Direction.LEFT;
		case RIGHT:
			this.right = true;
			this.left = false;
			return Direction.RIGHT;
		default:
			return null;
		}
	}
	
	@Override
	public void move() {
		super.move();
		if(this.left || this.right) {
			this.setModel(walking.getCurrentFrame());
		}
	}
	
	@Override
	public void onEntityCollision(Entity entity, GameStateManager gsm) {
		if(entity instanceof Projectile) {
			this.setDead(true);
			((Projectile) entity).setShouldDespawn();
			((LevelOneState) gsm.getActiveState()).extraEntities.add(new Fruit(this.posX, this.posY));
		}
	}

}

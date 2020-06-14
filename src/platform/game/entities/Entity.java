package platform.game.entities;

import java.awt.Rectangle;

import platform.framework.gamestates.GameStateManager;
import platform.framework.render.Model;
import platform.game.utils.Direction;

public class Entity extends StaticEntity {

	protected boolean left;
	protected boolean right;
	protected int movementSpeed;
	
	protected boolean isInAir;
	protected int fallSpeed;
	
	protected Direction facing;
	
	protected boolean dead;
	
	/**Creates an entity at given position
	 * @param posX - Position X
	 * @param posY - Position Y
	 */
	public Entity(int posX, int posY, Model model) {
		super(posX, posY, model);
		this.left = false;
		this.right = false;
		this.fallSpeed = 0;
		this.isInAir = true;
		this.facing = Direction.RIGHT;
		this.movementSpeed = 5;
		this.dead = false;
	}
	
	/**Moves the entity of 10 pixels left or right depending by movement booleans<br>
	 * Makes the entity fall because of gravity
	 */
	public void move() {
		if(left) {
			this.posX-=this.movementSpeed;
			this.facing = Direction.LEFT;
		}
		if(right) {
			this.posX+=this.movementSpeed;
			this.facing = Direction.RIGHT;
		}
		
		this.posY+=this.fallSpeed;
		this.fallSpeed++;
	}
	
	/**Sets the left movement boolean*/
	public void setLeft(boolean left) {
		this.left = left;
	}

	/**Sets the right movement boolean*/
	public void setRight(boolean right) {
		this.right = right;
	}
	
	/**Sets fall speed to 10*/
	public void jump() {
		if(!isInAir) {
			this.fallSpeed = -15;
			this.isInAir = true;
		}
	}
	
	/**Checks if this entity is colliding with given StaticEntity<br>
	 * and changes its position<br>
	 * @param block - The StaticEntity to test
	 * @param gsm - GameStateManager needed to change level if the player gets through a door
	 * @return The direction where the collision has happened
	 */
	public Direction checkCollision(StaticEntity block, GameStateManager gsm) {
		Rectangle intersection = this.getModel().intersection(block.getModel());
		
		if(intersection.isEmpty()) {
			return Direction.NULL; //No collision
		}
		
		if(intersection.width > intersection.height) {
			//Above or below
			block.onEntityCollision(this, gsm);
			
			if(this.posY < block.posY) {
				//Above
				this.posY = block.posY - this.getModel().height;
				this.fallSpeed = 0;
				this.isInAir = false;
				return Direction.NULL;
			} else {
				//Below
				this.posY = block.posY + block.getModel().height;
				this.fallSpeed = 0;
				return Direction.NULL;
			}
		} else {
			//Left or right
			block.onEntityCollision(this, gsm);
			
			if(this.posX < block.posX) {
				//Left
				this.posX = block.posX - this.getModel().width;
				return Direction.LEFT;
			} else {
				//Right
				this.posX = block.posX + block.getModel().width;
				return Direction.RIGHT;
			}
		}
	}
	
	public Direction getFacing() {
		return facing;
	}
	
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	public boolean isDead() {
		return dead;
	}
}

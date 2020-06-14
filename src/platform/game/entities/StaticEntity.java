package platform.game.entities;

import platform.framework.gamestates.GameStateManager;
import platform.framework.render.Model;

public class StaticEntity {

	private Model model;
	
	protected int posX;
	protected int posY;
	
	public StaticEntity(int posX, int posY, Model model) {
		this.posX = posX;
		this.posY = posY;
		this.model = model;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public Model getModel() {
		model.x = posX;
		model.y = posY;
		return model;
	}
	
	protected void setModel(Model model) {
		this.model = model;
	}
	
	public void onEntityCollision(Entity entity, GameStateManager gsm) {
		return;
	}
}

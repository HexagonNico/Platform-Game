package platform.game.entities;

import platform.framework.DisplayManager;

public class Camera extends StaticEntity {

	public Camera(Entity player) {
		super(player.posX - DisplayManager.WIDTH/2, player.posY - DisplayManager.HEIGHT/2, null);
	}
	
	public void setPosition(int x, int y) {
		this.posX = x - DisplayManager.WIDTH/2;
		this.posY = y - DisplayManager.HEIGHT/2;
	}
}

package platform.framework.render;

import java.awt.Graphics;

import platform.game.entities.Camera;
import platform.game.entities.Entity;
import platform.game.entities.StaticEntity;
import platform.game.utils.Direction;

public class Renderer {

	/**Renders an entity on screen
	 * @param entity - The entity to render
	 * @param camera - Needed to calculate render position
	 * @param graphics - Graphics object
	 */
	public static void renderEntity(StaticEntity entity, Camera camera, Graphics graphics) {
		int posX = entity.getPosX() - camera.getPosX();
		int posY = entity.getPosY() - camera.getPosY();
		Model model = entity.getModel();
		
		if(entity instanceof Entity) {
			if(((Entity) entity).getFacing() == Direction.LEFT) {
				model = model.flipTexture();
			}
		}
		
		renderModel(model, posX, posY, graphics);
	}
	
	/**Renders a model on screen
	 * @param model - The model to render
	 * @param posX - Model top left corner position
	 * @param posY - Model top left corner position
	 * @param graphics - Graphics object
	 */
	public static void renderModel(Model model, int posX, int posY, Graphics graphics) {
		graphics.drawImage(model.getTexture(), posX, posY, model.width, model.height, null);
	}
}

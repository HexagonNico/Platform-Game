package platform.framework.render;

import java.util.ArrayList;

public class Animation {

	private ArrayList<Model> frames;
	private int delay;
	
	private int currentFrame;
	private int currentDelay;
	
	/**Creates an animation
	 * @param delay - The number of ticks between each frame
	 * @param framesModels - The animation frames
	 */
	public Animation(int delay, Model... framesModels) {
		this.frames = new ArrayList<Model>();
		this.delay = delay;
		this.currentFrame = 0;
		this.currentDelay = 0;
		
		for(Model model : framesModels) {
			this.frames.add(model);
		}
	}
	
	/**Gets the current frame of the animation*/
	public Model getCurrentFrame() {
		this.currentDelay++;
		if(this.currentDelay == this.delay) {
			this.currentFrame++;
			if(this.currentFrame == frames.size()) {
				this.currentFrame = 0;
			}
			this.currentDelay = 0;
		}
		return frames.get(currentFrame);
		
	}
}

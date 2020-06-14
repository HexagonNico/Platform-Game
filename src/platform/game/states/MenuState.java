package platform.game.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import platform.framework.DisplayManager;
import platform.framework.gamestates.GameState;
import platform.framework.gamestates.GameStateManager;

public class MenuState extends GameState {

	private String[] optionsMenu;
	private int selected;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		System.out.println("[GameStates][MenuState]: Creating menu...");
	}

	@Override
	public void init() {
		this.optionsMenu = new String[] {"Start", "Options", "Quit"};
		this.selected = 0;
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(new Color(51, 153, 255));
		graphics.fillRect(0, 0, DisplayManager.WIDTH, DisplayManager.HEIGHT);
		
		graphics.setFont(new Font("Arail", Font.PLAIN, 42));
		
		for(int i=0;i<optionsMenu.length;i++) {
			if(selected == i) graphics.setColor(Color.GREEN);
			else graphics.setColor(Color.WHITE);
			
			graphics.drawString(optionsMenu[i], DisplayManager.WIDTH/2 -200, 100 +i*120);
		}
	}

	@Override
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
			if(selected > 0) selected--;
		}
		else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
			if(selected < optionsMenu.length-1) selected++;
		}
		else if(key == KeyEvent.VK_ENTER || key == KeyEvent.VK_E) {
			if(selected == 0) {
				gsm.addState(new LevelOneState(gsm));
			} else if(selected == 2) {
				System.exit(0);
			}
		}
	}

	@Override
	public void keyReleased(int key) {}

}

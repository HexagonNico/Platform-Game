package platform.game.entities;

import java.util.Random;

import platform.framework.gamestates.GameStateManager;
import platform.framework.render.ModelManager;
import platform.game.states.LevelOneState;

public class Letter extends StaticEntity {

	private char letter;
	private boolean collected;
	
	public Letter(int posX, int posY) {
		super(posX, posY, null);
		
		Random rand = new Random();
		switch(rand.nextInt(6)) {
		case 0:
		case 1:
			this.letter = 'E';
			this.setModel(ModelManager.model(ModelManager.E));
			break;
		case 2:
			this.letter = 'X';
			this.setModel(ModelManager.model(ModelManager.X));
			break;
		case 3:
			this.letter = 'T';
			this.setModel(ModelManager.model(ModelManager.T));
			break;
		case 4:
			this.letter = 'N';
			this.setModel(ModelManager.model(ModelManager.N));
			break;
		case 5:
			this.letter = 'D';
			this.setModel(ModelManager.model(ModelManager.D));
			break;
		}
		
		this.collected = false;
	}
	
	@Override
	public void onEntityCollision(Entity entity, GameStateManager gsm) {
		if(entity instanceof Player) {
			int i = -1;
			switch(letter) {
			case 'E':
				i = LevelOneState.letters[0] == false ? 0 : 3;
				break;
			case 'X':
				i = 1;
				break;
			case 'T':
				i = 2;
				break;
			case 'N':
				i = 4;
				break;
			case 'D':
				i = 5;
				break;
			}
			LevelOneState.letters[i] = true;
			this.collected = true;
		}
	}

	public boolean isCollected() {
		return collected;
	}
}

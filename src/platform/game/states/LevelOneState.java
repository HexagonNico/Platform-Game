package platform.game.states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import platform.framework.DisplayManager;
import platform.framework.gamestates.GameState;
import platform.framework.gamestates.GameStateManager;
import platform.framework.render.Model;
import platform.framework.render.ModelManager;
import platform.framework.render.Renderer;
import platform.game.entities.Camera;
import platform.game.entities.Enemy;
import platform.game.entities.Entity;
import platform.game.entities.Letter;
import platform.game.entities.Player;
import platform.game.entities.Projectile;
import platform.game.entities.StaticEntity;
import platform.game.world.Map;

public class LevelOneState extends GameState {

	private Player player;
	private Camera camera;
	
	private ArrayList<StaticEntity> blocks;
	private ArrayList<Projectile> projectilesOnSceen;
	private ArrayList<Enemy> enemiesInLevel;
	public ArrayList<StaticEntity> extraEntities;
	
	private static int playerLives = 2;
	private static int levelIndex = 0;
	public static final boolean[] letters = new boolean[6];
	public static int totalScore = 0;
	
	public LevelOneState(GameStateManager gsm) {
		super(gsm);
		System.out.println("[GameStates][LevelOneState]: Creating level state...");
	}

	@Override
	public void init() {
		levelIndex++;
		this.reset();
	}
	
	private void reset() {
		Map map = new Map(levelIndex);
		
		this.player = map.getPlayer();
		this.camera = new Camera(player);
		this.blocks = map.getBlocks();
		this.projectilesOnSceen = new ArrayList<>();
		this.enemiesInLevel = map.getEnemies();
		this.extraEntities = new ArrayList<>();
		this.extraEntities.addAll(map.getLetters());
	}

	@Override
	public void tick() {
		this.moveEntities();
		this.checkCollisions();
		
		this.findEntitiesToDespawn();
		this.respawnPlayerIfNecessary();
		
		this.pickupLettters();
		this.checkIfLettersAreDone();

		this.camera.setPosition(player.getPosX(), player.getPosY());
	}

	@Override
	public void render(Graphics graphics) {
		Renderer.renderEntity(player, camera, graphics);
		
		for(StaticEntity block : this.blocks) {
			Renderer.renderEntity(block, camera, graphics);
		}
		
		for(Projectile arrow : this.projectilesOnSceen) {
			Renderer.renderEntity(arrow, camera, graphics);
		}
		
		for(Enemy enemy : this.enemiesInLevel) {
			Renderer.renderEntity(enemy, camera, graphics);
		}
		
		for(StaticEntity extra : this.extraEntities) {
			Renderer.renderEntity(extra, camera, graphics);
		}
		
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, DisplayManager.HEIGHT-85, DisplayManager.WIDTH, 85);
		for(int i=0;i<playerLives;i++) {
			Renderer.renderModel(ModelManager.model(ModelManager.LIFE), 10+i*30, DisplayManager.HEIGHT-65, graphics);
		}
		
		Model letterModel = null;
		for(int i=0;i<letters.length;i++) {
			switch(i) {
			case 0:
			case 3:
				letterModel = ModelManager.model(ModelManager.E);
				break;
			case 1:
				letterModel = ModelManager.model(ModelManager.X);
				break;
			case 2:
				letterModel = ModelManager.model(ModelManager.T);
				break;
			case 4:
				letterModel = ModelManager.model(ModelManager.N);
				break;
			case 5:
				letterModel = ModelManager.model(ModelManager.D);
				break;
			}
			if(letters[i] == true)
				Renderer.renderModel(letterModel, 300+i*30, DisplayManager.HEIGHT-65, graphics);
		}
		
		graphics.setColor(Color.WHITE);
		graphics.drawString("Score: "+totalScore, 15, DisplayManager.HEIGHT-70);
	}

	@Override
	public void keyPressed(int key) {
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			this.player.setLeft(true);
		}
		else if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			this.player.setRight(true);
		}
		else if(key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) {
			this.player.jump();
		}
		else if(key == KeyEvent.VK_F) {
			Projectile p = this.player.shotArrow();
			if(p != null) this.projectilesOnSceen.add(p);
		}
	}

	@Override
	public void keyReleased(int key) {
		if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
			this.player.setLeft(false);
		}
		else if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
			this.player.setRight(false);
		}
	}
	
	/**Calls Entity.move() on all entities<br>
	 * Doesn't move camera because it must be moved after checking collisions<br>
	 * Called at beginning of tick
	 */
	private void moveEntities() {
		this.player.move();

		if(!this.player.isDead()) {
			for(Enemy enemy : this.enemiesInLevel) {
				enemy.move();
			}
			for(Projectile prj : this.projectilesOnSceen) {
				prj.move();
			}
			for(StaticEntity entity : this.extraEntities) {
				if(entity instanceof Entity) {
					((Entity) entity).move();
				}
			}
		}
	}
	
	/**Handles collision between entities<br>
	 * Iterates through this.blocks and check collision with player<br>
	 * For every block iterates through enemies and arrows and check collisions with them<br>
	 * For every enemy iterates through arrows and check collision between them
	 */
	private void checkCollisions() {
		for(StaticEntity block : this.blocks) {
			this.player.checkCollision(block, gsm);
			
			for(Enemy enemy : this.enemiesInLevel) {
				enemy.checkCollision(block, gsm);
				for(Projectile arrow : this.projectilesOnSceen) {
					arrow.checkCollision(enemy, gsm);
				}
				enemy.checkCollision(player, gsm);
			}
			
			for(Projectile arrow : this.projectilesOnSceen) {
				arrow.checkCollision(block, gsm);
			}
			
			for(StaticEntity entity : this.extraEntities) {
				if(entity instanceof Entity) {
					((Entity) entity).checkCollision(block, gsm);
				}
			}
		}

		for(StaticEntity entity : this.extraEntities) {
			this.player.checkCollision(entity, gsm);
		}
	}

	/**Removes enemies and projectiles if they should despawn or if they're dead*/
	private void findEntitiesToDespawn() {
		for(int i=0;i<this.projectilesOnSceen.size();i++) {
			if(this.projectilesOnSceen.get(i).shouldDespawn())
				this.projectilesOnSceen.remove(i);
		}
		
		for(int i=0;i<this.enemiesInLevel.size();i++) {
			if(this.enemiesInLevel.get(i).isDead())
				this.enemiesInLevel.remove(i);
		}
		
		for(int i=0;i<this.extraEntities.size();i++) {
			if(this.extraEntities.get(i) instanceof Entity) {
				if(((Entity) this.extraEntities.get(i)).isDead())
					this.extraEntities.remove(i);
			}
		}
	}
	
	/**Checks if player should respawn<br>
	 * If so resets the level or pass to game over state
	 */
	private void respawnPlayerIfNecessary() {
		if(this.player.shouldRespawn()) {
			this.player.setDead(false);
			playerLives--;
			if(playerLives >= 0) {
				this.reset();
			} else {
				this.gsm.addState(new GameOver(gsm));
				levelIndex = 0;
				playerLives = 2;
			}
		}
	}
	
	/**Iterates through this.blocks and check if letters were collcted<br>
	 * If so removes the letter from the blocks
	 */
	private void pickupLettters() {
		for(int i=0;i<this.extraEntities.size();i++) {
			if(this.extraEntities.get(i) instanceof Letter) {
				if(((Letter) this.extraEntities.get(i)).isCollected()) {
					this.extraEntities.remove(i);
				}
			}
		}
	}
	
	/**Checks if all 6 letters were collected<br>
	 * If so removes them and add a life
	 */
	private void checkIfLettersAreDone() {
		if(letters[0] == true && letters[1] == true && letters[2] == true && letters[3] == true && letters[4] == true && letters[5] == true) {
			for(int i=0;i<letters.length;i++) {
				letters[i] = false;
			}
			playerLives++;
		}
	}
}

package platform.game.world;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import platform.framework.render.Model;
import platform.framework.render.ModelManager;
import platform.game.entities.Enemy;
import platform.game.entities.ExitDoor;
import platform.game.entities.Letter;
import platform.game.entities.Player;
import platform.game.entities.Spikes;
import platform.game.entities.StaticEntity;
import platform.game.utils.Direction;

public class Map {

	private Player player;
	private ArrayList<StaticEntity> blocks;
	private ArrayList<Enemy> enemies;
	private ArrayList<Letter> letters;
	
	private int height;
	private int width;
	
	public Map(int levelIndex) {
		this.blocks = new ArrayList<>();
		this.enemies = new ArrayList<>();
		this.letters = new ArrayList<>();
		
		System.out.println("[World][Map]: Loading map file "+levelIndex);
		this.loadFile(levelIndex);

	}
	
	public ArrayList<StaticEntity> getBlocks() {
		return blocks;
	}
	
	public ArrayList<Enemy> getEnemies() {
		return enemies;
	}
	
	public ArrayList<Letter> getLetters() {
		return letters;
	}

	public Player getPlayer() {
		return player;
	}
	
	private void loadFile(int levelIndex) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(new File("res/maps/level"+levelIndex+".txt")));
			
			this.height = Integer.parseInt(reader.readLine());
			this.width = Integer.parseInt(reader.readLine());
			
			Model blockModel = ModelManager.model("block");
			
			for(int y=0;y<height;y++) {
				String line = reader.readLine();
				String[] singles = line.split("\\s+");
				
				for(int x=0;x<width;x++) {
					switch(singles[x]) {
					case "P":
						this.player = new Player(x*blockModel.width, y*blockModel.height);
						break;
					case "1":
						this.blocks.add(new StaticEntity(x*blockModel.width, y*blockModel.height, blockModel));
						break;
					case "x":
						this.enemies.add(new Enemy(x*blockModel.width, y*blockModel.height));
						break;
					case "^":
						this.blocks.add(new Spikes(x*blockModel.width, y*blockModel.height, Direction.UP));
						break;
					case "<":
						this.blocks.add(new Spikes(x*blockModel.width, y*blockModel.height, Direction.LEFT));
						break;
					case ">":
						this.blocks.add(new Spikes(x*blockModel.width, y*blockModel.height, Direction.RIGHT));
						break;
					case ".":
						this.blocks.add(new Spikes(x*blockModel.width, y*blockModel.height, Direction.DOWN));
						break;
					case "X":
						this.blocks.add(new ExitDoor(x*blockModel.width, y*blockModel.height-20));
						break;
					case "e":
						this.letters.add(new Letter(x*blockModel.width, y*blockModel.height-20));
						break;
					case "0":
						break;
					}
				}
			}
		} catch(IOException e) {
			System.err.println("[World][Map]: Couldn't load map file "+levelIndex);
			this.loadFile(levelIndex-1);
		}
	}
}

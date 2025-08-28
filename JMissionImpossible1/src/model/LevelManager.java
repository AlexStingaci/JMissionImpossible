package model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import controller.Game;
import utilities.LoadSave;

public class LevelManager {
	private Game game;
	private BufferedImage[][] levelImg;
	private Level levelOne;
	
	public LevelManager(Game game) {
		this.game = game;
		loadAnimations();
		levelOne = new Level(LoadSave.GetLevelData());
	}
	
	public Level getCurrentLevel() {
		return levelOne;
	}
	
	
	private void loadAnimations() {

		levelImg = new BufferedImage[9][22];
		
		for(int i=0;i<8;i++) {
			levelImg[i][0] = LoadSave.loadImages("/levels/"+(i+1)+"/floor1.png");
			levelImg[i][1] = LoadSave.loadImages("/levels/"+(i+1)+"/leftFloor1.png");
			levelImg[i][2] = LoadSave.loadImages("/levels/"+(i+1)+"/leftWall1.png");
			levelImg[i][3] = LoadSave.loadImages("/levels/"+(i+1)+"/lift1.png");
			levelImg[i][4] = LoadSave.loadImages("/levels/"+(i+1)+"/rightFloor1.png");
			levelImg[i][5] = LoadSave.loadImages("/levels/"+(i+1)+"/rightWall.png");
		}
		
		for(int i=8;i<22;i++) {
			levelImg[8][i] = LoadSave.loadImages("/objects/obj"+(i-7)+".png");
		}	
	}
	public void draw(Graphics g) {
		for(int j=0;j<Game.Tiles_Height;j++) {
			for(int i=0;i<Game.Tiles_Width;i++) {
				int index = levelOne.getSpriteIndex(i,j);
				if(index!=0) {
					try {
						Optional<String[]> res = cercaValori("./res/levels/level1.txt", index);
						String[] values = res.get();
						if(values[0].equals("8")) {
							g.drawImage(levelImg[Integer.parseInt(values[0])][Integer.parseInt(values[1])], Game.Tiles_Final_Size*i, Game.Tiles_Final_Size*j,Game.Tiles_Final_Size,Game.Tiles_Final_Size,null);
						}else {
							g.drawImage(levelImg[Integer.parseInt(values[0])][Integer.parseInt(values[1])], Game.Tiles_Final_Size*i, Game.Tiles_Final_Size*j,Game.Tiles_Final_Size,Game.Tiles_Final_Size,null);
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public void update() {
		
	}
	
	public static Optional<String[]> cercaValori(Path file, long numero) throws IOException {
	    String key = Long.toString(numero);

	    Pattern p = Pattern.compile("^\\s*(\\d+)\\s*=\\s*([^,]+?)\\s*,\\s*([^#\\r\\n]+?)\\s*(?:#.*)?$");

	    try (Stream<String> lines = Files.lines(file, StandardCharsets.UTF_8)) {
	        return lines
	            .map(String::trim)
	            .filter(s -> !s.isEmpty() && !s.startsWith("#"))
	            .map(p::matcher)
	            .filter(Matcher::matches)
	            .filter(m -> m.group(1).equals(key))
	            .map(m -> new String[]{ m.group(2).trim(), m.group(3).trim() })
	            .findFirst();
	    }
	}
	
	public static Optional<String[]> cercaValori(String file, long numero) throws IOException {
	    return cercaValori(Paths.get(file), numero);
	}
}

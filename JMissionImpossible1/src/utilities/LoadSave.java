package utilities;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;

import controller.Game;

public class LoadSave {
	
	public static BufferedImage loadImages(String resourcePath) {
	    try (InputStream is = LoadSave.class.getResourceAsStream(resourcePath)) {
	        if (is == null) throw new IllegalArgumentException("Risorsa non trovata: " + resourcePath);
	        return ImageIO.read(is);
	    } catch (Exception e) {
	        throw new RuntimeException("Errore nel caricare " + resourcePath, e);
	    }
	}
	
	public static int[][] GetLevelData(){
		int[][] lvData = new int[Game.Tiles_Height][Game.Tiles_Width];
		
		BufferedImage img = LoadSave.loadImages("/levels/1/level1.png");
		
		for(int j=0; j<img.getHeight();j++) {
			for(int i=0;i<img.getWidth();i++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				lvData[j][i] = value;
			}
		}
		return lvData;
	}
	
}

package com.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpawnLevel extends Level {
	
	//private Tile[] tiles;
	//private int[] tiles;
	
	public SpawnLevel(String path) {
		super(path);
	}
	
	protected void loadLevel(String path)
	{
		try {
			BufferedImage image = ImageIO.read(new File(path));
			int w = width= image.getWidth();
			int h = height =image.getHeight();
			//tiles = new Tile[w * h];
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			System.out.println("Exception! Could not load level file!");
		}
	}
	
	//0xff00ff6f
	//0xffe0ff19
	//0xff6d7c0c
	protected void generateLevel() 
	{
		//for(int i = 0; i < levelPixels.length; i++)
		//{
			//if(levelPixels[i] == 0xff00ff6f) tiles[i] = Tile.grass;
			//if(levelPixels[i] == 0xffe0ff19) tiles[i] = Tile.flower;
			//if(levelPixels[i] == 0xff6d7c0c) tiles[i] = Tile.rock;
		//}
	}
}

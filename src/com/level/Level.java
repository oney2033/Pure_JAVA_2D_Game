package com.level;

import java.util.ArrayList;
import java.util.List;

import com.again.Screen;
import com.again.Sprite;

import entity.Entity;
import entity.Player;

public class Level {
	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	//protected Level level;
	//protected Tile[] tiles;
	
	private List<Entity>entities = new ArrayList<Entity>();
	
	public static Level spawn = new SpawnLevel("resa/textures/levels/spawn.png");
	
	public Level(int width, int height)
	{
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level(String path)
	{
		loadLevel(path);
		generateLevel();
	}
	
	
	
	protected void generateLevel() {
		
	}
	
	protected void loadLevel(String path)
	{
		
	}
	
	public void update()
	{
		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).update();
		}
	}
	
	public void time()
	{
		
	}
	
	public void render(int xScroll, int yScroll,Screen screen)
	{
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll>>4;
		int x1 = (xScroll + screen.width + 16)>>4;
		int y0 = yScroll>>4;
		int y1 = (yScroll + screen.height + 16)>>4;
		
		for(int y = y0; y < y1; y++)
		{
			for(int x = x0; x < x1; x++)
			{
				getTile(x,y).render(x, y, screen);
			}
		}
		for(int i = 0; i < entities.size(); i++)
		{
			entities.get(i).render(screen);
		}
	}
	
	public void add(Entity e)
	{
		entities.add(e);
	}
	

	//0xff00ff6f
	//0xffe0ff19
	//0xff6d7c0c
	
	public Tile getTile(int x, int y)
	{
		if( x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		
		if(tiles[x + y * width] ==Tile.col_spawn_floor)return Tile.spawn_floor;
		if(tiles[x + y * width] ==Tile.col_spawn_grass)return Tile.spawn_grass;
		if(tiles[x + y * width] ==Tile.col_spawn_hedge)return Tile.spawn_hedge;
		if(tiles[x + y * width] ==Tile.col_spawn_wall1)return Tile.spawn_wall1;
		if(tiles[x + y * width] ==Tile.col_spawn_wall2)return Tile.spawn_wall2;
		if(tiles[x + y * width] ==Tile.col_spawn_water)return Tile.spawn_water;
		return Tile.voidTile;
		
		//if(tilesInt[x + y * width] ==0)return Tile.grass;
		//if(tilesInt[x + y * width] ==1)return Tile.flower;
		//if(tilesInt[x + y * width] ==2)return Tile.rock;
		//return Tile.voidTile;
	}
	
}

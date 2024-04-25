package com.again;

import java.util.Random;

import com.level.Tile;

public class Screen {
	public int width, height;
	public int[] Pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE-1;
	
	public int xOffset, yOffset;
	
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	
	private Random random = new Random();

	
	Screen(int width, int height)
	{
		this.width = width;
		this.height = height;
		Pixels = new int[width * height];
		
		for(int i = 0; i< MAP_SIZE * MAP_SIZE; i++)
		{
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	public void Clear()
	{
		for(int i = 0; i < Pixels.length; i++)
		{
			Pixels[i] = 0;
		}
	}
	

	public void Render(int xoffset,int yoffset)
	{
		for(int y = 0; y < height; y++)
		{
			int yy = y+yoffset;
			if(yy < 0 || yy >= height)continue;
			for(int x = 0; x < width; x++)
			{
				int xx = x+xoffset;
				if(xx < 0 || xx >= width)continue;
				Pixels[xx + yy * width] = Sprite.grass.Pixels[(x & 15) + (y & 15) * Sprite.grass.SIZE];
			}
		}		
	}

	public void renderTile(int xp, int yp, Tile tile)
	{
		xp -=xOffset;
		yp -=yOffset;
		for(int y = 0; y < tile.sprite.SIZE; y++)
		{
			int ya = y + yp;
			for(int x = 0; x < tile.sprite.SIZE; x++)
			{
				int xa = x + xp;
				//当一个图块超出了屏幕范围后，停止渲染这个图块,就是只渲染我们看得到的图块
				if(ya < 0 || ya >= height || xa < -tile.sprite.SIZE || xa >=width)break;
				if(xa < 0)xa=0;
				Pixels[xa + ya * width] = tile.sprite.Pixels[x + y * tile.sprite.SIZE];
			}
		}
	}
	
	public void renderTile(int xp, int yp, Sprite sprite)
	{
		xp -=xOffset;
		yp -=yOffset;
		for(int y = 0; y < sprite.SIZE; y++)
		{
			int ya = y + yp;
			for(int x = 0; x < sprite.SIZE; x++)
			{
				int xa = x + xp;
				//当一个图块超出了屏幕范围后，停止渲染这个图块,就是只渲染我们看得到的图块
				if(ya < 0 || ya >= height || xa < -sprite.SIZE || xa >=width)break;
				if(xa < 0)xa=0;
				Pixels[xa + ya * width] = sprite.Pixels[x + y * sprite.SIZE];
			}
		}
	}

	
	
	public void renderPlayer(int xp, int yp, Sprite sprite,int flip)
	{
		xp -=xOffset;
		yp -=yOffset;
		for(int y = 0; y < 32; y++)
		{
			int ya = y + yp;
			int ys = y;
			if(flip == 2 || flip ==3)ys = 31-y;
			for(int x = 0; x < 32; x++)
			{
				int xa = x + xp;
				int xs = x;
				if(flip == 1 || flip ==3)xs = 31-x;
				//if(flip == 1)xs = 31-x;
				if(xa < -32 || xa >= width || ya < 0 || ya >= height)break;
				if(xa < 0)xa=0;
				int col = sprite.Pixels[xs + ys * 32];
				if(col != 0xffff00ff)
				Pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void setOffset(int xoffset, int yoffset)
	{
		this.xOffset = xoffset;
		this.yOffset = yoffset;
	}
}

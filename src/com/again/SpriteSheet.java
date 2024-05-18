package com.again;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path;
	public final int SIZE;
	public final int WIDTH, HEIGHT;
	public int[] Pixels;
	
	public static SpriteSheet tiles = new SpriteSheet("resa/textures/sheets/spritesheets.png",256);
	public static SpriteSheet spawn_level = new SpriteSheet("resa/textures/sheets/spawn_level.png",48);
	public static SpriteSheet projectile_wisard = new SpriteSheet("resa/textures/sheets/projectiles/wizard.png",48);
	
	public static SpriteSheet player = new SpriteSheet("resa/textures/sheets/player_sheet1.png",128,96);
	
	public static SpriteSheet player_down = new SpriteSheet(player, 0, 0, 1, 3, 32);
	public static SpriteSheet player_up = new SpriteSheet(player, 1, 0, 1, 3, 32);
	public static SpriteSheet player_left = new SpriteSheet(player, 2, 0, 1, 3, 32);
	public static SpriteSheet player_right = new SpriteSheet(player, 3, 0, 1, 3, 32);
	
	public static SpriteSheet dummy = new SpriteSheet("resa/textures/sheets/King_Cherno.png",128,96);
	public static SpriteSheet dummy_down = new SpriteSheet(dummy, 0, 0, 1, 3, 32);
	public static SpriteSheet dummy_up = new SpriteSheet(dummy, 1, 0, 1, 3, 32);
	public static SpriteSheet dummy_left = new SpriteSheet(dummy, 2, 0, 1, 3, 32);
	public static SpriteSheet dummy_right = new SpriteSheet(dummy, 3, 0, 1, 3, 32);
	
	private Sprite[] sprites;//��������
	
	public Sprite[] getSprite()
	{
		return sprites;
	}
	
	//����һ���������Ϊ����
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height,int spriteSize)
	{
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if(width == height)SIZE = width;
		else SIZE =-1;
		WIDTH = w;
		HEIGHT = h;
		Pixels = new int[w * h];
		for(int y0 = 0; y0 < h; y0++)
		{
			int yp = yy + y0;
			for(int x0 = 0; x0 < w; x0++)
			{
				int xp = xx + x0;
				Pixels[x0 + y0 * w] = sheet.Pixels[xp + yp * sheet.WIDTH];
			}
		}
		
		int frame = 0;
		sprites = new Sprite[width * height];
		for(int ya = 0; ya < height; ya++)//y�᷽���ж��ٸ�����
		{
			for(int xa = 0; xa < width; xa++)//x�᷽���ж��ٸ�����
			{
				int[] spritePixels = new int[spriteSize * spriteSize];
				for(int y0 = 0; y0 < spriteSize; y0++)
				{
					for(int x0 = 0; x0 < spriteSize; x0++)
					{
						spritePixels[x0 + y0 * spriteSize] = Pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * WIDTH];//���Ӿ�����������ȡ����
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize,spriteSize);
				sprites[frame++] = sprite;
			}
		}
		
	}
	
	public SpriteSheet(String path, int size)
	{
		this.path = path;
		SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		Pixels = new int[SIZE * SIZE];
		load();
	}
	
	//�Ӿ����
	public SpriteSheet(String path, int width, int height)
	{
		this.path = path;
		SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		Pixels = new int[WIDTH * HEIGHT];
		load();
	}
	
	private void load()
	{
		try {
			BufferedImage image = ImageIO.read(new File(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, Pixels, 0, w);//�������洢��pixels��
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}

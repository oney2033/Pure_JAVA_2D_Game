package com.level.Spwan_Level;

import com.again.Screen;
import com.again.Sprite;
import com.level.Tile;

public class SpawnWallTile extends Tile {

	public SpawnWallTile(Sprite sprite) 
	{
		super(sprite);
		// TODO �Զ����ɵĹ��캯�����
	}
	
	public void render(int x, int y, Screen screen)
	{
		//������Ⱦ
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid()
	{
		return true;
	}
}

package com.level.Spwan_Level;

import com.again.Screen;
import com.again.Sprite;
import com.level.Tile;

public class SpawnWaterTile extends Tile {

	public SpawnWaterTile(Sprite sprite) 
	{
		super(sprite);
		// TODO �Զ����ɵĹ��캯�����
	}
	
	public void render(int x, int y, Screen screen)
	{
		//������Ⱦ
		screen.renderTile(x << 4, y << 4, this);
	}
}

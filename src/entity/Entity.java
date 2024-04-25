package entity;

import java.util.Random;

import com.again.Screen;
import com.level.Level;

public abstract class Entity {
	public int x , y ;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update()
	{
		
	}
	
	public void render(Screen screen)
	{
		
	}
	
	public void remove()
	{
		removed = true;
	}
	
	public void init(Level level)
	{
		this.level = level;
	}
	
	public boolean isRemove()
	{
		return removed;
	}
}

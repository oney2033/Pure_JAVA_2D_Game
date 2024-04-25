package entity;

import com.again.Screen;
import com.again.Sprite;

public class WizarProjectile extends Projectile{

	public WizarProjectile(int x, int y, double dir)
	{
		super(x, y, dir);
		range = 300;
		damage = 20;
		speed = 4;
		rateOfFire = 15;
		sprite = Sprite.grass;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);	
	}
	
	public void update()
	{
		move();
	}
	
	public void move()
	{
		x += nx;
		y += ny;
	}
	
	public void render(Screen screen)
	{
		screen.renderTile(x, y,sprite);
	}
}

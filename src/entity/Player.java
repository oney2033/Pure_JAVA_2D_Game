package entity;

import com.again.Game;
import com.again.Mouese;
import com.again.Screen;
import com.again.Sprite;
import com.again.keyboard;

public class Player extends Mob{
	private keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	private int fireRate = 0;
	
	public Player(keyboard input)
	{
		this.input = input;
		sprite = sprite.player_forward;
	}
	
	public Player(int x, int y,keyboard input)
	{
		this.x = x;
		this.y = y;
		this.input = input;
		fireRate = WizarProjectile.FIRE_RATE;
	}
	
	public void update()
	{
		if(fireRate > 0)fireRate--;
		int xa = 0, ya = 0;
		if(anim < 7500) anim++;
		else anim = 0;
		if(input.up) ya--;
		if(input.down) ya++;
		if(input.left) xa--;
		if(input.right) xa++;
				
		if(xa != 0 || ya != 0)
		{			
			move(xa,ya);
			walking = true;
		}
		else
		{
			walking = false;
		}
		clear();
		updateShooting();
	}
	
	private void clear() 
	{
		for(int  i = 0; i < level.getProjectiles().size(); i++)
		{
			Projectile p = level.getProjectiles().get(i);
			if(p.isRemove())level.getProjectiles().remove(i);
		}
	}

	private void updateShooting()
	{
		if(Mouese.getButton()==1 && fireRate <= 0)
		{
			double dx = Mouese.getX() - Game.getWindowWidth() / 2;
			double dy = Mouese.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);
			//double trueangle = dir * 180/Math.PI;
			//System.out.println("truedir" + trueangle);
			shoot(x,y,dir);
			fireRate = WizarProjectile.FIRE_RATE;
		}
	}
	
	public void render(Screen screen)
	{
	
		int flip = 0;
		if(dir == 0)
		{
			sprite = sprite.player_forward;
			if(walking)
			{
				if(anim % 20 > 10)
				{
					sprite = sprite.player_forward_1;
				}
				else
				{
					sprite = sprite.player_forward_2;
				}
			}
		}
		if(dir == 1)
		{
			sprite = sprite.player_side;
			if(walking)
			{
				if(anim % 20 > 10)
				{
					sprite = sprite.player_side_1;
				}
				else
				{
					sprite = sprite.player_side_2;
				}
			}
		}
		
		if(dir == 2)
			{
				sprite = sprite.player_back;
				if(walking)
				{
					if(anim % 20 > 10)
					{
						sprite = sprite.player_back_1;
					}
					else
					{
						sprite = sprite.player_back_2;
					}
				}
			}

		if(dir == 3)
			{
				sprite = sprite.player_side;
				flip = 1;
				if(walking)
				{
					if(anim % 20 > 10)
					{
						sprite = sprite.player_side_1;
					}
					else
					{
						sprite = sprite.player_side_2;
					}
				}
			}
		
		//if(dir == 3)sprite = sprite.player_left;
		//int xx = x-16;
		//int yy = y-16
		screen.renderPlayer(x-16, y-16, sprite,flip);
		
		//screen.renderPlayer(xx + 16, yy, sprite.player1);
		//screen.renderPlayer(xx, yy + 16, sprite.player2);
		//screen.renderPlayer(xx + 16, yy + 16, sprite.player3);
	}
	

}

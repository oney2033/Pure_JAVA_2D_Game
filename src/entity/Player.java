package entity;

import java.util.List;

import com.again.AnimatedSprite;
import com.again.Game;
import com.again.Mouese;
import com.again.Screen;
import com.again.Sprite;
import com.again.SpriteSheet;
import com.again.keyboard;

public class Player extends Mob{
	private keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	private int fireRate = 0;
	
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down,32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up,32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left,32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right,32, 32, 3);
	
	private AnimatedSprite animSprite = down;
	
	public Player(keyboard input)
	{
		this.input = input;
		sprite = sprite.player_forward;
		//animSprite = down;
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
		/*
		List<Entity> es = level.getEntities(this, 80);
		System.out.println(es.size());
		for(Entity e : es)
		{
			System.out.println(e);
		}
		*/
		
		if(walking)animSprite.update();
		else animSprite.setFrame(0);
		if(fireRate > 0)fireRate--;
		double xa = 0, ya = 0;
		double speed =2.0;
		if(anim < 7500) anim++;
		else anim = 0;
		if(input.up) 
		{
			ya-= speed;
			animSprite = up;
		}
		else if(input.down) 
		{
			ya+= speed;
			animSprite = down;
		}
		 if(input.left) 
		{
			xa-= speed;
			animSprite = left;
		}
		 else if(input.right) 
		{
			xa+= speed;
			animSprite = right;
		}
				
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
	/*
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
			*/
		sprite = animSprite.getSprite();
		screen.renderMob((int)(x-16), (int)(y-16), sprite,flip);
	}
	

}

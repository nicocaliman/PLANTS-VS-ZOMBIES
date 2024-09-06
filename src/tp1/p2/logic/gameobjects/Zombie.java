package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Zombie extends GameObject implements Cloneable
{
	private static final int ENDURANCE = 5;
	
	private static final int DAMAGE = 1;
	
	private static final int SPEED = 2;	
	
	Zombie(GameWorld game, int col, int row)
	{
		super(game, col, row, ENDURANCE, SPEED);
	}
	
	Zombie(GameWorld game, int col, int row, int endurance, int cooldown)
	{
		super(game, col, row, endurance, cooldown);
	}
	
	Zombie()
	{
		
	}
	
	
	public void update()
	{
		super.update();										//call superclass's update method (GameObject.java)
	}		
	
	public String getName()
	{
		return Messages.ZOMBIE_NAME;		//"Zombie"
	}
	
	@Override	
	public String getSymbol()
	{
		return Messages.ZOMBIE_SYMBOL;		//"Z"
	}
		
	@Override	
	public String getDescription()
	{
		return Messages.zombieDescription(Messages.ZOMBIE_NAME, SPEED, DAMAGE, ENDURANCE);		//zombie's description
	}
	
	public Zombie copy(GameWorld game, int col, int row) 
	{
		ExplosiveZombie zombie = new ExplosiveZombie(game, col, row);
				
		return zombie;								//create zombie
	}
	
	public void onEnter()
	{
		this.game.updateRemainingZombies();									//zombie enters on the board(remaining zombies--)
	}
	
	public void onExit()
	{
		this.game.updateZombiesAlived();								//zombie dies(zombiesAlived--)
	}	
	
	protected int getSpeed()
	{
		return SPEED;						//return zombie speed
	}
	
	protected int getDamage()
	{
		return DAMAGE;						//return zombie damage
	}
	
	protected int getEndurance()
	{
		return ENDURANCE;					//return zombie endurance
	}	

	@Override
	public boolean receiveZombieAttack(int damage)
	{
		// TODO Auto-generated method stub
		return false;							//false: zombie cannot receive zombie damage
	}

	@Override
	public boolean receivePlantAttack(int damage) 
	{
		// TODO Auto-generated method stub		
		
		super.receiveDamage(damage);				//receive damage
		
		return true;						//true: zombie receives plant's damage
	}
	
	@Override
	protected void delayedAction()
	{
		// TODO Auto-generated method stub
		
		boolean attackAction = this.game.attackPlant(this.col - 1, this.row, this.getDamage());		
		
		if(this.cooldown > 0)
		{
			this.cooldown--;
		}
		
		else if(this.cooldown == 0)
		{
			if(attackAction == true)
			{
				this.cooldown = this.getCooldown() - 1;
			}
			
			else if(attackAction == false && game.isValidZombiePosition(this.col - 1, this.row))
			{
				this.col--;
				
				this.game.attackPlant(this.col - 1, this.row, this.getDamage());	
				
				this.cooldown = this.getCooldown() - 1;
			}
			
			else if(attackAction == false && game.isValidZombiePosition(this.col - 1, this.row) == false)
			{
				this.cooldown = this.getCooldown() - 1;
			}
		}		
	}	
	
	@Override
	public int getCooldown()
	{
		return SPEED;
	}
	
}
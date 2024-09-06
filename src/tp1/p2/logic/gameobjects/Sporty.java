package tp1.p2.logic.gameobjects;

import tp1.p2.view.Messages;
import static tp1.p2.view.Messages.zombieDescription;

import tp1.p2.logic.GameWorld;

public class Sporty extends Zombie
{
	private static final int ENDURANCE = 2;
	
	private static final int DAMAGE = 1;
	
	private static final int SPEED = 1;
	
	public Sporty()
	{
		
	}
	
	public Sporty(GameWorld game, int col, int row)
	{
		super(game, col, row, ENDURANCE, SPEED);						//call super's constructor (Zombie)
	}
	
	@Override	
	public String getDescription()
	{
		return zombieDescription(Messages.SPORTY_ZOMBIE_NAME, SPEED, DAMAGE, ENDURANCE);		//sporty'z description
	}

	@Override
	public String getSymbol()
	{
		return Messages.SPORTY_ZOMBIE_SYMBOL;				//"Sz"
	}	
	
	@Override
	public int getSpeed()
	{
		return SPEED;						//1
	}	
	
	@Override
	public int getCooldown()
	{
		return SPEED;
	}
}
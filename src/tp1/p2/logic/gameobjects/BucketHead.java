package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class BucketHead extends Zombie
{
	private static final int ENDURANCE = 8;
	
	private static final int DAMAGE = 1;
	
	private static final int SPEED = 4;
	
	public BucketHead(GameWorld game, int col, int row)
	{
		super(game, col, row, ENDURANCE, SPEED);					//call super's constructor(Zombie)
	}
	
	public BucketHead()
	{
	
	}
	
	@Override	
	public String getDescription()
	{
		return Messages.zombieDescription(Messages.BUCKET_HEAD_ZOMBIE_NAME, SPEED, DAMAGE, ENDURANCE);		//zombie's description
	}

	@Override
	public String getSymbol()
	{
		return Messages.BUCKET_HEAD_ZOMBIE_SYMBOL;		//"Bz"
	}	 
	
	protected int getSpeed()
	{
		return SPEED;									//speed = 4
	}	
	
	@Override
	public int getCooldown()
	{
		return SPEED;
	}	
}
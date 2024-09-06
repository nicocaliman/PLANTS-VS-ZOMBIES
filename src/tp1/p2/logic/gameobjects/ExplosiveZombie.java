package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class ExplosiveZombie extends Zombie 
{
	private static final int ENDURANCE = 5;
	
	private static final int DAMAGE = 1;
	
	private static final int SPEED = 2;
	
	private static final int EXPLOSION_DAMAGE = 3;
	
	public ExplosiveZombie(GameWorld game, int col, int row)
	{
		super(game, col, row, ENDURANCE, SPEED);				//call super's constructor (Zombie)
	}
	
	public ExplosiveZombie()
	{
		
	}
	
	@Override	
	public String getDescription()
	{
		return Messages.zombieDescription(Messages.EXPLOSIVE_ZOMBIE_NAME, SPEED, DAMAGE, ENDURANCE) + "\n";		//explosive zombie's description
	}
			
	@Override
	public String getSymbol()
	{
		return Messages.EXPLOSIVE_ZOMBIE_SYMBOL;		//"Ez"
	}

	@Override
	public void onExit()
	{
		super.onExit();																					//zombiesAlived--
		
		game.pushAction(new ExplosionAction(this.col, this.row, EXPLOSION_DAMAGE, false));
	}		
}
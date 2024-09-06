package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class CherryBomb extends Plant
{
	private static final int COST = 50;
	
	private static final int ENDURANCE = 2;
	
	private static final int DAMAGE = 10;
	
	private static final int DELAY_TO_EXPLODE = 2;
	
	private boolean explode;

	CherryBomb(GameWorld game, int col, int row)
	{
		super(game, col, row, ENDURANCE, DELAY_TO_EXPLODE);			//call super's constructor(Plant)
		
		this.explode = false;
	}
	
	CherryBomb()
	{
		
	}
	
	@Override
	public String getDescription() 
	{
		return Messages.plantDescription(Messages.CHERRY_BOMB_NAME_SHORTCUT, COST, DAMAGE, ENDURANCE) + "\n";			//cherry description
	}
	
	@Override
	protected String getSymbol() 
	{		
		if(this.cooldown == DELAY_TO_EXPLODE - 1)
		{	
			return Messages.CHERRY_BOMB_SYMBOL;				//"c"
		}
		
		return Messages.CHERRY_BOMB_SYMBOL.toUpperCase();								//"C"
	}

	@Override
	protected String getName() 
	{
		return Messages.CHERRY_BOMB_NAME;					//"Cherry-Bomb"
	}
	
	@Override
	public int getCost() 
	{
		return COST;										//50
	}	
	
	@Override
	protected void delayedAction() 
	{
		if(this.cooldown != 0)
		{
			this.cooldown--;
		}
		
		else
		{

			this.explode = true;						//explode
			
			this.kill();								//kill cherry bomb (lives = 0)
		}			
	}	

	@Override
	public Plant copy(GameWorld game, int col, int row) 
	{
		return new CherryBomb(game, col, row);						//create cherrybomb
	}	
	
	@Override
	public void onExit()
	{
		if(explode)
		{
			game.pushAction(new ExplosionAction(this.col, this.row, DAMAGE));    //explosion action
		}
	}

	@Override
	public void update() 
	{				
		super.update();							//call superclass's update (GameObject)
	}		
}
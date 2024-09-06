package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Peashooter extends Plant
{
	private static final int COST = 50;
	
	private static final int ENDURANCE = 3;
	
	private static final int DAMAGE = 1;
		
	Peashooter(GameWorld game, int col, int row)
	{
		super(game, col, row, ENDURANCE);					//call super's constructor(Plant)
	}
	
	Peashooter()
	{
		
	}
	
	@Override
	
	public String getDescription()
	{
		return Messages.plantDescription(Messages.PEASHOOTER_NAME_SHORTCUT, COST, DAMAGE, ENDURANCE);			//peashooter's description
	}
	
	@Override
	
	protected String getSymbol()
	{
		return Messages.PEASHOOTER_SYMBOL;				//"P"
	}
	
	@Override
	
	protected String getName()
	{
		return Messages.PEASHOOTER_NAME;					//"Peashooter"
	}
	
	@Override
	
	public int getCost()
	{
		return COST;										//50
	}
	
	@Override
	
	public void update()
	{				
		for(int i = this.col + 1; i < GameWorld.NUM_COLS;i++)
		{
			if(game.attackZombie(i, this.row, DAMAGE) == true)		
			{				
				break;										//success
			}
		}		
	}
	
	@Override
	
	public Plant copy(GameWorld game, int col, int row)
	{		
		return new Peashooter(game, col, row);								//create peashooter
	}	
}
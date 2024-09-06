package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class WallNut extends Plant
{
	private static final int COST = 50;
	
	private static final int ENDURANCE = 10;
	
	private static final int DAMAGE = 0;
	
	WallNut(GameWorld game, int col, int row)
	{
		super(game, col, row, ENDURANCE);					//call super's constructor(Plant)
	}
	
	WallNut()
	{
		
	}	

	@Override
	protected String getName() 
	{
		return Messages.WALL_NUT_NAME;						//"Wall-Nut"
	}

	@Override
	public Plant copy(GameWorld game, int col, int row)
	{
		return new WallNut(game, col, row);							//create wall-nut
	}

	@Override
	public int getCost() 
	{		
		return COST;										//50
	}	

	@Override
	protected String getSymbol()
	{		
		return Messages.WALL_NUT_SYMBOL;					//"WN"
	}

	@Override
	public String getDescription() 
	{
		return Messages.plantDescription(Messages.WALL_NUT_NAME_SHORTCUT, COST, DAMAGE, ENDURANCE);				//wall-nut description
	}

	@Override
	public void update()
	{		
		//void
	}	
}
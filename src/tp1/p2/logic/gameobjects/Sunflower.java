package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sunflower extends Plant 
{	
	//CONSTANTS
	
	private static final int COST = 20;
	
	private static final int ENDURANCE = 1;
	
	private static final int DAMAGE = 0;
	
	private static final int GENERATE_COINS = 3;
	
	public static final int COINS = 10;	
	
	//CONSTRUCTORS
	
	Sunflower(GameWorld game, int col, int row)
	{
		super(game, col, row, ENDURANCE, GENERATE_COINS);    //call super's constructor (Plant)
	}
	
	Sunflower()
	{
		
	}
	
	//METHODS	

	@Override
	protected String getName()
	{
		// TODO Auto-generated method stub
		return Messages.SUNFLOWER_NAME;									//"Sunflower"
	}

	@Override
	public Plant copy(GameWorld game, int col, int row) 
	{
		// TODO Auto-generated method stub
		return new Sunflower(game, col, row);							//create sunflower
	}

	@Override
	public int getCost() 
	{
		// TODO Auto-generated method stub
		return COST;													//cost = 20
	}

	@Override
	protected String getSymbol() 
	{
		// TODO Auto-generated method stub
		return Messages.SUNFLOWER_SYMBOL;								//"S"
	}

	@Override
	public String getDescription() 
	{
		// TODO Auto-generated method stub
		return Messages.plantDescription(Messages.SUNFLOWER_NAME_SHORTCUT, COST, DAMAGE, ENDURANCE);		//sunflower's description
	}	

	@Override
	protected void delayedAction() 
	{		
		game.addSun();							//add sun 							
	}	
	
	@Override
	public void update()
	{						
			if(this.cooldown == 0)
			{
				this.delayedAction();						//execute delayedAction
				
				this.cooldown = GENERATE_COINS - 1;					//reset cooldown
			}
			
			else
			{
				this.cooldown--;						//update cooldown
			}
		}	
	}	
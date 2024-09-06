package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sun extends GameObject
{
	// Remember that a Sun is updated the very same cycle is added to the container
	public static final int SUN_LIVES = 11;
	
	private static final int SUN_COINS = 10;
	
	private static int generatedSuns;
	
	private static int catchedSuns;

	public Sun(GameWorld game, int col, int row)
	{		
		super(game, col, row, SUN_LIVES);		
	}
	
	public Sun()
	{
		
	}
	
	public static void resetCounters()
	{
		generatedSuns = 0;									//initial value
		
		catchedSuns = 0;										//initial value
	}
	 		
	@Override
	public boolean catchObject() 
	{
		// TODO add your code here
		
		game.addSunCoins(SUN_COINS);							//add suncoins
		
		this.kill();												//kill sun
		
		Sun.catchedSuns++;										//update catchedSun's counters
		
		return true;										//true: sun can be catched
	}

	@Override
	public boolean fillPosition() 
	{
		return false;						//if sun: false, else:true
	}

	@Override
	public boolean receiveZombieAttack(int damage) 
	{
		return false;								//false: 0 damage received by zombies
	}

	@Override
	public boolean receivePlantAttack(int damage) 
	{
		return false;								//false: 0 damage received by plants
	}

	@Override
	protected String getSymbol() 
	{
		return Messages.SUN_SYMBOL;						//"*"
	}

	@Override
	public String getDescription() 
	{
		return Messages.SUN_DESCRIPTION;				//"Sun"
	}

	@Override
	public void update() 
	{		
		this.lives--;									//update sun's lives
	}

	@Override
	public void onEnter() 
	{
		// TODO Auto-generated method stub
		
		Sun.generatedSuns++;							//update generatedSuns counter
	}

	@Override
	public void onExit() 
	{	
		//void
	}	
	
	public static int getCaughtSuns()
	{
		return catchedSuns;								//return catched suns
	}

	public static int getGeneratedSuns() 
	{
		// TODO Auto-generated method stub
		return generatedSuns;								//return generated suns
	}	 
}
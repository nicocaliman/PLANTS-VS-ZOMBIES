package tp1.p2.logic;

import java.util.Random;

import tp1.p2.logic.gameobjects.Sun;

public class SunsManager 
{

	private static final int COOLDOWN_RANDOM_SUN = 5;

	private GameWorld game;

	private Random rand;

	private int cooldown;	

	public SunsManager(GameWorld game, Random rand)
	{
		this.game = game;
		this.rand = rand;
		this.cooldown = COOLDOWN_RANDOM_SUN;
		// TODO add your code here
		
		Sun.resetCounters();
	}

	public int getCatchedSuns() 
	{		
		return Sun.getCaughtSuns();									//return caughtSuns counter
	}

	public int getGeneratedSuns()
	{		
		return Sun.getGeneratedSuns();								//return generatedSuns counter
	}

	public void update()
	{
		if (this.cooldown == 0) 
		{				
				this.addSun();											//add sun
			
				cooldown = COOLDOWN_RANDOM_SUN;							//reset cooldown
		}
		
		else
		{
			cooldown--;													//update cooldown
		}	
	}

	private int getRandomInt(int bound) 
	{
		return this.rand.nextInt(bound);					//return random int
	}

	public void addSun() 
	{		
		int col = getRandomInt(GameWorld.NUM_COLS);				//random column
		
		int row = getRandomInt(GameWorld.NUM_ROWS);				//random row
			
		game.addItem(new Sun(this.game, col, row));				//add sun to the game						
	}	
} 
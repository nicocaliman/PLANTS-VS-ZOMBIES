package tp1.p2.logic;

import java.util.Random;

import tp1.p2.control.Level;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;

/**
 * Manage zombies in the game.
 *
 */
public class ZombiesManager 
{
	private GameWorld game;

	private Level level;

	private Random rand;

	private int remainingZombies;
	
	private int zombiesAlived;

	public ZombiesManager(GameWorld game, Level level, Random rand) 
	{
		this.game = game;
		this.level = level;
		this.rand = rand;
		this.remainingZombies = level.getNumberOfZombies();
		this.zombiesAlived = 0;
	}

	/**
	 * Checks if the game should add (if possible) a zombie to the game.
	 * 
	 * @return <code>true</code> if a zombie should be added to the game.
	 */
	private boolean shouldAddZombie() 
	{
		return rand.nextDouble() < level.getZombieFrequency();
	}

	/**
	 * Return a random row within the board limits.
	 * 
	 * @return a random row.
	 */
	private int randomZombieRow() 
	{
		return rand.nextInt(GameWorld.NUM_ROWS);									//random row
	}

	private int randomZombieType() 
	{
		return rand.nextInt(ZombieFactory.getAvailableZombies().size());			//return random zombie type
	}

	public void update() 
	{
		addZombie();												//try to add zombie
	}

	public boolean addZombie() 
	{
		int row = randomZombieRow();
		return addZombie(row);
	}

	public boolean addZombie(int row)
	{
		boolean canAdd = getRemainingZombies() > 0 && shouldAddZombie() && isPositionEmpty(GameWorld.NUM_COLS, row);
		int zombieType = randomZombieType();

		if (canAdd)
		{
			// TODO add your code here
			
			Zombie newZombie = ZombieFactory.spawnZombie(zombieType, game, GameWorld.NUM_COLS, row);
			
			if(newZombie != null)
			{
				this.game.addItem(newZombie);						//add zombie to game's container
								
				this.zombiesAlived++;									//update zombiesAlived counter
			}			
		}
		
		return canAdd;												//true if zombie was successfully added
	}

	// TODO add your code here
	
	public int getRemainingZombies()
	{
		return this.remainingZombies;								//return remaining zombies
	}
	
	public boolean allZombiesDied()
	{
		return this.zombiesAlived == 0;								//true if zombiesAlived = 0
	}
	
	public void zombieDied()
	{	
		this.zombiesAlived--;									//update zombiesAlived counter
	}
	
	private boolean isPositionEmpty(int col, int row)
	{		
		return game.isValidZombiePosition(col,row);					//call isValidZombiePosition() from Game.java to check if there's no object in that position 
	}

	public void updateRemainingZombies() 
	{		
		if(this.game.aZ() != true)
		{
			this.remainingZombies--;									//update remainingZombies counter
		}		
	}	
}
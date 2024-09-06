package tp1.p2.logic;

import static tp1.p2.view.Messages.error;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.control.Level;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.view.Messages;

public class Game implements GameStatus, GameWorld 
{	
	public static final int INITIAL_SUNCOINS = 50;

	private long seed;

	private Level level;
	
	private int cycle;

	private GameObjectContainer container;

	private Deque<GameAction> actions;

	// TODO add your attributes here
	
	private boolean playerQuits;
	
	private Random rand;
	
	private int suncoins;

	private ZombiesManager zombiesManager;	
	
	private SunsManager sunsManager;
	
	private boolean playerDied;
	
	private boolean azCommand;
	
	private GameItem usa;
	
	public Game(long seed, Level level) 
	{
		this.seed = seed;
		this.level = level;
		this.container = new GameObjectContainer();									//create container
		reset();	
	}

	/**
	 * Resets the game.
	 */
	@Override
	public void reset() 
	{
		reset(this.level, this.seed);											//call reset(level, seed)
	}

	/**
	 * Resets the game with the provided level and seed.
	 * 
	 * @param level {@link Level} Used to initialize the game.
	 * @param seed Random seed Used to initialize the game.
	 */
	@Override
	public void reset(Level level, long seed) 
	{
		// TODO add your code here
		this.cycle = 0;															//initial cycles
		
		this.actions = new ArrayDeque<>();
		
		this.suncoins = INITIAL_SUNCOINS;										//initial suncoins(50)
		
		this.playerQuits = false;												//playerQuits(initially no)
		
		this.level = level;
		
		this.seed = seed;
		
		this.rand = new Random(seed);
		
		this.container.clear();													//clear container(remove all objects)
		
		this.zombiesManager = new ZombiesManager(this, level, rand);	
		
		this.sunsManager = new SunsManager(this, rand);
		
		System.out.println(String.format(Messages.CONFIGURED_LEVEL, level.name()));
		
		System.out.println(String.format(Messages.CONFIGURED_SEED, seed));
		
		/*
		 	Level: x
			Random generator initialized with seed: x		 
		 */		
	}

	/**
	 * Executes the game actions and update the game objects in the board.
	 * 
	 */
	@Override
	public void update() 
	{

		// 1. Execute pending actions
		executePendingActions();

		// 2. Execute game Actions
		// TODO add your code here
		
		this.zombiesManager.update();
		
		this.sunsManager.update();

		// 3. Game object updates
		// TODO add your code here
		
		this.container.update();		

		// 4. & 5. Remove dead and execute pending actions
		boolean deadRemoved = true;
		while (deadRemoved || areTherePendingActions())
		{
			// 4. Remove dead
			deadRemoved = this.container.removeDead();

			// 5. execute pending actions
			executePendingActions();
		}

		this.cycle++;

		// 6. Notify commands that a new cycle started
		Command.newCycle();

	}

	private void executePendingActions()
	{
		while (!this.actions.isEmpty()) 
		{
			GameAction action = this.actions.removeLast();
			action.execute(this);
		}
	}

	private boolean areTherePendingActions() 
	{
		return this.actions.size() > 0;
	}

	
	@Override
	public boolean isFullyOcuppied(int col, int row)
	{
		return this.container.isFullyOccupied(col, row);
	}
	// TODO add your code here

	public boolean isFinished()
	{
		if((this.zombieArrived() == true) || (this.allZombiesDied() && this.getRemainingZombies() == 0))
		{
			return true;				//true if a zombie arrived or if all zombies died
		}
		
		return false;					
	}
	
	@Override
	
	public boolean isPlayerDied()
	{
		if(this.playerDied == true)			
		{
			return true;					//true if player died
		}
		
		return false;						//false if player did not die
	}
	
	private void playerDied()
	{
		this.playerDied = true;				//set playerDied = true
	}
	
	@Override
	
	public boolean isPlayerQuits()
	{
		if(this.playerQuits == true)
		{
			return true;						//true if player leaves the game
		}
		
		return false;					//false if player does not leave the game
	}
	
	@Override
	
	public void playerQuits()
	{
		this.playerQuits = true;				//set playerQuits = true
	}
	
	@Override
	
	public boolean isPositionEmpty(int col, int row)
	{		
		return this.container.isPositionEmpty(col, row);						//true if an object is in that position
	}
	
	private boolean isPositionInLimits(int col, int row)
	{
		if((col >= -1 && col <= GameWorld.NUM_COLS) && (row >= 0 && row < GameWorld.NUM_ROWS))
		{
			return true;					//true if position is in limits
		}
		
		return false;					//false if position is not in limits
	}
	
	@Override
	
	public void addGameObject(GameObject object)
	{
		this.container.add(object);							//add object to game's container
	}	
	
	@Override
	
	public boolean tryToBuy(int coins)
	{
		if(this.suncoins >= coins)							
		{
			this.suncoins -= coins;							//remove plant's cost from player's total suncoins
			
			return true;									//true if player has enough coins to purchase the plant
		}
		
		return false;											//false if player cannot purchase the plant
	}
	
	@Override
	
	public int getCycle()
	{
		return this.cycle;								//return current cycle
	}
	
	@Override
	
	public int getSuncoins()
	{
		return this.suncoins;								//return current player's suncoins
	}
	
	@Override
	
	public int getRemainingZombies()
	{
		return this.zombiesManager.getRemainingZombies();				//return remaining zombies
	}
	
	@Override
	
	public void addSunCoins(int suncoins)
	{
		this.suncoins += suncoins;							//add suncoins to player's total amount
	}
	
	@Override
	
	public boolean allZombiesDied()
	{
		return this.zombiesManager.allZombiesDied();				//true if all zombies died
	}
	
	@Override 
	
	public String positionToString(int col, int row)
	{
		return this.container.positionToString(col, row);				//return "" if position is empty or Object[lives]
	}
	
	@Override
	
	public boolean attackPlant(int col, int row, int damage)
	{
		//GameObject object = this.container.getGameItemInPosition(col, row);	
		
		List<GameObject> object = this.container.getGameItemsInPosition(col, row);
		
		int i = 0;
		
		while(i < object.size())
		{
			if(object.get(i).fillPosition() == true)
			{
				return object.get(i).receiveZombieAttack(damage);
			}
			
			i++;
		}		
		return false;		
	}
	
	public boolean attackZombie(int col, int row, int damage)
	{
		//GameObject object = this.container.getGameItemInPosition(col, row);
		
		List<GameObject> object = this.container.getGameItemsInPosition(col, row);
		
		int i = 0;
		
		while(i < object.size())
		{
			if(object.get(i).fillPosition() == true)
			{
				return object.get(i).receivePlantAttack(damage);
			}
			
			i++;
		}		
		return false;	
	}
	
	public boolean execute(Command command)
	{				
		ExecutionResult commandResult = command.execute(this);		//execute command
		
		return commandResult.draw();									//true if game must be printed
	}
	
	@Override 
	
	public boolean isValidPlantPosition(int col, int row)
	{
		if(this.isPositionInLimits(col, row) && col != GameWorld.NUM_COLS && (this.isPositionEmpty(col, row) == true || this.container.isFullyOccupied(col, row) == false))
		{
			return true;				//true if plant's position is valid
		}
		
		return false;				//false if plant's position is not valid
	}
	
	@Override
	
	public boolean isValidZombiePosition(int col, int row)
	{
		GameObject object = this.container.getGameItemInPosition(col, row);
		
		if((this.isPositionInLimits(col, row)) && (this.container.isPositionEmpty(col, row)) || object.fillPosition() == false)
		{
			return true;					//true if zombie's position is valid
		}
		
		return false;						//false if zombie's position is not valid
	}	

	@Override
	
	public void zombieDied()
	{
		this.zombiesManager.zombieDied();			
	}
	
	
	@Override
	
	public boolean zombieArrived()
	{
		
		for(int i = 0; i < GameWorld.NUM_ROWS; i++)
		{	
			GameObject zombieA = this.container.getGameItemInPosition(-1, i);
						
			if(zombieA != null)
			{
				this.playerDied();
				
				return true;
			}
		}
		
		return false;
	}	
	
	@Override 
	
	public boolean tryToCatchObject(int col, int row)
	{
		return this.container.catchObject(col, row);
	}
	
	@Override
	
	public boolean addItem(GameObject object)
	{
		if(this.isPositionInLimits(object.getCol(), object.getRow()) == false || object.fillPosition() && this.isFullyOcuppied(object.getCol(), object.getRow()))
		{
			System.out.println(error(Messages.INVALID_POSITION));			
		}
		
			this.addGameObject(object);
		
		
		//this.addGameObject(object);
		
		return this.container.isObjectInPosition(object.getCol(), object.getRow());
	}
	
	@Override
	
	public int getGeneratedSuns()
	{
		return this.sunsManager.getGeneratedSuns();
	}
	
	@Override
	
	public void addSun()
	{
		this.sunsManager.addSun();
	}
	
	@Override
	
	public int getCaughtSuns()
	{
		return this.sunsManager.getCatchedSuns();
	}

	@Override
	public void updateRemainingZombies()
	{
		// TODO Auto-generated method stub
		
		this.zombiesManager.updateRemainingZombies();
		
	}
	
	@Override
	
	public void updateZombiesAlived()
	{
		this.zombiesManager.zombieDied();
	}	
	
	@Override
	public void azCommand()
	{		
		this.azCommand = true;
	}
	
	@Override
	public boolean aZ()
	{
		if(this.azCommand == true)
		{
			this.azCommand = false;
			
			return true;
		}
		
		return false;
	}

	@Override
	public void pushAction(GameAction gameAction) 
	{	
		// TODO Auto-generated method stub
		
		this.actions.addLast(gameAction);		
	}	
}
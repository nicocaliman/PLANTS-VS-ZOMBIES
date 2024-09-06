package tp1.p2.logic;

import tp1.p2.control.Level;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;

public interface GameWorld
{

	public static final int NUM_ROWS = 4;

	public static final int NUM_COLS = 8;

	// TODO add your code here

	void addSun();

	boolean tryToCatchObject(int col, int row);

	boolean addItem(GameObject object);	
	
	// TODO add your code here
	
	void playerQuits();
	
	void update();
	
	void reset();
	
	void reset(Level level, long seed);
	
	boolean isPositionEmpty(int col, int row);
	
	boolean attackPlant(int col, int row, int damage);
	
	boolean attackZombie(int col, int row, int damage);
	
	void addSunCoins(int suncoins);
	
	void addGameObject(GameObject object);
	
	boolean tryToBuy(int coins);
	
	boolean zombieArrived();
	
	void zombieDied();
	
	boolean isValidPlantPosition(int col, int row);
	
	boolean isValidZombiePosition(int col, int row);

	void updateRemainingZombies();

	void updateZombiesAlived();

	boolean isFullyOcuppied(int col, int row);	
	
	void azCommand();

	boolean aZ();

	void pushAction(GameAction gameAction);
}	
package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;

public abstract class Plant extends GameObject
{
	protected Plant(GameWorld game, int col, int row, int endurance)
	{
		super(game, col, row, endurance);				
	}
	
	public Plant(GameWorld game, int col, int row, int endurance, int cooldown)
	{
		super(game, col ,row, endurance, cooldown);
	}
	
	public Plant()
	{
		
	}
	
	 abstract protected String getName();
	 
	 abstract public Plant copy(GameWorld game, int col, int row);
	 
	 abstract public int getCost();
	 	 
	 @Override
	 public void onEnter()
	 {
		//void 
	 }
	 
	 @Override
	 public void onExit()
	 {
		//void
	 }
	 
	 @Override
	 public boolean receivePlantAttack(int damage)
	 {
		 return false;									//false: plant does not receive plant's damage
	 }
	 
	 @Override
	 public boolean receiveZombieAttack(int damage)
	 {
		 super.receiveDamage(damage); 					//receive damage
		 
		return true;										//true: plant can receive zombie's damage
	 }		 
}
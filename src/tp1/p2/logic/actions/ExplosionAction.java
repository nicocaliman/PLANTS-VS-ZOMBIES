package tp1.p2.logic.actions;

import tp1.p2.logic.GameWorld;

public class ExplosionAction implements GameAction 
 {

	private int col;

	private int row;

	private int damage;
	
	private boolean attackZombies;

	public ExplosionAction(int col, int row, int damage) 			//cherry bomb (explosion action) constructor
	{
		this.col = col;
		this.row = row;
		this.damage = damage;
		
		this.attackZombies = true;
	}
	
	public ExplosionAction(int col, int row, int damage, boolean attackZombies)			//explosive zombie (explosion action) constructor
	{
		this.col = col;
		
		this.row = row;
		
		this.damage = damage;
		
		this.attackZombies = attackZombies;
	}

	@Override
	public void execute(GameWorld game)
	{		
		for(int i = this.row - 1; i <= this.row + 1; i++)
		{
			for(int j = this.col - 1; j <= this.col + 1; j++)
			{
				if(this.attackZombies == true)						//if plant damage
				{
					game.attackZombie(j, i, damage);				//attack zombie
				}
				
				else											//if zombie damage
				{
					game.attackPlant(j, i, damage);				//attack plant
				}
			}
		}		
	}	

}

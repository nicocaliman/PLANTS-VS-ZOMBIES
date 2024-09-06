package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.logic.GameWorld;

public class ZombieFactory
{

	/* @formatter:off */
	private static final List<Zombie> AVAILABLE_ZOMBIES = Arrays.asList(
		new Zombie(),
		new BucketHead(),
		new Sporty(),
		new ExplosiveZombie()
	);
	/* @formatter:on */

	public static boolean isValidZombie(int zombieIdx) 
	{
		return zombieIdx >= 0 || zombieIdx < AVAILABLE_ZOMBIES.size();				//true if idx is in <0 - 3> 
	}

	public static Zombie spawnZombie(int zombieIdx, GameWorld game, int col, int row) 
	{
		if (!isValidZombie(zombieIdx)) 									//if users index is not valid	
		{
			return null;												//zombie can't be spawned
		}		
		
		//Zombie z = AVAILABLE_ZOMBIES.get(zombieIdx);					//store the zombie in z	
		
		Zombie z = create(zombieIdx, game, col, row);
		
		return z;
		
		//return z.copy(game, col, row);									//return created zombie		
	}	

	public static List<Zombie> getAvailableZombies() 
	{
		return Collections.unmodifiableList(AVAILABLE_ZOMBIES);
	}
	
	public static Zombie create(int iDx, GameWorld game, int col, int row)
	{
		switch(iDx)
		{
			case 0:
				return new Zombie(game, col, row);
				
			case 1:
				return new BucketHead(game, col, row);
				
			case 2:
				return new Sporty(game, col, row);
				
			case 3:
				return new ExplosiveZombie(game, col, row);
				
				default:
					return null;
		}
	}

	/*
	 * Avoid creating instances of this class
	 */
	private ZombieFactory() 
	{
	}
}
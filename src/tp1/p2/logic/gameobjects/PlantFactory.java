
package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tp1.p2.logic.GameWorld;

public class PlantFactory
{

	/* @formatter:off */
	private static final List<Plant> AVAILABLE_PLANTS = Arrays.asList(
		new Sunflower(),
		new Peashooter(),
		new WallNut(),
		new CherryBomb()
	);
	/* @formatter:on */

	public static boolean isValidPlant(String plantName) 
	{
		for (Plant p : AVAILABLE_PLANTS) 							//traverse list
		{			
			if(p.getName().charAt(0) == plantName.toUpperCase().charAt(0))							
			{
				return true;									//true if introduced name/symbol is identic to one of the AVAILABLE_PLANTS
			}
		}

		return false;			//false if introduced name/symbol is not identic to one of the AVAILABLE_PLANTS
	}

	public static Plant spawnPlant(String plantName, GameWorld game, int col, int row) 
	{		
		for(Plant p : AVAILABLE_PLANTS)												//traverse list of AVAILABLE_PLANTS
		{
			if(p.getSymbol().toUpperCase().charAt(0) == plantName.toUpperCase().charAt(0))		//if user's type of plant exists						
			{				
				return p.copy(game, col, row);															//return created plant
			}
		}
	
		return null;																//null if user's type of plant does not exist
	}

	public static Iterable<Plant> getAvailablePlants() 
	{
		return Collections.unmodifiableList(AVAILABLE_PLANTS);
	}

	/*
	 * Avoid creating instances of this class
	 */
	private PlantFactory() 
	{
		
	}
}
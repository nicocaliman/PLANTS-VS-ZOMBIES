package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class AddPlantCommand extends Command implements Cloneable 
{

	private int col;

	private int row;

	private String plantName;

	private boolean consumeCoins;
	
	private boolean error;

	public AddPlantCommand() 
	{
		this(true);											//call AddPlantCommand(boolean consumeCoins)
	}

	public AddPlantCommand(boolean consumeCoins) 
	{
		super();											//call Command class constructor			
		
		this.consumeCoins = consumeCoins;
	}

	@Override
	protected String getName() 
	{
		return Messages.COMMAND_ADD_NAME;					//"add"
	}

	@Override
	protected String getShortcut() 
	{
		return Messages.COMMAND_ADD_SHORTCUT;				//"a"
	}

	@Override
	public String getDetails() 
	{
		return Messages.COMMAND_ADD_DETAILS;				//"[a]dd <plant> <col> <row>"
	}

	@Override
	public String getHelp() 
	{	
		return Messages.COMMAND_ADD_HELP;					//"add a plant in position (col, row)"
	}


	@Override
	public ExecutionResult execute(GameWorld game) 
	{
		// TODO add your code here		
		
		if(this.plantName == null)
		{
			ExecutionResult e = new ExecutionResult(error(Messages.COMMAND_PARAMETERS_MISSING));	//error message
			
			System.out.println(e.errorMessage());
			
			return e;
		}
		
		if(PlantFactory.isValidPlant(plantName) == false && error == true)																		//if plantName is not introduce by the user
		{			
			ExecutionResult e = new ExecutionResult(error(Messages.INVALID_GAME_OBJECT));
			
			System.out.println(e.errorMessage());
			
			return e;																				//unsuccessful command(do not print)
		}	
		
		if(game.isValidPlantPosition(col, row) == false || error == true)											//if user inserts non valid plant's position
		{			
			ExecutionResult e = new ExecutionResult(error(Messages.INVALID_POSITION));
				
			System.out.println(e.errorMessage());
			
			return e;		
		}
		
		Plant newPlant = PlantFactory.spawnPlant(plantName, game, col, row);				//store in newPlant the spawnedPlant
		
		if(newPlant == null)																		//if plant could not have been created
		{
			ExecutionResult e = new ExecutionResult(error(Messages.INVALID_GAME_OBJECT));			//"ERROR: Invalid object"
			
			System.out.println(e.errorMessage());
			
			return e;																//unsuccessful command(do not print)
		}
		
		if(this.consumeCoins == true)												//if consume coins
		{
			if(game.tryToBuy(newPlant.getCost()))									//if there are enough coins to buy the plant
			{
				game.addItem(newPlant);
				
				game.update();														//update game
			}
		}
		
		else																//if cheatplant command(consume = false)
		{
			game.addItem(newPlant);											//add Item to container
			
			game.update();													//update game			
		}
		
		return new ExecutionResult(true);											//print game(successful command)
	}

	@Override
	public Command create(String[] parameters) 
	{
		// TODO add your code here
		
		this.error = false;
		
		if(parameters.length == 4)											//if number of parameters is correct (= 4)
		{				
			try 
			{				
				this.plantName = parameters[1];									//store plantName
			
				this.col = Integer.parseInt(parameters[2]);						//store col
				
				this.row = Integer.parseInt(parameters[3]);						//store row
				
				if(PlantFactory.isValidPlant(plantName) == true)				//if is valid plant
				{								
					try 
					{
						
						AddPlantCommand plant = new AddPlantCommand();
						AddPlantCommand newPlant = (AddPlantCommand)plant.clone();			//clone command
						
						return newPlant;													//return cloned command
					} 
					
					catch (CloneNotSupportedException e)
					{
						e.getStackTrace();					
					}				
				}	
				
				else
				{
					error = true;
				}
			}
			
			catch(NumberFormatException e)
			{								
				error = true;
			}					
		}			
		
		else
		{
			this.plantName = null;		
		}
		
		return null;
	}

}
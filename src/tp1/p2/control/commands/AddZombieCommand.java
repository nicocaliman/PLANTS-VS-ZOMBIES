package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;

public class AddZombieCommand extends Command implements Cloneable
{
	private int zombieIdx;

	private int col;

	private int row;
	
	public AddZombieCommand() 
	{
		super();													//call Command class constructor
	}

	private AddZombieCommand(int zombieIdx, int col, int row) 
	{	this();
		this.zombieIdx = zombieIdx;
		this.col = col;
		this.row = row;
	}

	@Override
	protected String getName() 
	{
		return Messages.COMMAND_ADD_ZOMBIE_NAME;					//"addZombie"
	}

	@Override
	protected String getShortcut() 
	{
		return Messages.COMMAND_ADD_ZOMBIE_SHORTCUT;				//"az"
	}

	@Override
	public String getDetails()
	{
		return Messages.COMMAND_ADD_ZOMBIE_DETAILS;					//"[a]dd[Z]ombie <idx> <col> <row>
	}

	@Override
	public String getHelp() 
	{
		return Messages.COMMAND_ADD_ZOMBIE_HELP;					//"add a zombie in position (col, row)"
	}

	@Override
	public ExecutionResult execute(GameWorld game) 
	{
		// TODO add your code here	

		if(ZombieFactory.isValidZombie(this.zombieIdx) == false)																		//if plantName is not introduce by the user
		{
			ExecutionResult e = new ExecutionResult(error(Messages.COMMAND_PARAMETERS_MISSING));		//"ERROR: Missing parameters"
			
			System.out.println(e.errorMessage());
			
			return e;																				//unsuccessful command(do not print)
		}
		
		if(game.isValidZombiePosition(col, row) == false)											//if user inserts non valid plant's position
		{
			ExecutionResult e = new ExecutionResult(error(Messages.INVALID_POSITION));			//"ERROR: Invalid position"
			
			System.out.println(e.errorMessage());
			
			return e;																		//unsuccessful command (do not print)
		}		
		
		Zombie newZombie = ZombieFactory.spawnZombie(this.zombieIdx, game, this.col, this.row);				//store in newPlant the spawnedPlant
		
		if(newZombie == null)																		//if plant could not have been created
		{
			ExecutionResult e = new ExecutionResult(error(Messages.INVALID_GAME_OBJECT));			//"ERROR: Invalid object"
			
			System.out.println(e.errorMessage());
			
			return e;																//unsuccessful command(do not print)
		}		
		
		game.azCommand();											//do not update remaining zombies
						
		game.addItem(newZombie);										//add item to game's container			
		
		game.update();														//update game		
		
		return new ExecutionResult(true);											//print game(successful command)
	}

	@Override
	public Command create(String[] parameters) 
	{		
		if(parameters.length == 4)											//if number of parameters is correct (= 4)
		{			
			this.zombieIdx = Integer.parseInt(parameters[1]);				//store zombie type
			
			this.col = Integer.parseInt(parameters[2]);						//store col
			
			this.row = Integer.parseInt(parameters[3]);						//store row
						
			if(ZombieFactory.isValidZombie(zombieIdx))				//if is zombie is valid
			{								
				try 
				{					
					AddZombieCommand zombie = new AddZombieCommand(this.zombieIdx, this.col, this.row);
					AddZombieCommand newZombie = (AddZombieCommand)zombie.clone();			//clone command
					
					return newZombie;													//return cloned command
				} 
				
				catch (CloneNotSupportedException e)
				{
					// TODO Auto-generated catch block
										
					e.printStackTrace();
				}
			}
		}
		
		return null;		
	}
}

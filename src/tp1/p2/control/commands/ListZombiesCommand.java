package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;

public class ListZombiesCommand extends Command
{
	public ListZombiesCommand()
	{
		super();														//call superclass(Command) constructor
	}	

	@Override
	protected String getName() 
	{
		return Messages.COMMAND_LIST_ZOMBIES_NAME;						//"listZombies"
	}

	@Override
	protected String getShortcut()
	{
		return Messages.COMMAND_LIST_ZOMBIES_SHORTCUT;					//"lz"
	}

	@Override
	public String getDetails() 
	{
		return Messages.COMMAND_LIST_ZOMBIES_DETAILS;					//"[l]ist[Z]ombies"
	}

	@Override
	public String getHelp()
	{
		return Messages.COMMAND_LIST_ZOMBIES_HELP;						//"print the list of available zombies"
	}

	@Override
	public ExecutionResult execute(GameWorld game) 
	{
		// TODO add your code here
		
		System.out.println(Messages.AVAILABLE_ZOMBIES);					//"Available zombies:"
		
		for(Zombie z: ZombieFactory.getAvailableZombies())				//traverse available zombies list
		{			
			System.out.println(z.getDescription());						//print zombie's description
		}	
		
		/*
		  	Zombie: speed='2', damage='1', endurance='5'
			BucketHead Zombie: speed='4', damage='1', endurance='8'
			Sporty Zombie: speed='1', damage='1', endurance='2'
			Explosive Zombie: speed='2', damage='1', endurance='5'
		 */	
		
		return new ExecutionResult(false);								//do not print game (successful command)
	}

}
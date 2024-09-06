package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class ListPlantsCommand extends Command 
{	
	public ListPlantsCommand()
	{
		super();								//call superclass(Command) constructor
	}
	
	@Override
	protected String getName() 
	{		
		return Messages.COMMAND_LIST_NAME;			//"list"
	}
	
	@Override
	protected String getShortcut() 
	{
		return Messages.COMMAND_LIST_SHORTCUT;		//"l"
	}
	@Override
	public String getDetails()
	{
		return Messages.COMMAND_LIST_DETAILS;		//"[l]ist"
	}
	@Override
	public String getHelp()
	{
		return Messages.COMMAND_LIST_HELP;			//"print the list of available plants"
	}

	@Override
	public ExecutionResult execute(GameWorld game) 
	{
		System.out.println(Messages.AVAILABLE_PLANTS);		//"Available plants:"
		// TODO add your code here
		
		for(Plant p : PlantFactory.getAvailablePlants())	//traverse available plants list
		{			
			System.out.println(p.getDescription());			//print plant's description				
		}		
		
		/*
		 * [P]eashooter: x cost, x damage, x endurance
		 * [S]unflower: x cost, x damage, x endurance
		 */	
		
		return new ExecutionResult(false);					//do not print game (successful command)					
	}

}
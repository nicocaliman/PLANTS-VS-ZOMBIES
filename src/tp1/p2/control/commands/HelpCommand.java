package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class HelpCommand extends Command
{
	public HelpCommand()
	{
		super();								//call superclass(Command) constructor
	}
	

	@Override
	protected String getName() 
	{
		return Messages.COMMAND_HELP_NAME;					//"help"
	}

	@Override
	protected String getShortcut() 
	{
		return Messages.COMMAND_HELP_SHORTCUT;				//"h"
	}

	@Override
	public String getDetails() 
	{
		return Messages.COMMAND_HELP_DETAILS;				//"[h]elp"
	}

	@Override
	public String getHelp()
	{
		return Messages.COMMAND_HELP_HELP;					//"print this help message"
	}

	@Override
	public ExecutionResult execute(GameWorld game) 
	{
		StringBuilder buffer = new StringBuilder(Messages.HELP_AVAILABLE_COMMANDS);					//"Available commands:"

		for (Command command : Command.getAvailableCommands())
		{
			/* @formatter:off */

			// TODO add your code here
			
			buffer.append(Messages.LINE_SEPARATOR);
			
			buffer.append(command.getDetails()).append(Messages.HELP_DETAILS_COMMAND_HELP_SEPARATOR).append(command.getHelp());

			/* @formatter:on */
		}
		
		/*
		 * 	[a]dd <plant> <col> <row>: add a plant in position (col, row)
			[l]ist: print the list of available plants
			[r]eset [<level> <seed>]: start a new game (if level and seed are both provided, they are used to initialize the game)
			[h]elp: print this help message
			[e]xit: terminate the program
			[n]one | "": skip user action for this cycle
			[l]ist[Z]ombies: print the list of available zombies
			[a]dd[Z]ombie <idx> <col> <row>: add a zombie in position (col, row)
			[C]heat[P]lant <plant> <col> <row>: add a plant in position (col, row) without consuming suncoins
		 * 
		 * */

		System.out.println(buffer.toString());			

		return new ExecutionResult(false);				//do not print (successful command)
	}
}
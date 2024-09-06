package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ExitCommand extends Command 
{
	public ExitCommand()
	{
		super();								//call superclass(Command) constructor
	}
	
	@Override
	protected String getName() 
	{
		return Messages.COMMAND_EXIT_NAME;			//"exit"
	}

	@Override
	protected String getShortcut() 
	{
		return Messages.COMMAND_EXIT_SHORTCUT;		//"e"
	}

	@Override
	public String getDetails() 
	{
		return Messages.COMMAND_EXIT_DETAILS;		//"[e]xit"
	}

	@Override
	public String getHelp() 
	{
		return Messages.COMMAND_EXIT_HELP;			//"terminate the program"
	}

	@Override
	public ExecutionResult execute(GameWorld game)
	{
		game.playerQuits();							//set playerQuits = true	
		
		return new ExecutionResult(false);			//do not draw(successful command)
	}
}
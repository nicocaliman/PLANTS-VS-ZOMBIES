package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class NoneCommand extends Command 
{

	public NoneCommand() 
	{
		// default command
		super(true);
	}

	@Override
	protected String getName()
	{
		return Messages.COMMAND_NONE_NAME;						//"none"
	}

	@Override
	protected String getShortcut() 
	{
		return Messages.COMMAND_NONE_SHORTCUT;					//"n"
	}

	@Override
	public String getDetails()
	{
		return Messages.COMMAND_NONE_DETAILS;					//"[n]one | \"\"
	}

	@Override
	public String getHelp()
	{
		return Messages.COMMAND_NONE_HELP;						//skip user action for this cycle
	}

	@Override
	public ExecutionResult execute(GameWorld game) 
	{
		// TODO add your code here
		
		game.update();											//update game
		
		return new ExecutionResult(true);						//print game(successful command)
	}

}
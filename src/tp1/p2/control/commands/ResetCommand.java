package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.control.Level;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ResetCommand extends Command 
{

	private Level level;

	private long seed;
	
	private boolean error = false;

	public ResetCommand() 
	{
		super();										//call constructor from super class (command)
	}

	public ResetCommand(Level level, long seed) 
	{			
		this();										//call constructor from super class (command)
		
		this.level = level;
		this.seed = seed;
	}

	@Override
	protected String getName() 
	{
		return Messages.COMMAND_RESET_NAME;				//"reset"
	}

	@Override
	protected String getShortcut() 
	{
		return Messages.COMMAND_RESET_SHORTCUT;			//"r"
	}

	@Override
	public String getDetails() 
	{
		return Messages.COMMAND_RESET_DETAILS;			//"[r]eset [<level><seed>]"
	}

	@Override
	public String getHelp() 
	{
		return Messages.COMMAND_RESET_HELP;				//"start a new game (if level and seed are both provided, they are used to inizialize the game)"
	}

	@Override
	public ExecutionResult execute(GameWorld game)
	{
		// TODO add your code here					
		
 		if(this.level == null && error != true)
		{
			game.reset();							//if user does not insert level parameter
		}	
 		
 		else if(this.level == null && error == true)
 		{
 			return new ExecutionResult(false);
 		}
				
		else
		{
			game.reset(this.level, this.seed);		//if user inserts level + seed
		}			
		
		return new ExecutionResult(true);				//print(successful command)
	}

	@Override
	public Command create(String[] parameters) 
	{
		// TODO add your code here
		
		if(parameters.length == 1)						//if string[] has 1 element... 
		{			
			return new ResetCommand();		//create ResetCommand(level, seed) object
		}
		
		else if(parameters.length == 3)
		{
			try 
			{
				this.level = Level.valueOfIgnoreCase(parameters[1]);			//return null if non match level
				
				this.seed = Long.parseLong(parameters[2]);						//parse the string as a decimal long
			
				return new ResetCommand(this.level, this.seed);					//create resetCommand() object
			}
			
			catch(NumberFormatException e)
			{
				System.out.println(error(Messages.INVALID_COMMAND));
				
				error = true;
								
				return null;
			}			
		}			
			
		System.out.println(error(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER));			//error message
				
		return null;		
	}
}
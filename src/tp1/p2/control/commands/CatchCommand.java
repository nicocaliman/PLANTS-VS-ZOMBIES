package tp1.p2.control.commands;

import static tp1.p2.view.Messages.error;

import tp1.p2.control.Command;
import tp1.p2.control.ExecutionResult;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class CatchCommand extends Command 
{
	private static boolean caughtSunThisCycle = false;

	private int col;

	private int row;
	
	private static boolean error;

	public CatchCommand() 
	{
		super(false);									//call Command class constructor
	}
	
	@Override
	protected void newCycleStarted() 
	{
		caughtSunThisCycle = false;						//set caughtSunThisCycle = false
	}

	private CatchCommand(int col, int row)
	{
		this();										//call CatchCommand() constructor
		
		error = false;
				
		this.col = col;
		this.row = row;		
	}

	@Override
	protected String getName() 
	{
		return Messages.COMMAND_CATCH_NAME;					//"catch"
	}

	@Override
	protected String getShortcut()
	{
		return Messages.COMMAND_CATCH_SHORTCUT;				//"c"
	}

	@Override
	public String getDetails()
	{
		return Messages.COMMAND_CATCH_DETAILS;				//"[C]atch <col> <row>"
	}

	@Override
	public String getHelp() 
	{
		return Messages.COMMAND_CATCH_HELP;					//"catch a sun, if possible, in position (col, row)"
	}

	@Override
	public ExecutionResult execute(GameWorld game)
	{
		// TODO add your code here
		
		if(error == true)
		{
			return new ExecutionResult(false);				//do not print
		}
		
		if(caughtSunThisCycle == true)						//if sun was caught this cycle
		{			
			ExecutionResult result = new ExecutionResult(error(Messages.SUN_ALREADY_CAUGHT));		//error message
			
			System.out.println(result.errorMessage());		
			
			return result;							
			
		}
		
		else 
		{
			if(game.tryToCatchObject(col, row) == true)							//if sun is caught
			{						
				CatchCommand.caughtSunThisCycle = true;								//set caughtSunThisCycle = true				
			}
			
			else
			{
				ExecutionResult result = new ExecutionResult(error(Messages.INVALID_POSITION));		//error message
				
				System.out.println(result.errorMessage());		
				
				return result;
			}
		}	
		
		return new ExecutionResult(true);										//print game(successful command)
	}

	@Override
	public Command create(String[] parameters)
	{
		// TODO add your code here
		
		if(parameters.length == 3)								//if correct parameters
		{			
			try 
			{
				this.col = Integer.parseInt(parameters[1]);						//store col
				
				this.row = Integer.parseInt(parameters[2]);						//store row
				
				return new CatchCommand(this.col, this.row);					//create CatchCommand object
				
			}
			
			catch(NumberFormatException e)
			{
				error = true;
				
				System.out.println(error(Messages.INVALID_POSITION));		//error message
			}
		}
		
		return null;						//null if object could not be created
	}
}
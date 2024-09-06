package tp1.p2.control.commands;

import tp1.p2.view.Messages;

public class AddPlantCheatCommand extends AddPlantCommand 
{			
	public AddPlantCheatCommand()
	{
		super(false);											//call superclass(AddPlantCommand) constructor
	}	

	@Override
	protected String getName() 
	{
		// TODO Auto-generated method stub
		return Messages.COMMAND_CHEAT_PLANT_NAME;			//"cheatPlant"
	}

	@Override
	protected String getShortcut() 
	{
		// TODO Auto-generated method stub
		return Messages.COMMAND_CHEAT_PLANT_SHORTCUT;		//"cp"
	}

	@Override
	public String getDetails() 
	{
		// TODO Auto-generated method stub
		return Messages.COMMAND_CHEAT_PLANT_DETAILS;		//"[C]heat[P]lant <plant> <col> <row>"
	}

	@Override
	public String getHelp() 
	{
		// TODO Auto-generated method stub
		return Messages.COMMAND_CHEAT_PLANT_HELP;			//"add a plant in position (col,row) without consuming suncoins"	
	}
}
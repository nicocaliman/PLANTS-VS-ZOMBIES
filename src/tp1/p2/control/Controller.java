package tp1.p2.control;

import static tp1.p2.view.Messages.debug;
import static tp1.p2.view.Messages.error;

import java.util.Scanner;

import tp1.p2.logic.Game;
import tp1.p2.view.GamePrinter;
import tp1.p2.view.Messages;

/**
 * Accepts user input and coordinates the game execution logic.
 */
public class Controller 
{	
	//ATTRIBUTES

	private Game game;

	private Scanner scanner;

	private GamePrinter gamePrinter;

	//CONSTRUCTOR
	
	public Controller(Game game, Scanner scanner) 
	{
		this.game = game;
		this.scanner = scanner;
		this.gamePrinter = new GamePrinter(game);
	}
	
	//METHODS

	/**
	 * Draw / Paint the game.
	 */
	private void printGame()
	{
		System.out.println(gamePrinter);
	}

	/**
	 * Prints the final message once the match is finished.
	 */
	public void printEndMessage() 
	{
		System.out.println(gamePrinter.endMessage());
	}

	/**
	 * Show prompt and request command.
	 *
	 * @return the player command as words
	 */
	private String[] prompt() 
	{
		System.out.print(Messages.PROMPT);
		String line = scanner.nextLine();
		String[] words = line.toLowerCase().trim().split("\\s+");

		System.out.println(debug(line));

		return words;															//return word(s)
	}

	/**
	 * Runs the game logic.
	 */
	public void run() 
	{
		boolean refreshDisplay = true;

		while (!game.isFinished() && !game.isPlayerQuits())						//while player does not quit (playerQuits = false) & player did not kill all zombies or a zombie arrived at column 0 							
		{

			// 1. Draw
			if (refreshDisplay) 												//if refreshDisplay = true
			{
				printGame();													//print
			}

			// 2. User action

			String[] words = prompt();											//show prompt and request command

			if (words.length == 0) 												//if empty string[]...
			{
				System.out.println(error(Messages.UNKNOWN_COMMAND));			//Unknown command
			} 
			
			else 																//else
			{
				Command command = Command.parse(words);							//parse(seeks the command that corresponds to users entrance(words)) -> store in command	
				
				if (command != null) 											//if command is found...
				{
					// 3-4. Game Action & Update
					refreshDisplay = game.execute(command);						//execute(executes command's action, modifying the game) -> store in refreshDisplay
				}
				
				else 															//else
				{
					refreshDisplay = false;										//do not refresh
				}
			}
		}														

		if (refreshDisplay)																//if loop ends and refreshDisplay is still true
		{
			printGame();																//print
		}

		printEndMessage();																//print end message
	}
}
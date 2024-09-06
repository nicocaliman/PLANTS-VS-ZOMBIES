package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

/**
 * Base class for game non playable character in the game.
 *
 */
public abstract class GameObject implements GameItem 
{	
	protected GameWorld game;

	protected int col;

	protected int row;
	
	protected int lives;
	
	protected int cooldown;
			
	GameObject()
	{
		
	}
	
	GameObject(GameWorld game, int col, int row, int lives) 
	{
		this.game = game;
		
		this.col = col;
		
		this.row = row;
		
		this.lives = lives;
		
	}

	GameObject(GameWorld game, int col, int row, int lives, int cooldown)
	{
		this.game = game;
		
		this.col = col;
		
		this.row = row;
		
		this.lives = lives;		
		
		this.cooldown = cooldown;		
	}

	public boolean isInPosition(int col, int row)
	{
		return this.col == col && this.row == row;				//true if object's in that position
	}

	public int getCol()
	{
		return col;									//return object's column
	}

	public int getRow()
	{
		return row;									//return object's row
	}
	
	public boolean isAlive()
	{
		return this.lives > 0;							//true if lives > 0
	}

	public String toString() 
	{
		if (isAlive()) 
		{			
			return Messages.status(getSymbol(), this.lives);			//return object's status
		} 
		
		else
		{
			return "";												//empty slot
		}
	}

	abstract protected String getSymbol();

	abstract public String getDescription();

	public void update()
	{			
		this.delayedAction();
	}

	protected int getCooldown()
	{
		return 0;
	}
	
	abstract public void onEnter();
	
	abstract public void onExit();
	
    protected void delayedAction()
    {
    	
    }
       
    @Override
    public boolean catchObject()
    {
    	return false;						//by default
    }
    
    protected void kill()
    {
    	this.lives = 0;							//kill object 
    }       
    
    public void receiveDamage(int damage)
    {
    	this.lives -= damage;					//apply damage to object
    }	   
    
    @Override
    public boolean fillPosition()
    {
    	return true;							//by default
    }
}
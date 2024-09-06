package tp1.p2.logic;

import java.util.ArrayList;
import java.util.List;

import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.view.Messages;

public class GameObjectContainer
{
	private List<GameObject> gameObjects;

	public GameObjectContainer() 
	{
		gameObjects = new ArrayList<>();
	}

	public String positionToString(int col, int row) 
	{
		StringBuilder buffer = new StringBuilder();
		
		boolean sunPainted = false;
		boolean sunAboutToPaint = false;

		for (GameObject g : gameObjects) 
		{
			if(g.isAlive() && g.getCol() == col && g.getRow() == row) 
			{
				String objectText = g.toString();
				
				sunAboutToPaint = objectText.indexOf(Messages.SUN_SYMBOL) >= 0;
				
				if (sunAboutToPaint) 
				{
					if (!sunPainted) 
					{
						buffer.append(objectText);
						
						sunPainted = true;
					}
				} 
				
				else
				{
					buffer.append(objectText);
				}
			}
		}

		return buffer.toString();
	}

	public boolean removeDead()
	{		
		return this.deleteDeadObjects();
	}

	// TODO add your code here
	
	public void add(GameObject object)
	{		
		this.gameObjects.add(object);								//add object to the list
		
		object.onEnter();							//execute its onEnter()
	}
	
	public boolean isPositionEmpty(int col, int row)
	{
		for(GameObject object : this.gameObjects)				//traverse gameObjects list
		{
			if(object.isInPosition(col, row))					
			{
				return false;									//false if position is not empty
			}	
		}
		
		return true;										//true if position is empty
	}
	
	public void update()
	{
		// Can't use for-each loop (for(GameObject g : gameObjexts)) without errors.
				for(int i = 0; i < gameObjects.size(); i++) 
				{
					GameObject g = gameObjects.get(i);
					
					if(g.isAlive())
					{
						g.update();
					}
				}		
	}
	
	public boolean isFullyOccupied(int col, int row) 
	{
		int i=0;
		boolean fullyOcuppied = false;

		while (i<gameObjects.size() && !fullyOcuppied) 
		{
			GameObject g = gameObjects.get(i);
			
			if (g.isAlive() && g.isInPosition(col, row))
			{
				fullyOcuppied = g.fillPosition();
			}
			i++;
		}

		return fullyOcuppied;
	}
	
	public void clear()
	{
		this.gameObjects.clear();										//remove all objects from the list
	}
	
	public GameObject getGameItemInPosition(int col, int row)
	{
		for(GameObject object : this.gameObjects)					//traverse gameObjects list
		{
			if(object.isInPosition(col, row))
			{
				return object;									//return object if is in position <col, row>
			}
		}
		
		return null;										//null if there is no object in that position
	}	
	
	public List<GameObject> getGameItemsInPosition(int col, int row)
	{		
		List<GameObject> objects = new ArrayList<>();
		
		for(GameObject object : this.gameObjects)					//traverse gameObjects list
		{
			if(object.isInPosition(col, row))
			{
				objects.add(object);
			}
		}	
		
		return objects;										//null if there is no object in that position
	}
	private boolean deleteDeadObjects()
	{
		boolean removed = false;
		
		List<GameObject> objectsToRemove = new ArrayList<>();				//create new list to store dead objects
		
		for(GameObject object : this.gameObjects)						//traverse gameObjects list
		{
			if(object.isAlive() == false)					//if object is dead
			{
				object.onExit();							//execute its onExit() method
				
				objectsToRemove.add(object);				//add dead object to objectsToRemove list
				
				//objectsToRemove.add(this.seekDuplicate(object.getCol(), object.getRow()));
												
				removed = true;
			}					
		}
		
		this.gameObjects.removeAll(objectsToRemove);		//remove dead objects
		
		return removed;							// true if any dead object was removed
	}
	
	public boolean isObjectInPosition(int col, int row)
	{
		int i = 0;
		
		boolean found = false;
		
		while(i < gameObjects.size() && !found)
		{
			if(gameObjects.get(i).isAlive() && gameObjects.get(i).isInPosition(col, row))
			{
				found = true;
			}
			
			else
			{
				i++;
			}
		}
		
		return found;
	}
	
	private GameObject seekDuplicate(int col, int row)
	{
		for(GameObject object : this.gameObjects)
		{
			if(object.isInPosition(col, row) && object.fillPosition() == false)
			{
				return object;
			}
		}
		
		return null;
	}
	
	public boolean catchObject(int col, int row)
	{
		for(GameObject object : this.gameObjects)
		{
			if(object.catchObject() == true)
			{					
				return true;
			}
		}		
		
		return false;
	}
}
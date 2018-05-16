package Stock;

import java.util.ArrayList;

public class Stock {
	
	ArrayList<Item> inventory;
	
	/*
	 *  Creates an array list to be called
	 *  @author Kyle Langton
	 */
	public Stock(){
		inventory = new ArrayList<Item>();
	}
	
	/*
	 *  adds the given item parameter to the array list
	 *  
	 *  
	 * @param an Item to be added the the arraylist.
	 * @author Kyle Langton
	 * 
	 */
	public void addItem(Item item) {
		// TODO Auto-generated method stub
		inventory.add(item);
	}

	/*
	 * gets the arraylist
	 * 
	 * @return returns the arraylist 
	 * @author Kyle Langton
	 */
	public ArrayList<Item> getStock() {
		// TODO Auto-generated method stub
		return inventory;
	}

	/*
	 *  gets the length of the arraylist
	 *  
	 * @return returns the size of the arraylist as an integer
	 * @author Kyle Langton
	 */
	public int getLength() {
		// TODO Auto-generated method stub
		return inventory.size();
	}

	/* gets the item given at given number
	 * 
	 * 
	 * @param itemNumber, a given index number as an integer
	 * @return returns the item given at the location of the paramaters integer
	 * @author Kyle Langton
	 */
	public Object getItem(int itemNumber) {
		// TODO Auto-generated method stub
		return inventory.get(itemNumber);
	}
	
	

}

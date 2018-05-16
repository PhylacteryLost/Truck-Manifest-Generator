package Stock;

import java.util.ArrayList;

public class Stock {
	
	ArrayList<Item> inventory;
	
	public Stock(){
		inventory = new ArrayList<Item>();
	}
	

	public void addItem(Item item) {
		// TODO Auto-generated method stub
		inventory.add(item);
	}


	public ArrayList<Item> getStock() {
		// TODO Auto-generated method stub
		return inventory;
	}


	public int getLength() {
		// TODO Auto-generated method stub
		return inventory.size();
	}


	public Object getItem(int itemNumber) {
		// TODO Auto-generated method stub
		return inventory.get(itemNumber);
	}
	
	

}

import java.util.ArrayList;

import Stock.*;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Item colditem =  new Item("food", 2.0, 3.0, 530, 100, 2.0);
		Item noncold = new Item("food", 2.0, 3.0, 530, 100);
		
		
		ArrayList<Item> inventory = new ArrayList<Item>();
		inventory.add(colditem);
		inventory.add(noncold);
		
		for (Item temp : inventory)
		{
			System.out.println(temp.GetTemperature());
		}
	}

}

package Delivery;

import Stock.Item;
import Stock.Stock;

public class Truck {
	
	Stock truckInventory;

	public Truck(Stock cargo) {
		// TODO Auto-generated constructor stub
		truckInventory = cargo;
	}

	public void AddCargo(Item inventory) {
		// TODO Auto-generated method stub
		truckInventory = inventory;
	}

	public Stock getCargo() {
		// TODO Auto-generated method stub
		return truckInventory;
	}

	public double getCost() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getCapacity() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}

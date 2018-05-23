package Delivery;

import Stock.Stock;

public class Truck {
	
	private Stock truckInventory;

	public Truck(Stock cargo) {
		// TODO Auto-generated constructor stub
		truckInventory = cargo;
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

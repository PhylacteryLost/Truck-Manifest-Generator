package Delivery;

import Stock.Item;
import Stock.Stock;

public class OrdinaryTruck extends Truck {

	// Set maximum cargo capacity.
	private int cargoCapacity = 1000;
	
	// Create cargo list for all items.
	private Stock cargo = new Stock();
	
	/*
	 *  constructs ordinary truck object.
	 *    
	 * @param default truck cargo.
	 * @author Clinton Hodge.
	 */
	public OrdinaryTruck(Stock cargo)
	{
		super(cargo);
		int numItems = 0;
		for(Item i : cargo.getStock())
		{
			numItems += i.getQuantity();
		}
		
		// Checks if cargo amount within limit.
		if(numItems <= cargoCapacity) 
		{
			// Assign cargo.
			this.cargo = cargo;
		}			
	}
	
	/*
	 *  updates/ replaces the truck's cargo. 
	 *    
	 * @param new truck cargo.
	 * @author Clinton Hodge.
	 */
	public void UpdateCargo(Stock cargo)
	{
		if(cargo.getStock().size() <= cargoCapacity) {
			this.cargo = cargo;
		}
	}
	
	/*
	 * return cost of truck hire to transport cargo.
	 *    
	 * @author Clinton Hodge.
	 */
	@Override
	public double getCost()
	{		
		int numItems = 0;
		for(Item i : cargo.getStock()) {
			numItems += i.getQuantity();
		}
		
		// Return truck transport cost.
		System.out.println(750 + (0.25 * numItems));
		System.out.println(cargo.getStock().size());
		return 750 + (0.25 * numItems);
	}
	
	/*
	 * returns maximum item capacity of ordinary truck.
	 *    
	 * @author Clinton Hodge.
	 */
	@Override
	public int getCapacity()
	{
		return this.cargoCapacity;
	}

	
	/*
	 * returns num items in inventory.
	 *    
	 * @author Clinton Hodge.
	 */
	public int getCargoSize() {
		int numItems = 0;
		for(Item i : cargo.getStock()) {
			numItems += i.getQuantity();
		}
		return numItems;
	}
}

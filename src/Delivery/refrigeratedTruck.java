package Delivery;

import Stock.Item;
import Stock.Stock;

public class refrigeratedTruck extends Truck {
	
	// Set maximum cargo capacity.
	private final int cargoCapacity = 800;	
	
	// Create cargo array list for all items.
	private Stock cargo = new Stock();
	
	// Create cargo array list for cold items.
	Stock coldCargo = new Stock();
	
	// Set default highest temperature.
	private 	double temperature = 10;
	
	
	/*
	 * constructs refrigerated truck object.
	 *    
	 * @param default cargo.
	 * @author Clinton Hodge
	 * 
	 */
	public refrigeratedTruck(Stock cargo) 
	{
		super(cargo);
		
		// Check quantity.
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
			this.setTemperature();
		}
		else
		{
		}
	}
	
	
	/*
	 * calculate and set truck temperature.
	 *    
	 * @author Clinton Hodge
	 * 
	 */
	public void setTemperature()
	{					
		for(Item i: cargo.getStock())
		{			
			// Check if item is refrigerated.
			if(i.getClass() == new RefrigeratedItem("",0,0,0,0,0).getClass())
			{
				// Add refrigerated items to cold cargo. 
				coldCargo.addItem(i);			
			}
		}		
		// Iterate through all cold cargo.
		for(Item i: coldCargo.getStock())
		{
			// CHECK IF WORKS.
			if(i.getClass() == new RefrigeratedItem("",0,0,0,0,0).getClass()) {
				RefrigeratedItem x = (RefrigeratedItem) i;
				if(x.GetTemperature() < temperature && x.GetTemperature() >= -20)
				{
					temperature = x.GetTemperature();
				}
			}			
		}
	}	
	
	
	public void UpdateCargo(Stock cargo)
	{
		if(cargo.getStock().size() <= cargoCapacity) {
			this.coldCargo = cargo;
		}
	}
	
	
	/*
	 * return temperature of truck.
	 *    
	 * @author Clinton Hodge
	 * 
	 */
	@Override
	public double getTemperature()
	{
		// Return truck temperature.
		return this.temperature;
	}
	
	
	/*
	 * return cost of hiring cold truck.
	 *    
	 * @author Clinton Hodge
	 * 
	 */
	@Override
	public double getCost()
	{		
		// Return cost of transport truck.
		return 900 + 200 * Math.pow(0.7, temperature / 5);
	}
	
	
	/*
	 * returns maximum truck item capacity.
	 *    
	 * @author Clinton Hodge
	 * 
	 */
	@Override
	public int getCapacity()
	{
		return cargoCapacity;
	}
}

package Delivery;

import java.text.DecimalFormat;

import Stock.Item;
import Stock.Stock;

public class RefrigeratedTruck extends Truck {
	
	// Set maximum cargo capacity.
	private final int cargoCapacity = 800;	
	
	// Create cargo array list for all items.
	private Stock cargo = new Stock();
		
	// Set default highest temperature.
	private 	double temperature = 10;
	
	
	/*
	 * constructs refrigerated truck object.
	 *    
	 * @param default cargo.
	 * @author Clinton Hodge
	 * 
	 */
	public RefrigeratedTruck(Stock cargo) 
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
	}
	
	
	/*
	 * calculate and set truck temperature.
	 *    
	 * @author Clinton Hodge
	 * 
	 */
	public void setTemperature()
	{		
		// Iterate through all cold cargo.
		for(Item i: cargo.getStock())
		{
			// CHECK IF WORKS.
			if(i.getTemperature() != null && i.getTemperature() < temperature && i.getTemperature() >= -20) 
			{		
				temperature = i.getTemperature();
			}			
		}
	}	
	
	
	public void UpdateCargo(Stock cargo)
	{
		int numItems = 0;
		for(Item i : cargo.getStock()) 
		{
			numItems += i.getQuantity();
		}
		if(numItems <= cargoCapacity) {
			this.cargo = cargo;
		}
	}
	
	
	/*
	 * return temperature of truck.
	 *    
	 * @author Clinton Hodge
	 * 
	 */	
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
        DecimalFormat roundUpFormat = new DecimalFormat("###.##");
        double returnValue =  900 + 200 * Math.pow(0.7, (temperature / 5));
        String roundedUpValue = roundUpFormat.format(returnValue);
        return Double.parseDouble(roundedUpValue);

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
	

	/*
	 * returns quantity of cargo in truck.
	 *    
	 * @author Clinton Hodge
	 */
	public int getCargoSize() {
		int numItems = 0;
		for(Item i : cargo.getStock()) {
			numItems += i.getQuantity();
		}
		return numItems;
	}
}

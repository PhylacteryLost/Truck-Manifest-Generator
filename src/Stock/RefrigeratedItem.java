package Stock;

public class RefrigeratedItem extends Item{
	
	private double temp;
	
	
	/*
	 * Inherits item and adds temperature as a parameter for items that require a temperature.
	 * 
	 * @param name, name of the item as a string
	 * @param sellPrice, selling price of the item as a double
	 * @param manPrice, manufactor price of the item as a double
	 * @param oreorderPoint, the Re-order point for the item as an integer
	 * @param re-orderamount, the price of the reorder amount as an integer
	 * @param temperature, the temperature of the item as an integer
	 * 
	 * @author Kyle Langton
	 * 
	 */
	public RefrigeratedItem(String name, double sellPrice, double manfPrice, int reorderPoint, int reorderAmount, double temperature) {
		super(name, sellPrice, manfPrice, reorderPoint, reorderAmount);
		// TODO Auto-generated constructor stub
		temp = temperature;
	}

	/*
	 * gets the the temperature of the item
	 * @return returns the temperature of the item as a double
	 */
	public double GetTemperature() {
		// TODO Auto-generated method stub
		return temp;
	}


}

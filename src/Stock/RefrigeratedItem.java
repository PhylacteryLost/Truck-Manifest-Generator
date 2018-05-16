package Stock;

public class RefrigeratedItem extends Item{
	
	private double temp;

	public RefrigeratedItem(String name, double sellPrice, double manfPrice, int reorderPoint, int reorderAmount, double temperature) {
		super(name, sellPrice, manfPrice, reorderPoint, reorderAmount);
		// TODO Auto-generated constructor stub
		temp = temperature;
	}

	public double GetTemperature() {
		// TODO Auto-generated method stub
		return temp;
	}


}

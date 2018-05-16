package Stock;

public class Item {
	
	private String itemName;
	private double sellingPrice;
	private double manfCost;
	private int restockPoint;
	private int reorderAmount;
	private int quantity;

	/*
	 * Ceates a food item using a string as the name, 2 doubles for the selling and manufacturing cost
	 * and two integers for the reorder point and reorder amount
	 * 
	 */
	public Item(String name, double sellPrice, double manfPrice, int reorderPoint, int reorderAmount) {
		itemName = name;
		sellingPrice = sellPrice;
		manfCost = manfPrice;
		restockPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
		quantity = restockPoint;
	}
	
	/*
	 * Simple method which returns the name of the item
	 * RETURN: Name of the item as a string
	 */
	public Object GetName() {
		// TODO Auto-generated method stub
		return itemName;
	}
	
	public double GetManufacturePrice() {
		// TODO Auto-generated method stub
		return manfCost;
	}
	
	public double GetSellPrice() {
		// TODO Auto-generated method stub
		return sellingPrice;
	}
	
	public int GetReorderPoint() {
		// TODO Auto-generated method stub
		return restockPoint;
	}
	
	public int GetReorderAmount() {
		// TODO Auto-generated method stub
		return reorderAmount;
	}
	
	public int getQuantity() {
		// TODO Auto-generated method stub
		return quantity;
	}

}

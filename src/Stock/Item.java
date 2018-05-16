package Stock;

public class Item {
	
	private String itemName;
	private double sellingPrice;
	private double manfCost;
	private int restockPoint;
	private int reorderAmount;
	private int quantity;


	/*
	 * Creates an item using a string for the name, doubles to represent a selling price and manufacturing price, 
	 * And integers to represent reorderpoints and reorderAmounts 
	 * 
	 * 
	 * @Author Kyle Langton
	 * @param Doubles: sellprice , selling price of the item
	 * @param double manfprice, price required to manufacture the item
	 * @param integer reorderPoint, the re-order point for the item
	 * @param integer reorderamount, the price of the reorder
	 * @return An Item with the above properties
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
	 *
	 * 
	 * @Author Kyle Langton
	 * @return the item name as a string
	 */
	public Object GetName() {
		// TODO Auto-generated method stub
		return itemName;
	}
	
	/* gets the manufacture price of the item
	 * @Author Kyle Langton
	 * @return the manufacture cost of the item as a double
	 * 
	 */
	public double GetManufacturePrice() {
		// TODO Auto-generated method stub
		return manfCost;
	}
	
	/* gets the selling price of the item
	 * @Author Kyle Langton
	 * @return the selling price of the item as a double
	 */
	public double GetSellPrice() {
		// TODO Auto-generated method stub
		return sellingPrice;
	}
	
	/* gets the reorderpoint of the item
	 * @Author Kyle Langton
	 * @return returns the reorderpoint of the item as a integer
	 */
	public int GetReorderPoint() {
		// TODO Auto-generated method stub
		return restockPoint;
	}
	
	/* gets the re-order amount of the item
	 * @Author Kyle Langton
	 * @return returns the re-order amount of the item as an integer
	 */
	public int GetReorderAmount() {
		// TODO Auto-generated method stub
		return reorderAmount;
	}
	
	/* gets the quantity of the items
	 * @Author Kyle Langton
	 * @return returns the item quantity as an integer
	 */
	public int getQuantity() {
		// TODO Auto-generated method stub
		return quantity;
	}

}

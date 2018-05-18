package Stock;

public class Store {
	
	private Store() 	{}
	
	// Create single store object.
	private static Store store = new Store();
	
	// Store name.
	private String storeName;
	
	// Default store capital.
	private double capital = 100000.00;
	
	// Create store inventory.
	private Stock inventory = new Stock();
		
	/*
	 * returns single instance of store.
	 *    
	 * @author Clinton Hodge
	 * 
	 */
	public static Store GetStore()
	{
		return store;
	}
	
	/*
	 * returns name of store.
	 *    
	 * @author Clinton Hodge
	 * 
	 */
	public String GetName() 
	{
		return storeName;		
	}
	
	/*
	 * return entire inventory of store.
	 *    
	 * @author Clinton Hodge
	 * 
	 */
	public Stock getInventory()
	{
		return inventory;
	}

	/*
	 * return total current capital of store.
	 *     
	 * @author Clinton Hodge
	 * 
	 */
	public double GetCapital() 
	{		
		return capital;
	}
	
	/*
	 * set the name of the store,
	 *    
	 * @param new store name.
	 * @author Clinton Hodge
	 * 
	 */
	public void UpdateName(String newStoreName)
	{
		this.storeName = newStoreName;
	}
	
	/*
	 * Update/ replace store inventory.
	 *    
	 * @param new replacement inventory.
	 * @author Clinton Hodge
	 * 
	 */
	public void UpdateInventory(Stock inventory)
	{
		this.inventory = inventory;
	}
	
	/*
	 * updates/ replaces store capital.
	 *    
	 * @param new replacement capital amount.
	 * @author Clinton Hodge
	 * 
	 */
	public void UpdateCapital(double newCapital)
	{
		capital = newCapital;
	}
}
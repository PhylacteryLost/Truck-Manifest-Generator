import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class Stock_Test 
{
	Stock testStoreInventory;
	List<Item> items = new ArrayList<Item>();
	Item item_1, item_2, item_3;
	
	@Before
	public void TestAddItemsToInventory()
	{		
		// Create test items and add to a ArrayList.
		item_1 = new Item("test_item_1", 35.00, 40.00, 20, 30, 54.67);
		item_2 = new Item("test_item_1", 35.00, 40.00, 20, 30, 54.67);
		item_3 = new Item("test_item_3", 35.00, 40.00, 20, 30, 54.67);
		items.add(item_1);
		items.add(item_2);
		items.add(item_3);
		
		// Add all items to stock.
		testStoreInventory.AddToInventory(items);			
	}
	
	@Test	// Test if items array list same as one passed in.
	public void TestGetInventoryList() throws Exception
	{
		assertEquals(items, testStoreInventory.GetInventoryList());
	}
	
	@Test	// Test for number of items in inventory.
	public void TestGetItemQuantity()
	{
		assertEquals(3, testStoreInventory.GetItemQuantity());
	}
}

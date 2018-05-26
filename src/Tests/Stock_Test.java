package Tests;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Stock.Item;
import Stock.Stock;

public class Stock_Test 
{
	Stock inventory = new Stock();	
	Item item_1, item_2;
	Item item_3;
	
	
	/*
	 * Construct items and add them to inventory.
	 * @author Clinton Hodge.
	 */
	@Before
	public void TestAddItemsToInventory()
	{		
		// Create test items.
		item_1 = new Item("warm rock", 1, 3, 100, 170);
		item_2 = new Item("warm matt", 3, 1, 1300, 17000);
		item_3 = new Item("cold item", 3, 1, 1300, 17000, 12.0);
		
		// Add items.
		inventory.addItem(item_1);
		inventory.addItem(item_2);
		inventory.addItem(item_3);		
	}
	
	/*
	 * Test add items to stock.
	 * @author Clinton Hodge.
	 */
	@Test
	public void TestAddItems()
	{
		Stock tStock = new Stock();
		for(int i = 0; i < 500; i++)
		{
			Item item = new Item(("item"+i), 1, 3, 100, 170);
			tStock.addItem(item);
		}
		assertEquals(500, tStock.getStock().size());
	}
	
	/*
	 * Test get inventory item list.
	 * @author Clinton Hodge.
	 */
	@Test	
	public void TestGetInventoryList() throws Exception
	{		
		// Create test stock.
		Stock stock = new Stock();
		stock.addItem(item_1);
		stock.addItem(item_2);
		stock.addItem(item_3);
		
		// Compare test stock to retrieved stock.
		assertEquals(inventory.getStock(), stock.getStock());
	}
	
	/*
	 * Test return get item quantity.
	 * @author Clinton Hodge.
	 */
	@Test	
	public void TestGetItemQuantity()
	{
		// Test for number of items in inventory.
		assertEquals(0, item_1.getQuantity());
		item_1.setQuantity(50);
		assertEquals(50, item_1.getQuantity());
	}
	
	/*
	 * Test return get length.
	 * @author Clinton Hodge.
	 */
	@Test
	public void TestGetLength()
	{
		Stock stock = new Stock();
		for(int i = 0; i < 200; i++)
		{
			Item item = new Item(("item"+i), 1, 3, 100, 170);
			stock.addItem(item);
		}
		
		assertEquals(200, stock.getLength());
	}
	
	/*
	 * Test return get item.
	 * @author Clinton Hodge.
	 */
	@Test
	public void TestGetItem()
	{
		// Test if get item works.
		assertEquals(item_1, inventory.getItem(0));
	}
}

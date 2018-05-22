package Tests;
import static org.junit.Assert.assertEquals;

import org.junit.*;
import org.junit.Test;

import Stock.Item;


public class Item_Test
{
	private Item itemCooled;
	private Item item;

	@Before	// Build test constructors.
	public void TestConstructorCooled()
	{
		itemCooled = new Item("test_item", 35.00, 40.00, 20, 30, 54.67);
		item = new Item("test_item_uncooled", 35.00, 40.00, 20, 30);
	}
	
	@Test	// Try test if item name correct.
	public void TestItemNameCooled()
	{
		assertEquals("test_item", itemCooled.getName());
	}

	@Test
	public void TestItemNameUncooled()
	{
		assertEquals("test_item_uncooled", item.getName());
	}

	@Test	// Try test if manufacture price correct.
	public void TestManufacturePrice()
	{
		assertEquals(40.00, itemCooled.getManufacturePrice(),0);
	}

	@Test	// Try test if sell price is correct.
	public void TestSellPrice()
	{
		assertEquals(35.00, itemCooled.getSellPrice(),0);
	}

	@Test	// Try test if reorder point correct.
	public void TestReorderPoint()
	{
		assertEquals(20, (int)itemCooled.getReorderPoint());
	}

	@Test	// test if reorder amount correct.
	public void TestReorderAmount()
	{
		assertEquals(30, (int)itemCooled.getReorderAmount());
	}

	@Test
	public void TestGetQuantity()
	{
		assertEquals(20, (int)itemCooled.getQuantity());
	}
	
	@Test
	public void TestSetItem()
	{
		itemCooled.setQuantity(32);
		assertEquals(32, itemCooled.getQuantity());
	}
	
	@Test
	public void TestTempreatureCooled()
	{
		assertEquals(54.67, itemCooled.getTemperature(),0);
	}
}

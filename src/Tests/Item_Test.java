import static org.junit.Assert.assertEquals;

import org.junit.*;
import org.junit.Test;



public class Item_Test
{
	private RefrigeratedItem item;
	private Item item_uncooled;

	@Before	// Build test constructors.
	public void TestConstructorCooled()
	{
		item = new RefrigeratedItem("test_item", 35.00, 40.00, 20, 30, 54.67);
		item_uncooled = new Item("test_item_uncooled", 35.00, 40.00, 20, 30);
	}




	@Test	// Try test if item name correct.
	public void TestItemNameCooled()
	{
		assertEquals("test_item", item.GetName());
	}

	@Test
	public void TestItemNameUncooled()
	{
		assertEquals("test_item_uncooled", item_uncooled.GetName());
	}

	@Test	// Try test if manufacture price correct.
	public void TestManufacturePrice()
	{
		assertEquals(35.00, item.GetManufacturePrice(),0);
	}

	@Test	// Try test if sell price is correct.
	public void TestSellPrice()
	{
		assertEquals(40.00, item.GetSellPrice(),0);
	}

	@Test	// Try test if reorder point correct.
	public void TestReorderPoint()
	{
		assertEquals(20, (int)item.GetReorderPoint());
	}

	@Test	// test if reorder amount correct.
	public void TestReorderAmount()
	{
		assertEquals(30, (int)item.GetReorderAmount());
	}

	@Test
	public void TestGetQuantity()
	{
		assertEquals(20, (int)item.getQuantity());
	}
	
	@Test
	public void TestTempreatureCooled()
	{
		assertEquals(54.67, item.GetTemperature(),0);
	}
}

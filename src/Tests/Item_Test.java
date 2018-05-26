package Tests;
import static org.junit.Assert.assertEquals;

import org.junit.*;
import org.junit.Test;

import Stock.Item;


public class Item_Test
{
	private Item itemCooled;
	private Item item;

	/*
	 * Construct items for testing.
	 * @author Clinton Hodge.
	 */
	@Before
	public void TestConstructorCooled()
	{
		itemCooled = new Item("test_item", 35.00, 40.00, 20, 30, 54.67);
		item = new Item("test_item_uncooled", 35.00, 40.00, 20, 30);
	}
	
	/*
	 * Test if return correct item name of 1st item constructor.
	 * @author Clinton Hodge.
	 */
	@Test
	public void TestItemNameCooled()
	{
		assertEquals("test_item", itemCooled.getName());
	}

	/*
	 * Test if return correct item name of 2nd constructor.
	 * @author Clinton Hodge.
	 */
	@Test
	public void TestItemNameUncooled()
	{
		assertEquals("test_item_uncooled", item.getName());
	}

	/*
	 * Test return manufacture cost.
	 * @author Clinton Hodge.
	 */
	@Test	
	public void TestManufacturePrice()
	{
		assertEquals(35.00, itemCooled.getManufacturePrice(),0);
	}
	
	/*
	 * Test return item sell price.
	 * @author Clinton Hodge.
	 */
	@Test
	public void TestSellPrice()
	{
		assertEquals(40.00, itemCooled.getSellPrice(),0);
	}

	/*
	 * Test return item reorder point.
	 * @author Clinton Hodge.
	 */
	@Test	
	public void TestReorderPoint()
	{
		assertEquals(20, (int)itemCooled.getReorderPoint());
	}

	/*
	 * Test return item reorder amount.
	 * @author Clinton Hodge.
	 */
	@Test
	public void TestReorderAmount()
	{
		assertEquals(30, (int)itemCooled.getReorderAmount());
	}

	/*
	 * Test return item get quantity.
	 * @author Clinton Hodge.
	 */
	@Test
	public void TestGetQuantity()
	{
		assertEquals(0, itemCooled.getQuantity());
		itemCooled.setQuantity(50);
		assertEquals(50, itemCooled.getQuantity());
	}
	
	/*
	 * Test set item quantity.
	 * @author Clinton Hodge.
	 */
	@Test
	public void TestSetItem()
	{
		itemCooled.setQuantity(32);
		assertEquals(32, itemCooled.getQuantity());
	}
	
	/*
	 * Test return item temperature.
	 * @author Clinton Hodge.
	 */
	@Test
	public void TestTempreatureCooled()
	{
		assertEquals(54.67, itemCooled.getTemperature(),0);
	}
}

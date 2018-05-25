package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Stock.Item;
import Stock.Stock;
import Stock.Store;

public class Store_Test {
	
	private Store testStore;
	private Stock testInventory = new Stock();
	
	Item rice = new Item("Rice", 2.0 , 3.0, 225, 300 );
	Item beans = new Item("Beans", 4.0 , 6.0, 450, 525);
	
	/*
	 * Sets the testStore to null
	 * @Author Kyle Langton
	 */
	@Before
	public void setUpTests() {
		testStore = null;	
	}
	
	/*
	 * Tests if the testStore is a type of Store
	 * @author Kyle Langton
	 */
	@Test
	public void testStoreClass() {
		testStore  = Store.getStore();
		assertEquals(Store.class, testStore.getClass());
	}
	
	
	/*
	 * Test that store name is assigned and updates correctly 
	 * @author Kyle Langton 
	 */
	@Test
	public void testStoreName() {
		testStore = Store.getStore();
		testStore.updateName("Store 40");
		assertEquals("Store 40", testStore.getName());
	}
	
	/**
	 * Test if the intial capital is set at 100,000.00
	 * @author Kyle Langton
	 */
	@Test
	public void testStoreCapital() {
		testStore = Store.getStore();
		assertEquals(100000.00, testStore.getCapital(),0);
	}
	
	/*
	 * Test if the store capital updates correctly
	 * @author Kyle Langton
	 */
	@Test
	public void testUpdatedCapital() {
		testStore = Store.getStore();
		testStore.updateCapital(testStore.getCapital() - 100.00);
		assertEquals(99900.00, testStore.getCapital(), 0);
	}
	
	/*
	 * Test that the capital updated to the correct decimal value 
	 * @author Kyle Langton
	 */
	@Test
	public void testUpdatedDecimal() {
		testStore = Store.getStore();
		testStore.updateCapital(testStore.getCapital() - 200.40);
		assertEquals(99699.60, testStore.getCapital(), 0);	
	}
	
	/*
	 * test the the inventory is that placed inside the store
	 * @author Kyle Langton
	 */
	@Test
	public void testInventory() {
		testStore = Store.getStore();
		testStore.updateInventory(testInventory);
		assertEquals(testInventory, testStore.getInventory());
	}

}

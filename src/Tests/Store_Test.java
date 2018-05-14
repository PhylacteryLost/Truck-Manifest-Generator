package Tests;

import org.junit.Before;
import org.junit.Test;

public class Store_Test {
	
	private Store testStore;
	private Stock testInventory;
	
	Item rice = new Item("Rice", 2.0 , 3.0, 225.0, 300.0 );
	Item beans = new Item("Beans", 4.0 , 6.0, 450.0, 525.0);
	
	testInventory.add(rice);
	testInventory.add(beans);
	
	/**
	 * Sets the store to null before executing the tests
	 */
	@Before
	public void setUpTests() {
		testStore = null;	
	}
	
	/**
	 * Tests to check if the testStore variable is a type of Store
	 */
	@Test
	public void testStoreClass() {
		testStore  = Store.getStore();
		assertEquals(Store.class, testStore.getClass());
	}
	
	
	/**
	 * Checks to see if the storeName has been updated within the testStore variable
	 */
	@Test
	public void testStoreName() {
		testStore = Store.getStore();
		testStore.updateName("Store 40");
		assertEquals("Store 40", testStore.getName());
	}
	
	/**
	 * Check to see if the store has the required capital starting value
	 */
	@Test
	public void testStoreCapital() {
		testStore = Store.getStore();
		assertEquals(100000.00, testStore.getCapital());
	}
	
	/**
	 * Checks to see that the capital has been updated to the correct amount
	 */
	@Test
	public void testUpdatedCapital() {
		testStore = Store.getStore();
		testStore.setCapital(testStore.getCapital() - 100.00);
		assertEquals(99900.00, testStore.getCapital());
	}
	
	/**
	 * Checks to see that the capital has been updated including its decimal values
	 */
	@Test
	public void testUpdatedDecimal() {
		testStore = Store.getStore();
		testStore.setCapital(testStore.getCapital() - 200.40);
		assertEquals(99799.6, testStore.getCapital());	
	}
	
	@Test
	public void testInventory() {
		testStore = Store.getStore();
		testStore.updateInventory(testInventory);
		assertEquals(testInventory, testStore.getInventory());
	}

}

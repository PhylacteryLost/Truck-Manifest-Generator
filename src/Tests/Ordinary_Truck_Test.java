package Tests;

import org.junit.*;

public class Ordinary_Truck_Test {
	
	private OrdinaryTruck testTruck;
	private Stock testStock;
	
	private Item beans = new Itemc("beans",4.0 , 6.0, 450.0, 52.0);
	
	@Before
	public void setUp() {
		testTruck = null;	
	}
	
	/*
	 * Tests the that the truck is an Ordinary Truck
	 */
	@Test
	public void testClass() {
		testTruck = new OrdinaryTruck(testStock);
		assertEquals(OrindaryTruck.class, testTruck.getClass());
	}
	
	/* 
	 * Tests that the cargo limit is 1000
	 */
	@Test
	public void testCargoLimit() {
		testTruck = new OrdinaryTruck(testStock);
		assertEquals(1000, testTruck.getCargoLimit);
	}
	
	/*
	 * Tests that the cargo is the stock is the one that was imported into 
	 * the class
	 */
	@Test
	public void testCargo() {
		testTruck = new OrdinaryTruck(testStock);
		assertEquals(testStock, testTruck.getCargo());
	}
	
	/* 
	 * Test that the cargo size is being calculated correctly
	 */
	@Test
	public void testGetCargoSize() {
		testTruck = new OrdinaryTruck(testStock);
	}
	
	/*
	 * Tests that the truck cost is being calculated properly
	 */
	@Test
	public void testCost() {
		testTruck = new OrindaryTruck(testStock);
		assertEquals(862.5, testTruck.getTruckCost());
	}
	
	
}

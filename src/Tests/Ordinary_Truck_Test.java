package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import Delivery.OrdinaryTruck;
import Stock.Item;
import Stock.Stock;

public class Ordinary_Truck_Test {
	
	private OrdinaryTruck testTruck;
	private Stock testStock = new Stock();
	
	private Item beans = new Item("beans",4.0 , 6.0, 450, 52);
	
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
		assertEquals(OrdinaryTruck.class, testTruck.getClass());
	}
	
	/* 
	 * Tests that the cargo limit is 1000
	 */
	@Test
	public void testCargoLimit() {
		testTruck = new OrdinaryTruck(testStock);
		assertEquals(1000, testTruck.getCapacity());
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
		testStock.addItem(beans);
		assertEquals(450, testTruck.getCargoSize());
	}
	
	/*
	 * Tests that the truck cost is being calculated properly
	 */
	@Test
	public void testCost() {
		testStock.addItem(beans);
		testTruck = new OrdinaryTruck(testStock);	
		assertEquals(862.5, testTruck.getCost(), 0 );
	}
	
	
}

package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import Delivery.OrdinaryTruck;
import Delivery.RefrigeratedTruck;
import Stock.Item;
import Stock.Stock;

public class Refrigerated_Truck_Test {
	
	private RefrigeratedTruck testTruck;
	private Stock testStock = new Stock();
	private Item beef = new Item("beef",12.00,	17.00,	425,	550,	3.0);

	/**
	 * Sets up before the tests
	 * @author Kyle Langton	
	 */
	@Before
	public void setUp() {
		testTruck = null;
		testStock.addItem(beef);
	}
	/**
	 * Tests is the truck is refrigerated truck
	 * @author Kyle Langton
	 */
	@Test
	public void testClass() {
		testTruck = new RefrigeratedTruck(testStock);
		assertEquals(RefrigeratedTruck.class,testTruck.getClass());
	}
	/**
	 * Gets the temperature of the truck, Equal to the temperature of the lowest item in the stock
	 * @author Kyle Langton
	 */
	@Test
	public void testSetTemp() {
		testTruck = new RefrigeratedTruck(testStock);
		assertEquals(3.0, testTruck.getTemperature(), 0 );
	}
	
	/*
	 * Checks to see if the cargoLimit is at 800;
	 * @author Kyle Langton
	 */
	@Test
	public void TestCargoLimit() {
		testTruck = new RefrigeratedTruck(testStock);
		assertEquals(800, testTruck.getCapacity());		
	}
	
	/**
	 * Gets the cargo size of the items currently inside the truck
	 * @author Kyle Langton
	 */
	@Test
	public void testCargoSize() {
		testTruck = new RefrigeratedTruck(testStock);
		assertEquals(0, testTruck.getCargoSize());
	}
	
	
	/**
	 * Tests that the calculation for the price of the truck is correct
	 * @author Kyle Langton
	 */
	@Test
	public void testCargoCost() {
		testTruck = new RefrigeratedTruck(testStock);
		System.out.println(testStock.getStock().get(0).getTemperature());
		assertEquals(1061.47, testTruck.getCost(), 0 );
	}
	
	/*
	 * Test the cargo size updates correctly
	 * @author Kyle Langton
	 */
	@Test
	public void testUpdatedCargoSize() {
		testTruck = new RefrigeratedTruck(testStock);
		testStock.getItem(0).setQuantity(50);
		assertEquals(50, testTruck.getCargoSize());
	}

}

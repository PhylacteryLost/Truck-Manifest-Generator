package Tests;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import Delivery.RefrigeratedTruck;
import Stock.Item;
import Stock.Stock;

public class Refrigerated_Truck_Test {
	
	private RefrigeratedTruck testTruck;
	private Stock testStock = new Stock();
	private Item beef = new Item("beef",12.00,	17.00,	425,	550,	3.0);

	/**
	 * Sets up before the tests
	 */
	@Before
	public void setUp() {
		testTruck = null;
		testStock.addItem(beef);
	}
	/**
	 * Tests is the truck is refrigerated truck
	 */
	@Test
	public void testClass() {
		testTruck = new RefrigeratedTruck(testStock);
		assertEquals(RefrigeratedTruck.class,testTruck.getClass());
	}
	/**
	 * Gets the temperature of the truck, Equal to the temperature of the lowest item in the stock
	 */
	@Test
	public void testSetTemp() {
		testTruck = new RefrigeratedTruck(testStock);
		assertEquals(3.0, testTruck.getTemperature(), 0 );
	}
	
	/*
	 * Checks to see if the cargoLimit is at 800;
	 */
	@Test
	public void TestCargoLimit() {
		testTruck = new RefrigeratedTruck(testStock);
		assertEquals(800, testTruck.getCapacity());		
	}
	
	/**
	 * Gets the cargo size of the items currently inside the truck
	 */
	@Test
	public void testCargoSize() {
		testTruck = new RefrigeratedTruck(testStock);
		assertEquals(425, testTruck.getCargoSize());
	}

	/**
	 * Tests that the calculation for the price of the truck is correct
	 */
	@Test
	public void testCargoCost() {
		testTruck = new RefrigeratedTruck(testStock);
		System.out.println(testStock.getStock().get(0).getTemperature());
		assertEquals(1061.47, testTruck.getCost(), 0 );
	}
	

}

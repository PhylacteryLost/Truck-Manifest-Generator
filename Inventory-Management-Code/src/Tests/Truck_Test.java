package Tests;
import static org.junit.Assert.*;

import org.junit.Test;

import Delivery.OrdinaryTruck;
import Stock.Stock;
import Delivery.Truck;


public class Truck_Test {
	Stock cargo = new Stock();
	Truck truck = new OrdinaryTruck(cargo);
	
	/*
	 * Test get truck cargo.
	 * @author Clinton Hodge.
	 */
	@Test
	public void TestGetCargo()
	{
		assertEquals(truck.getCargo(), cargo);
	}
}

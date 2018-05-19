import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import Delivery.OrdinaryTruck;
import Stock.Item;
import Stock.Stock;
import Delivery.Truck;


public class Truck_Test {
	Stock cargo = new Stock();
	Truck truck = new OrdinaryTruck(cargo);
	
	
	@Test
	public void TestAddCargo() 
	{	
		Item i = new Item("i",0,0,0,0);
		truck.AddCargo(i);
		assertEquals(truck.getCargo().getItem(0), i);		
	}
	
	@Test
	public void TestGetCargo()
	{
		assertEquals(truck.getCargo(), cargo);
	}
}

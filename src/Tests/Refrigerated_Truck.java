package Tests;

import org.junit.*;

public class Refrigerated_Truck {
	
	private RefrigeratedTruck testTruck;
	private Stock testStock = new Stock();
	Item rice = new Item("Rice", 2.0 , 3.0, 225.0, 300.0 );
	Item beans = new Item("Beans", 4.0 , 6.0, 450.0, 525.0);
	RefrigeratedItem beef = new RefrigeratedItem("beef",12.00,	17.00,	425.0,	550.0,	3.0);

	
	@Before
	public void setUp() {
		testTruck = null;
		testStock.add(rice);
		testStock.add(beans);
		testStock.add(beef);
	}
	
	@Test
	public void testClass() {
		testTruck = new refrigeratedTruck(testStock);
		assertEquals(refrigeratedTruck.class,testTruck.getClass());
	}
	
	@Test
	public void testSetTemp() {
		testTruck = new refrigeratedTruck(testStock);
		assertEquals(3.0, testTruck.getTemperature());
	}
	

}

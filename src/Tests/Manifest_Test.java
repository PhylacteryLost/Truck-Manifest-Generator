package Tests;


import static org.junit.Assert.assertEquals;

import org.junit.*;

import Stock.Manifest;


public class Manifest_Test {
	
	Stock testStock = new Stock();
	
	Manifest testManifest;
	OrdinaryTruck testOrd = new OrdinaryTruck(testStock);
	RefrigeratedTruck testRef = new RefrigeratedTruck(testStock);
	
	Arraylist<Truck> testColl; 
	
	
	@Before
	public void setUp() {
		testManifest = null;
	}
	
	
	@Test
	public void testClass() {
		testManifest = new Manifest();
		assertEquals(Manifest.class, testManifest.getClass());
	}
	
	@Test
	public void testType() {
		testManifest = new Manifest();
		assertEquals(testColl.class , testManifest.getManifest());
		
	}
	
	
	@Test
	public void testAdd() {
		testManifest = new Manifest();
		testManifest.addTruck(testOrd);
		assertEquals(testOrd, testManifest.getTruck(0));
	}
	
	@Test
	public void testLength() {
		testManifest = new Manifest();
		testManifest.addTruck(testOrd);
		testManifest.addTruck(test);
		assertEquals(2, testManifest.getLength());	
	}
	
	

}

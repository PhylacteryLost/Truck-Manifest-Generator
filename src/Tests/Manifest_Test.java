package Tests;


import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.*;

import Delivery.Manifest;
import Delivery.OrdinaryTruck;
import Delivery.RefrigeratedTruck;
import Delivery.Truck;
import Stock.Stock;


public class Manifest_Test {
	
	Stock testStock = new Stock();
	
	Manifest testManifest;
	OrdinaryTruck testOrd = new OrdinaryTruck(testStock);
	RefrigeratedTruck testRef = new RefrigeratedTruck(testStock);
	
	ArrayList<Truck> testColl; 
	
	
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
	public void testAdd() {
		testManifest = new Manifest();
		testManifest.addTruck(testOrd);
		assertEquals(testOrd, testManifest.getTruck(0));
	}
	
	@Test
	public void testLength() {
		testManifest = new Manifest();
		testManifest.addTruck(testOrd);
		testManifest.addTruck(testRef);
		assertEquals(2, testManifest.getLength());	
	}
	
	

}

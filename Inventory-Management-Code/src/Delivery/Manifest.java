package Delivery;

import java.util.ArrayList;

public class Manifest {
	private ArrayList<Truck> trucks = new ArrayList<Truck>();
	
	/*
	 *  replaces truck ArrayList with new truck manfiest.
	 *    
	 * @param a new manifest of trucks.
	 * @author Clinton Hodge
	 * 
	 */
	public void updateManifest(ArrayList<Truck> newManifest)
	{
		trucks = newManifest;
	}
	
	/*
	 *  returns ArrayList of trucks/ manifest of trucks.
	 *    
	 * @author Clinton Hodge
	 * 
	 */
	public ArrayList<Truck> getManifest()
	{
		return trucks;
	}

	/*
	 * adds ordinary truck to manifest.
	 *    
	 * @author Clinton Hodge
	 * 
	 */
	public void addTruck(OrdinaryTruck newTruck) {
		trucks.add(newTruck);		
	}
	
	
	/*
	 * adds refrigerated truck to manifest.
	 *    
	 * @author Clinton Hodge
	 * 
	 */
	public void addTruck(RefrigeratedTruck  newTruck) {
		trucks.add(newTruck);
	}

	
	/*
	 *  returns truck from manifest.
	 *    
	 * @author Clinton Hodge
	 * 
	 */
	public Truck getTruck(int truckIndex) {		
		return trucks.get(truckIndex);
	}

	/*
	 *  returns number of trucks in manifest.
	 *    
	 * @author Clinton Hodge
	 * 
	 */
	public int getLength() {
		return trucks.size();
	}
}

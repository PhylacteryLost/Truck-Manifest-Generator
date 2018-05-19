package Stock;

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
}

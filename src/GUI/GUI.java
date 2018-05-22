package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;



import Delivery.OrdinaryTruck;
import Delivery.RefrigeratedTruck;

import Stock.Stock;
import Stock.Store;
import Stock.Item;

public class GUI {
	
	private static Store supermarket = Store.getStore();
	private static Stock storeInventory = new Stock();
	private static JFrame mainFrame = new JFrame();
	
	public static void main(String[] args) {		
		gui();
	}
	
	public static void gui() {
		

		// Store variables 
		

		

		// Store variables 		
		Store supermarket = Store.getStore();
		Stock storeInventory = new Stock();
		double storeCapital = supermarket.getCapital();
		
		// File path to manifest.
		String manifestFilePath = null;
		
		
		// Swing Variables 
		
		
		// Mainframe
		Dimension mainFrameSize = new Dimension(1000,600);
		
		
		mainFrame.setTitle("Inventory");
		mainFrame.setPreferredSize(mainFrameSize);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		
		// Table 
		DefaultTableModel tableSettings = new DefaultTableModel(new Object[]{"Name", "Quantity", "Manufactoring Cost", "Sell Price", "Reorder Point", "Reorder Amount", "Temperature"}, 0);
		JTable inventoryTable = new JTable(tableSettings);

		
		// Menu Bar 
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new BorderLayout());

		// Labels
		
		JLabel capitalLabel = new JLabel("Store Capital: " + storeCapital);
		double capital = supermarket.getCapital();
		
		// Menu Buttons
		JMenu fileButton = new JMenu("File");
		JMenuItem importItemProperties = new JMenuItem("Import Item Properties");
		
		JMenuItem importManifest = new JMenuItem("Import Manifest");
		importManifest.setEnabled(false);
		
		JMenuItem importSaleLog = new JMenuItem("Import Sales log");
		importSaleLog.setEnabled(false);
		
		
		JMenuItem exportManifest = new JMenuItem("Export Manifest");
		exportManifest.setEnabled(false);
		
		// Action Listeners
		
		importItemProperties.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame csvChooserFrame = new JFrame();
				JFileChooser csvChooser = new JFileChooser();
				csvChooser.setFileFilter(new FileNameExtensionFilter(".csv", "csv"));
				csvChooserFrame.add(csvChooser);
				int status = csvChooser.showOpenDialog(null);
				
				if (status == JFileChooser.CANCEL_OPTION) {
					csvChooserFrame.dispose();
				}
				if (status == JFileChooser.OPEN_DIALOG) {
					String fileName = csvChooser.getSelectedFile().getName();					
					if (fileName.matches(".*.csv")) {
						ArrayList<String[]> readValues = new ArrayList<String[]>();
						
						try {
							readValues = CSVReader.readCSV(csvChooser.getSelectedFile());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						for (int stringcount = 0; stringcount < readValues.size(); stringcount++) {
							if (readValues.get(stringcount).length == 5) {
								
							Item foodItem = new Item(readValues.get(stringcount)[0],Double.parseDouble(readValues.get(stringcount)[1]),Double.parseDouble(readValues.get(stringcount)[2])
									,Integer.parseInt(readValues.get(stringcount)[3]),Integer.parseInt(readValues.get(stringcount)[4]));
							
							
							
							Object[] tempData = { foodItem.getName(), foodItem.getQuantity(), foodItem.getManufacturePrice(), 
									foodItem.getSellPrice(), foodItem.getReorderAmount(), foodItem.getReorderPoint(), null };
							tableSettings.addRow(tempData);
							
							// ---- TEMP STORE STUFF ----
							storeInventory.addItem(foodItem);
							}
							
							if (readValues.get(stringcount).length == 6){
								
								Item foodItem = new Item(readValues.get(stringcount)[0],Double.parseDouble(readValues.get(stringcount)[1]),Double.parseDouble(readValues.get(stringcount)[2])
										,Integer.parseInt(readValues.get(stringcount)[3]),Integer.parseInt(readValues.get(stringcount)[4]),
										Double.parseDouble(readValues.get(stringcount)[5]));
								
							Object[] tempData = { foodItem.getName(), foodItem.getQuantity(), foodItem.getManufacturePrice(), 
									foodItem.getSellPrice(), foodItem.getReorderAmount(), foodItem.getReorderPoint(), foodItem.getTemperature() };
							tableSettings.addRow(tempData);
							
							// ---- TEMP STORE STUFF ----
							storeInventory.addItem(foodItem);
							}
						}
						
					
						
						
						// Finally Configure The Table // 
						inventoryTable.disable();
						menuBar.add(inventoryTable.getTableHeader(), BorderLayout.SOUTH);
						mainFrame.add(inventoryTable, BorderLayout.CENTER);		
						JOptionPane.showMessageDialog(csvChooserFrame, "Loaded Item Properties");
						mainFrame.repaint();
						mainFrame.setVisible(true);						
					}
					else {
						JOptionPane.showMessageDialog(csvChooserFrame, "Error: Input was not a CSV File");
					}
					
				}
				// Update store inventory with new one.
				supermarket.updateInventory(storeInventory);
				// Display pop-up message after completing task.
				
				importSaleLog.setEnabled(true);
				importManifest.setEnabled(true);
				exportManifest.setEnabled(true);
				csvChooserFrame.dispose();
			}
			
			
			
		});
		
		
		importManifest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame csvChooserFrame = new JFrame();
				JFileChooser csvChooser = new JFileChooser();
				csvChooser.setFileFilter(new FileNameExtensionFilter(".csv", "csv"));
				ArrayList<String[]> manifestContent = new ArrayList<String[]>();
				double reduceCapitalValue = 0.0;
				int status = csvChooser.showOpenDialog(null);
				
				
				
				if (status == JFileChooser.CANCEL_OPTION) {
					csvChooserFrame.dispose();
				}
				
				if (status == JFileChooser.OPEN_DIALOG) {
					tableSettings.setRowCount(0);
					String fileName = csvChooser.getSelectedFile().getName();			
					if (fileName.matches(".*.csv")) {
						try {
							manifestContent = CSVReader.readCSV(csvChooser.getSelectedFile());
						}
						catch (IOException e1){
							e1.printStackTrace();
						}
					}
					
					for (int manifestCount = 0; manifestCount < manifestContent.size(); manifestCount++) {
						if (manifestContent.get(manifestCount)[0].matches(">Refrigerated")) {
							Stock truckStock = new Stock();
							int counter = manifestCount + 1;
							while (!(manifestContent.get(counter)[0].matches((">Refrigerated")) &&
									!(manifestContent.get(counter)[0].matches(">Ordinary")) && 
									counter < manifestContent.size())){
								
								for (int inventoryCounter = 0; inventoryCounter < storeInventory.getLength(); inventoryCounter++) {
									if (manifestContent.get(counter)[0].matches(storeInventory.getItem(inventoryCounter).getName())) {
										truckStock.addItem(storeInventory.getItem(inventoryCounter));
										reduceCapitalValue += storeInventory.getItem(inventoryCounter).getManufacturePrice() * Double.parseDouble(manifestContent.get(counter)[1]);
										storeInventory.getItem(inventoryCounter).setQuantity(storeInventory.getItem(inventoryCounter).getQuantity() + Integer.parseInt(manifestContent.get(counter)[1]));
									}
									
								}
								counter++;
								if (counter == manifestContent.size()) {
									break;
								}
								
								
							}
							RefrigeratedTruck coldTruck = new RefrigeratedTruck(truckStock);
							reduceCapitalValue += coldTruck.getCost();
						}
					}
					
					for (int manifestCount = 0; manifestCount < manifestContent.size(); manifestCount++) {
						if (manifestContent.get(manifestCount)[0].matches(">Ordinary")) {
							Stock truckStock = new Stock();
							OrdinaryTruck ordTruck = new OrdinaryTruck(truckStock);
							int counter = 0;
							while (!(manifestContent.get(counter)[0].matches(">Refrigerated")) && 
									!(manifestContent.get(counter)[0].matches(">Ordinary")) &&
									counter < manifestContent.size() ) {
								
								for (int inventoryCount = 0; inventoryCount < storeInventory.getLength(); inventoryCount++ ) {
									if (manifestContent.get(counter)[0].matches(storeInventory.getItem(inventoryCount).getName())) {
										truckStock.addItem(storeInventory.getItem(inventoryCount));
										reduceCapitalValue += storeInventory.getItem(inventoryCount).getManufacturePrice() * Double.parseDouble(manifestContent.get(counter)[1]);
										storeInventory.getItem(inventoryCount).setQuantity(storeInventory.getItem(inventoryCount).getQuantity() + Integer.parseInt(manifestContent.get(counter)[1]));
									}
									
								}
								counter++;
								if (counter == manifestContent.size()) {
									break;
								}
								ordTruck = new OrdinaryTruck(truckStock);
								reduceCapitalValue += ordTruck.getCost();
											
							}
						}
						
					}
					
					for (int inventCount = 0; inventCount < storeInventory.getLength(); inventCount++) {
						if (storeInventory.getItem(inventCount).getTemperature() == null) {
							Object[] tempdata = { storeInventory.getItem(inventCount).getName(), storeInventory.getItem(inventCount).getQuantity(), storeInventory.getItem(inventCount).getSellPrice(), 
									storeInventory.getItem(inventCount).getManufacturePrice(), storeInventory.getItem(inventCount).getReorderAmount(), 
									storeInventory.getItem(inventCount).getReorderPoint(), storeInventory.getItem(inventCount).getTemperature() };
							tableSettings.addRow(tempdata);
							}
						}
					
					for (int inventCount = 0; inventCount < storeInventory.getLength(); inventCount++) {
						if (storeInventory.getItem(inventCount).getTemperature() != null) {
							Object[] tempdata = { storeInventory.getItem(inventCount).getName(), storeInventory.getItem(inventCount).getQuantity(), storeInventory.getItem(inventCount).getSellPrice(), 
									storeInventory.getItem(inventCount).getManufacturePrice(), storeInventory.getItem(inventCount).getReorderAmount(), 
									storeInventory.getItem(inventCount).getReorderPoint(), storeInventory.getItem(inventCount).getTemperature() };
							tableSettings.addRow(tempdata);
							}
						}
				capitalLabel.setText("Store Capital: " + (storeCapital - reduceCapitalValue) );
				importItemProperties.setEnabled(false);
				inventoryTable.disable();
				inventoryTable.repaint();
				mainFrame.repaint();
					
					
					
				}
				
			else {
					JOptionPane.showMessageDialog(csvChooserFrame, "Error: Input was not a CSV File");
				}
				
				csvChooserFrame.dispose();
			}
			

		});
		
		
		/*
		 * exports manifest
		 *    
		 * @author Clinton Hodge
		 *  
		 */
		exportManifest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PrintWriter pw = null;
				
				try 
				{
					// Set output manifest file.
					pw = new PrintWriter(new File(manifestFilePath+"manifest.csv"));					
				} 
				catch (FileNotFoundException e1) 
				{					
					e1.printStackTrace();
				}
				
				double cOrderQuantity = 0;				// Quantity of all cold items.
				Stock manifest = new Stock();			// Items to be ordered.
				Stock refrigeratedItems = new Stock();	// Refrigerated items.
				Stock ordinaryItems = new Stock();		// Ordinary non-refrigerated items.
				
				
				// Check all items in store inventory.
				// Add under-stocked items to order.
				for(Item i : supermarket.getInventory().getStock())
				{
					if((int)i.getQuantity() <= (int)i.getReorderPoint())
					{					
						Item I = i;
						I.setQuantity(I.getReorderAmount());////////////////////// SET TO REORDER POINT ///////////////////
						manifest.addItem(I);						
					}
				}
				
				// Check all items inside order.
				// Get, sort and count all items.
				for(Item i : manifest.getStock())
				{
					if(i.getTemperature() != null)
					{					
						refrigeratedItems.addItem(i);
						cOrderQuantity += i.getQuantity();
					}
					else 
					{
						ordinaryItems.addItem(i);					
					}
				}
						
				// Cold Trucks Needed.
				int numColdTrucks = (int) Math.ceil(cOrderQuantity / 800.0);				
				int curNumItems = 0, allNumItems = 0;
				
				int capacity = 800;		// Capacity of truck inventory (default cold truck)
				String truckType = ">Refrigerated";
				String newline = System.getProperty("line.separator");
				Stock tempManifest = refrigeratedItems;
				
				// CSV file builder.
				StringBuilder sb = new StringBuilder();
				
				
				// Set heading, add stock.
				sb.append(truckType);	
				for(int j = 0; j < tempManifest.getLength(); j++)
				{		
					Item c = (Item) tempManifest.getItem(j);
					
					// Check if item quantity > max capacity.
					if((curNumItems + c.getQuantity()) > capacity) 
					{
						// Fill item remaining space.
						c.setQuantity(c.getQuantity() - (capacity - curNumItems));
												
						// Print only if item fits.
						if(capacity - curNumItems != 0) {							
							sb.append(newline+c.getName() + ",");
							sb.append(capacity - curNumItems);
						}
						allNumItems += (capacity - curNumItems);
						curNumItems = 0;							
													
						// Calculate and loop by number of needed trucks.
						for(int x = 0; x < Math.ceil(c.getQuantity() / capacity); x++)
						{
							// Set to ordinary trucks.
							if(tempManifest == ordinaryItems && capacity != 1000 && allNumItems >= (800 * numColdTrucks)) 
							{							
								capacity = 1000;								
								truckType = ">Ordinary";	
							}
							
							// Parse CSV data.								
								
							sb.append(newline+truckType);							
							sb.append(newline+c.getName() + ",");								
							
							// Check if fits and parse.
							if((int)c.getQuantity() > capacity)
							{
								c.setQuantity(c.getQuantity() - (capacity - curNumItems));
								sb.append((int)(capacity - curNumItems)+newline);	
								allNumItems += (capacity - curNumItems);
								curNumItems = 0;
							}
							else
							{
								sb.append((int)c.getQuantity());
								allNumItems += c.getQuantity();
								curNumItems += c.getQuantity();								
								c.setQuantity(0);
							}
						}
					}
					else
					{		
						// Parse items that fit.						
						sb.append(newline+c.getName() + ",");
						sb.append((int)c.getQuantity());		
						allNumItems += c.getQuantity();
						curNumItems += c.getQuantity();
						c.setQuantity(0);
					}
					
					// Set ordinary truck cargo to add to manifest.
					if(j >= tempManifest.getLength() - 1 && tempManifest == refrigeratedItems) {															
						tempManifest = ordinaryItems;
						j = 0;
					}
				}	
				
				
				pw.write(sb.toString());
				pw.close();
				
				// Display message.
				JOptionPane.showMessageDialog(exportManifest, "Truck Manifest Exported");
			}
			
		});
		

		
		
		
		
		// Adding Components to the menubar
		menuBar.add(fileButton, BorderLayout.WEST);
		menuBar.add(capitalLabel, BorderLayout.EAST);
		
		// Adding components to the file Menu
		fileButton.add(importItemProperties);
		fileButton.add(importManifest);
		fileButton.add(importSaleLog);
		fileButton.add(exportManifest);
		
		// Adding components to the mainframe
		mainFrame.add(menuBar, BorderLayout.NORTH);
		
		
		// Run the GUI
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		
	}

}

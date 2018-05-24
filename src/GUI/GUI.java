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
import Delivery.Truck;
import Stock.Stock;
import Stock.Store;
import Stock.Item;
import Delivery.Manifest;

public class GUI {
	
	private static Store supermarket = Store.getStore();
	private static Stock storeInventory = new Stock();
	private static JFrame mainFrame = new JFrame();
	
	// File path to manifest.
	static String manifestFilePath = null;
			
	public static void gui() {
		

		// Store variables 
		

		

		// Store variables 		
		Store supermarket = Store.getStore();
		Stock storeInventory = new Stock();
		double storeCapital = supermarket.getCapital();
				
		// Swing Variables 
		
		
		// Mainframe
		Dimension mainFrameSize = new Dimension(1000,600);
		
		
		mainFrame.setTitle("Inventory");
		mainFrame.setPreferredSize(mainFrameSize);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		
		// Table 
		DefaultTableModel tableData = new DefaultTableModel(new Object[]{"Name", "Quantity", "Manufactoring Cost in $", "Sell Price in $", "Reorder Point", "Reorder Amount", "Temperature"}, 0);
		JTable inventoryTable = new JTable(tableData);

		
		// Menu Bar 
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new BorderLayout());

		// Labels
		
		JLabel capitalLabel = new JLabel("Store Capital: $" + storeCapital);
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
							manifestFilePath = csvChooser.getSelectedFile().getParentFile().getPath()+"\\";
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						for (int stringcount = 0; stringcount < readValues.size(); stringcount++) {
							if (readValues.get(stringcount).length == 5) {
								
							Item foodItem = new Item(readValues.get(stringcount)[0],Double.parseDouble(readValues.get(stringcount)[1]),Double.parseDouble(readValues.get(stringcount)[2])
									,Integer.parseInt(readValues.get(stringcount)[3]),Integer.parseInt(readValues.get(stringcount)[4]));
							
							
							
							Object[] tempData = { foodItem.getName(), foodItem.getQuantity(), foodItem.getManufacturePrice(), 
									foodItem.getSellPrice(), foodItem.getReorderPoint(), foodItem.getReorderAmount(), null };
							tableData.addRow(tempData);
							
							// ---- TEMP STORE STUFF ----
							storeInventory.addItem(foodItem);
							}
							
							if (readValues.get(stringcount).length == 6){
								
								Item foodItem = new Item(readValues.get(stringcount)[0],Double.parseDouble(readValues.get(stringcount)[1]),Double.parseDouble(readValues.get(stringcount)[2])
										,Integer.parseInt(readValues.get(stringcount)[3]),Integer.parseInt(readValues.get(stringcount)[4]),
										Double.parseDouble(readValues.get(stringcount)[5]));
								
							Object[] tempData = { foodItem.getName(), foodItem.getQuantity(), foodItem.getManufacturePrice(), 
									foodItem.getSellPrice(), foodItem.getReorderPoint(), foodItem.getReorderAmount() , foodItem.getTemperature() };
							tableData.addRow(tempData);
							
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
				JFrame manifestFrame = new JFrame();
				JFileChooser csvChooser = new JFileChooser();
				ArrayList<String[]> manifestContent = new ArrayList<String[]>();
				csvChooser.setFileFilter(new FileNameExtensionFilter(".csv", "csv"));
				manifestFrame.add(csvChooser);
				int status = csvChooser.showOpenDialog(null);
				double reduceValue = 0.0;		
			
										
				if (status == JFileChooser.CANCEL_OPTION) {
					manifestFrame.dispose();
				}
				if (status == JFileChooser.OPEN_DIALOG) {
					tableData.setRowCount(0);
					String fileName =  csvChooser.getSelectedFile().getName();
					
					if (fileName.matches(".*.csv")) {
						JOptionPane.showMessageDialog(manifestFrame, "Loaded Manifest");
						try {
							manifestContent = CSVReader.readCSV(csvChooser.getSelectedFile());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						for (int i = 0; i < storeInventory.getLength();i++) {
							System.out.println(storeInventory.getItem(i).getName() + " " + storeInventory.getItem(i).getQuantity());
						}
						
						for (int manifestCount = 0; manifestCount < manifestContent.size();  manifestCount++) {
							if (manifestContent.get(manifestCount)[0].matches(">Refrigerated")) {
								Stock truckStock = new Stock();
								RefrigeratedTruck coldTruck = new RefrigeratedTruck(truckStock);
								int counter = manifestCount + 1;
								while (!(manifestContent.get(counter)[0].matches(">Refrigerated")) && !(manifestContent.get(counter)[0].matches(">Ordinary")) && counter < manifestContent.size() ) {
									for (int inventoryCount = 0; inventoryCount < storeInventory.getLength(); inventoryCount++) {
										if (manifestContent.get(counter)[0].matches(storeInventory.getItem(inventoryCount).getName())) {
											truckStock.addItem(storeInventory.getItem(inventoryCount));
											reduceValue += storeInventory.getItem(inventoryCount).getManufacturePrice() * Double.parseDouble(manifestContent.get(counter)[1]);
											storeInventory.getItem(inventoryCount).setQuantity(storeInventory.getItem(inventoryCount).getQuantity() + Integer.parseInt(manifestContent.get(counter)[1]));
										}
									}
									counter++;
									if (counter == manifestContent.size()) {
										break;
									}
								}
							
								try {
									coldTruck = new RefrigeratedTruck(truckStock);
									reduceValue += coldTruck.getCost();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					
						
																					
						for (int manifestCount = 0; manifestCount < manifestContent.size();  manifestCount++) {
							if (manifestContent.get(manifestCount)[0].matches(">Ordinary")) {
								Stock ordStock = new Stock();
								OrdinaryTruck ordTruck = new OrdinaryTruck(ordStock);
								int count = manifestCount + 1;
								while (!(manifestContent.get(count)[0].matches(">Refrigerated")) && !(manifestContent.get(count)[0].matches(">Ordinary")) && count < manifestContent.size() ) {
									for (int inventoryCount = 0; inventoryCount < storeInventory.getLength(); inventoryCount++) {
										if (manifestContent.get(count)[0].matches(storeInventory.getItem(inventoryCount).getName())) {
											ordStock.addItem(storeInventory.getItem(inventoryCount));
											reduceValue += storeInventory.getItem(inventoryCount).getManufacturePrice() * Double.parseDouble(manifestContent.get(count)[1]);
											storeInventory.getItem(inventoryCount).setQuantity(storeInventory.getItem(inventoryCount).getQuantity() + Integer.parseInt(manifestContent.get(count)[1]));
										}
									}
									count++;
									if (count == manifestContent.size()) {
										break;
									}
								}
								try {
									ordTruck = new OrdinaryTruck(ordStock);
									reduceValue += ordTruck.getCost();
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
						
							
						
							for (int inventCount = 0; inventCount < storeInventory.getLength(); inventCount++) {
								Object[] tempData = { storeInventory.getItem(inventCount).getName(), storeInventory.getItem(inventCount).getQuantity(), storeInventory.getItem(inventCount).getManufacturePrice(), 
										storeInventory.getItem(inventCount).getSellPrice(), storeInventory.getItem(inventCount).getReorderPoint(),
										storeInventory.getItem(inventCount).getReorderAmount(), storeInventory.getItem(inventCount).getTemperature() };
								tableData.addRow(tempData);
							}
							
							
							supermarket.updateCapital(supermarket.getCapital() - reduceValue);
							capitalLabel.setText("Store Capital: " + supermarket.getCapital());		
							importItemProperties.setEnabled(false);
							inventoryTable.disable();
							inventoryTable.repaint();
							mainFrame.repaint();
						}
									
					}
					else {
						JOptionPane.showMessageDialog(manifestFrame, "Error: Input was not a CSV File");
					}
				}

			}
			
		);
		
		
	
		
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
				
				
				
				Manifest manifest = new Manifest();							
				Stock items = new Stock();
				Stock oItems = new Stock();
				Stock tCargo = new Stock();
				int cOrderQuantity = 0;
				
				
				// Iterate through all store inventory items.
				Stock g = supermarket.getInventory();
				for(Item i : g.getStock())
				{
					// Check if order more of item.
					if(i.getQuantity() <= i.getReorderPoint())
					{
						Item I = i;
						I.setQuantity(I.getReorderAmount());
						if(I.getTemperature() != null) 
						{
							items.addItem(I);
							cOrderQuantity += I.getQuantity();
							
						}
						else
						{
							oItems.addItem(I);
						}
						
					}
				}
				
				
				
				
				//System.out.println("Quantity: "+numColdTrucks);
				
			
				int count = items.getLength() - 1;
				boolean sFlag = true;
				
			
				
				// Sort cold cargo.
				while(sFlag)
				{
					sFlag = false;					
					for(int i = 0; i < count; i++)
					{								
						if(items.getItem(i+1).getTemperature() < items.getItem(i).getTemperature())
						{
							Item c = items.getItem(i);
							items.getStock().set(i, items.getItem(i+1));
							items.getStock().set(i+1, c);							
							sFlag = true;												
						}
					}				
				}
				
			
				// Add warm stock to end of cargo.
				for(Item i : oItems.getStock()) {
					//items.getStock().add(i);	
					//System.out.println(i.getName()+" Quantity: "+i.getQuantity());
				}
				
				
				
				int numItems = 0;		
				String truckType = ">Refrigerated";
				double capacity = 800.0;	
				
				
				for(int i = 0; i < items.getLength(); i++)
				{				
					// Set items to manipulate.
					Item c = items.getItem(i);
					Item d = items.getItem(i);
						
					// If item type cannot fit in truck.
					if(c.getQuantity() + numItems > capacity)
					{	
						d.setQuantity(d.getQuantity() - (int)(capacity - numItems));
						c.setQuantity(c.getQuantity() - d.getQuantity());
						if(((int) (capacity - numItems)) > 0) {
							tCargo.addItem(d);
							
						}
						
						
						System.out.println(c.getName()+" Quantity: "+c.getQuantity()+" Minus: "+d.getQuantity());
						
						
						
						manifest = addTruckToManifest(tCargo, manifest);	// Add cargo to manifest as truck.						
						tCargo = new Stock();								// Empty temp cargo.
						numItems = 0;
						
						for(int x = 0; x < Math.ceil(c.getQuantity() / capacity); x++)
						{
							// Check if cold cargo.
							if(checkCold(tCargo) == false)	
							{
								capacity = 1000.0;
								truckType = ">Ordinary";
							}
						
							
							if((int)c.getQuantity() > capacity)
							{
								d.setQuantity(c.getQuantity() - (int)capacity - numItems);
								c.setQuantity((int)c.getQuantity() - d.getQuantity());								
								tCargo.addItem(d);
								
								manifest = addTruckToManifest(tCargo, manifest);	// Add cargo to manifest as truck.
								tCargo = new Stock();								// Empty temp cargo.
								numItems = 0;								
							}
							else
							{							
								d.setQuantity(c.getQuantity());
								tCargo.addItem(d);								
								numItems += d.getQuantity();
							}
						}
					}
					else
					{						
						tCargo.addItem(d);
						numItems += d.getQuantity();
					}
				}
						
						
					
				// CSV file builder.
				StringBuilder sb = new StringBuilder();
				
				String newline = System.getProperty("line.separator");
				OrdinaryTruck x = new OrdinaryTruck(tCargo);
				// Debug manifest.
				for(Truck t : manifest.getManifest())
				{
					if(t.getClass() == x.getClass()) {
					//	truckType = ">Ordinary";
					}
					// Parse truck type.
					sb.append(truckType);	
					
					// Parse truck cargo.
					for(int j = 0; j < t.getCargo().getStock().size(); j++)
					{
						Item r = t.getCargo().getItem(j);
						sb.append(newline+r.getName()+","+r.getQuantity());					
					}			
					sb.append(newline);
				}
				
				pw.write(sb.toString());
				pw.close();
				System.out.println(sb);
				// Display message.
				JOptionPane.showMessageDialog(exportManifest, "Truck Manifest Exported");
			
			//csvChooserFrame.dispose();
			}
		});
		
		

		importSaleLog.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame salesChooserFrame = new JFrame();
				JFileChooser salesChooser = new JFileChooser();
				salesChooser.setFileFilter(new FileNameExtensionFilter(".csv", "csv"));				
				salesChooserFrame.add(salesChooser);
				
				int status = salesChooser.showOpenDialog(null);
				if (status == JFileChooser.CANCEL_OPTION) {
					salesChooserFrame.dispose();
				}
				// Open file chooser menu.
				if (status == JFileChooser.OPEN_DIALOG) {
					// Name of selected file.
					String fileName =  salesChooser.getSelectedFile().getName();
					if (fileName.matches(".*.csv")) {
						
						// File value array.
						ArrayList<String[]> readValues = new ArrayList<String[]>();
						
						try {
							// Values of file saved to an array.
							readValues = CSVReader.readCSV(salesChooser.getSelectedFile());
													
							for(int i = 0; i < storeInventory.getStock().size(); i++) {
								// Update store capital and inventory stock.
								if(storeInventory.getItem(i).getName().contains(readValues.get(i)[0])) {	
									// Remove sold items form store stock.
									// Add net profit (sale price - manufacture cost) to capital.
									storeInventory.getItem(i).setQuantity(storeInventory.getItem(i).getQuantity() - (int)Double.parseDouble(readValues.get(i)[1]));			
									supermarket.updateCapital(supermarket.getCapital() + (storeInventory.getItem(i).getSellPrice() - storeInventory.getItem(i).getManufacturePrice()));
									
									// Update table
									Object temp = tableData.getValueAt(i, 1);								
									temp = (Double.parseDouble(temp.toString()) - Double.parseDouble(readValues.get(i)[1]));								
									tableData.setValueAt(temp, i, 1);
								}
							}
							capitalLabel.setText("Store Capital: " + supermarket.getCapital());
							
						} catch (IOException e1) {							
							e1.printStackTrace();
						}
						
						// Display completion message pop-up.
						JOptionPane.showMessageDialog(salesChooserFrame, "Updated Store Inventory and Capital");
					}
					else {
						JOptionPane.showMessageDialog(salesChooserFrame, "Error: Input was not a CSV File");
					}
				}			
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
	
	 // Export manifest function/
	static boolean checkCold(Stock cargo)		
	{
		boolean Cold = false;
		for(Item j : cargo.getStock())
		{
			if(j.getTemperature() != null)
			{
				Cold = true;
			}
		}		
		return Cold;
	}
	
	static Manifest addTruckToManifest(Stock cargo, Manifest manifest)
	{
		if(checkCold(cargo))
		{
			RefrigeratedTruck rt = new RefrigeratedTruck(cargo);
			manifest.addTruck(rt);			
		}
		else
		{
			OrdinaryTruck ot = new OrdinaryTruck(cargo);
			manifest.addTruck(ot);			
		}
		return manifest;
	}

}

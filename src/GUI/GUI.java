package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;

import Assignment.Program.Item;
import Delivery.OrdinaryTruck;
import Delivery.refrigeratedTruck;
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
		

		double storeCapital = supermarket.getCapital();
		
		
		
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
							
							
							
							Object[] tempData = { foodItem.GetName(), foodItem.getQuantity(), foodItem.GetManufacturePrice(), 
									foodItem.GetSellPrice(), foodItem.GetReorderAmount(), foodItem.GetReorderPoint(), null };
							tableSettings.addRow(tempData);
							
							// ---- TEMP STORE STUFF ----
							storeInventory.addItem(foodItem);
							}
							
							if (readValues.get(stringcount).length == 6){
								
								Item foodItem = new Item(readValues.get(stringcount)[0],Double.parseDouble(readValues.get(stringcount)[1]),Double.parseDouble(readValues.get(stringcount)[2])
										,Integer.parseInt(readValues.get(stringcount)[3]),Integer.parseInt(readValues.get(stringcount)[4]),
										Double.parseDouble(readValues.get(stringcount)[5]));
								
							Object[] tempData = { foodItem.GetName(), foodItem.getQuantity(), foodItem.GetManufacturePrice(), 
									foodItem.GetSellPrice(), foodItem.GetReorderAmount(), foodItem.GetReorderPoint(), foodItem.GetTemperature() };
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
			}
			
		});
		
		importManifest.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame csvChooserFrame = new JFrame();
				JFileChooser csvChooser = new JFileChooser();
				ArrayList<String[]> manifestContent = new ArrayList<String[]>();
				storeInventory = supermarket.getInventory();
				double reduceCapitalValue = 0.0;
				int status = csvChooser.showOpenDialog(null);
				
				csvChooser.setFileFilter(new FileNameExtensionFilter(".csv", "csv"));
				
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
					
				
				capital = capital - reduceCapitalValue;
				capitalLabel.setText("Store Capital: " + capital);
				importItemProperties.setEnabled(false);
				inventoryTable.disable();
				inventoryTable.repaint();
				mainFrame.repaint();
					
					
					
				}
				
			else {
					JOptionPane.showMessageDialog(csvChooserFrame, "Error: Input was not a CSV File");
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

}

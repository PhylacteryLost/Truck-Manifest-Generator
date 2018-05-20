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
import Stock.Stock;
import Stock.Store;
import Stock.Item;

public class GUI {
	
	public static void main(String[] args) {		
		gui();
	}
	
	public static void gui() {
		
		// Store variables 
		
		Store supermarket = Store.GetStore();
		Stock storeInventory = new Stock();
		double storeCapital = supermarket.GetCapital();
		
		
		// Swing Variables 
		
		
		// Mainframe
		JFrame mainFrame = new JFrame();
		Dimension mainFrameSize = new Dimension(1000,600);
		
		mainFrame.setTitle("Inventory");
		mainFrame.setPreferredSize(mainFrameSize);
		mainFrame.setLayout(new BorderLayout());
		
		// Table 
		DefaultTableModel tableSettings = new DefaultTableModel(new Object[]{"Name", "Quantity", "Manufactoring Cost", "Sell Price", "Reorder Point", "Reorder Amount", "Temperature"}, 0);
		JTable inventoryTable = new JTable(tableSettings);

		
		// Menu Bar 
		JMenuBar menuBar = new JMenuBar();
		menuBar.setLayout(new BorderLayout());

		// Labels
		
		JLabel capitalLabel = new JLabel("Store Capital: " + storeCapital);
		
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
							//manifestFilePath = csvChooser.getSelectedFile().getParentFile().getPath()+"\\";
							
							String regex = "item_properties";
							//manifestFilePath.replaceAll("item", "manifest");
							//System.out.println(manifestFilePath);
							System.out.println(regex);
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
						mainFrame.pack();
						
								
					}
					else {
						JOptionPane.showMessageDialog(csvChooserFrame, "Error: Input was not a CSV File");
					}
					
				}
				// Update store inventory with new one.
				supermarket.UpdateInventory(storeInventory);
				// Display pop-up message after completing task.
				
				importSaleLog.setEnabled(true);
				importManifest.setEnabled(true);
				exportManifest.setEnabled(true);
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
		mainFrame.setVisible(true);
		mainFrame.pack();
		
		
		
		
	}

}

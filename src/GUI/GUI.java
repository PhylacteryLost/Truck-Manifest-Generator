package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.table.*;

import Stock.Store;

public class GUI {
	
	public static void main(String[] args) {		
		gui();
	}
	
	public static void gui() {
		
		// Store variables 
		
		Store supermarket = Store.GetStore();
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

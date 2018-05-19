package GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
	
	/*
	 *  Reads the CSV file by line breaking the strings up by ,'s
	 *  Each string is places into an array for easy gathering.
	 *  
	 *  @author Kyle Langton
	 *  @param File: The CSV file
	 *  @return ArrayList<String[]> Returns an arrayList of string arrays containing the seperated values of each line
	 */
	public static ArrayList<String[]> readCSV(File fileName) throws IOException {
		FileReader reader = new FileReader(fileName);
		BufferedReader buffReader = new BufferedReader(reader);
		ArrayList<String[]> stringsBrocken = new ArrayList<String[]>();
		String textDocument;
		while ((textDocument = buffReader.readLine()) != null) {
				String[] splitStrings = textDocument.split(",");
				stringsBrocken.add(splitStrings);
		}
		buffReader.close();
		return stringsBrocken;
}

}

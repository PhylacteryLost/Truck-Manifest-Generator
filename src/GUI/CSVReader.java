package GUI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
	
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Loader {
	
	
	
	public ArrayList<Point> loadData(String fileName) {
		
		ArrayList<Point> citiesCoords = new ArrayList<Point>(200);
		try {
		      File file = new File(fileName);
		      BufferedReader reader = new BufferedReader(new FileReader(file));
		      String line = null;
		      
		      while(!(line = reader.readLine()).equals("NODE_COORD_SECTION")) {
		    	  
		      }
		      
		      while (!(line= reader.readLine()).equals("EOF")) {
		    	String [] split = line.trim().split("\\s+");
		    	double coordX = Double.parseDouble(split[1]);
		    	double coordY = Double.parseDouble(split[2]);
		    	citiesCoords.add(new Point(coordX, coordY));
		    	
		      }
		      
		      reader.close();
		    }
			catch(Exception ex) {
		      ex.printStackTrace();
		    }
		return citiesCoords;
	}


}

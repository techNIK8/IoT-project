package reading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reading {
	static File reading = new File("Reading.csv");
	public static void main(String[] args) throws IOException {
		
		//createFile();
		
		readCSV();
	}

	private static void createFile() throws IOException {
		// TODO Auto-generated method stub
		if(reading.createNewFile()){
            System.out.println("file.txt File Created in Project root directory");
        }else System.out.println("File file.txt already exists in the project root directory");
		
	}
	
	private static void readCSV() {

        String csvFile = "Reading.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                
                for(String s : country) {
                	System.out.println(s);
                }


            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        
        }
	}
}


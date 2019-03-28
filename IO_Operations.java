import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IO_Operations {
	/**
	 * reading all lines of input file and adding to an arraylist
	 * 
	 * @throws IOException  If an input exception occoured
	 * @see java.io.IOException
	 * @see java.nio.file.Files
	 * @see java.nio.file.Paths
	 * 
	 * @param path		string that holds the path of target input file
	 * @return			returning an arraylist that holds lines of the input file
	 */
	
	public static String[] readCommandFile(String path){
		try{
			int i = 0;
			int lenght = Files.readAllLines(Paths.get(path)).size();
			String[] results = new String[lenght];
			
			for (String line:Files.readAllLines(Paths.get(path))) {
				results[i++] = line;
			}
			
			return results;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * That function gets the personnel's id number, and uses it to make an output file
	 * After making the output writer object, it returns in PrintWriter class
	 * 
	 * @throws IOException  if an output exception occoured
	 * @see java.io.IOException
	 * @see java.io.PrintWriter
	 * 
	 * @param registerNumber	it holds the identification number of personnel, we need that attribute to use as name of output file
	 * @return					it returns print writer file
	 */
	
	public static PrintWriter openFile(String fileName){
		File output = new File(fileName);
		try{
			PrintWriter outWriter = new PrintWriter(output);
			return outWriter;
		}catch(IOException e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param commandLines
	 */
	public static void writeToFile(String[] commandLines, String path){
		PrintWriter outWriter = openFile(path);
		for(String line : commandLines){

				outWriter.println(line);
			
		}
		outWriter.close();
	}
}

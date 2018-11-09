import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class SOS_Reader {
	// Class that reads in the text file and can return it as a list of lists

	ArrayList<ArrayList<Integer>> setOfSets = null; // r1 index 0, etc.
	Iterator<ArrayList<Integer>> it = null;
	int size;
	int min;
	int max;
	
	int min_line;
	int max_line;

	public SOS_Reader(String fileName) {
//		ArrayList<String> lines = new ArrayList<String>();
		setOfSets = new ArrayList<>();
		
		try {
			BufferedReader in = null;
			try {
				// // abstract base class for representing an input stream of bytes
				InputStream inStream = new FileInputStream(fileName);
				// // using a Reader for input
				Reader r = new InputStreamReader(inStream, StandardCharsets.UTF_8); // for umlaute etc

				in = new BufferedReader(r);
				
				int currentNumber;
				ArrayList<Integer> numbers_of_line;
				String line = "";
//				int lineInteger = 0; // number of the line

				while (in.ready()) {
					size++;
//					lineInteger++; // Line number incrementation
					line = in.readLine(); // reading next line
					
					numbers_of_line = new ArrayList<>(); //Create new empty list to be filled with numbers of current line
					
					String[] numbers_line = line.split(" "); //Split on blank!
										
					
					for (String nr: numbers_line) {
						currentNumber = Integer.parseInt(nr);
//						if(currentNumber < this.min) this.min = currentNumber;
//						if(currentNumber > this.max) this.max = currentNumber;
						numbers_of_line.add(currentNumber); //add current number to the array of the numbers of that line
//						System.out.println(currentNumber);
					}
				   
					min_line = Collections.min(numbers_of_line);
					max_line = Collections.max(numbers_of_line);
					
					if(size == 1) {
						min = min_line;
						max = max_line;
					}else {
						if(min_line < min) {
							min = min_line;
						}
						if(max_line > max) {
							max = max_line;
						}
					}
					
			
					// Add list of numbers of that line to the list of lists
					setOfSets.add(numbers_of_line);
					
				}
			} catch (Exception e) {
				System.out.println(">> Exception 1: " + e.getMessage());
			}
			// these instructions are always executed
			finally {
				if (in != null)
					in.close();
				// System.out.println("All closed");
			}
		} catch (Exception e) {
			System.out.println(">> Exception 2: " + e.getMessage());
		}

		it = getIterator();

	}

//	public int size() {
//		return sentences.size();
//	}
//
	
	public int size() {
		return this.size;
	}
	public int size_inverted_list () {
		return this.max - this.min + 1;
	}
	
	public ArrayList<ArrayList<Integer>> getAllSets(){
		return this.setOfSets;
	}
	
	public int min_value() {
		return this.min;
	}
//	
//	public int max_value() {
//		return this.min;
//	}
	
	public Iterator<ArrayList<Integer>> getIterator() {
		return setOfSets.iterator();
	}
//
//	// returns the next sentence
//	// returns null if there is no more sentence
	public ArrayList<Integer> nextSet() {
		if (it.hasNext())
			return it.next();
		else
			return null;
	}
//
	// test if there exists a next sentence
	public boolean hasNextSet() {
		return it.hasNext();
	}
}


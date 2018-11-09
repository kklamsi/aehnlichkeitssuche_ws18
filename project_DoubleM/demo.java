import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class demo {

	public static void main(String[] args) {
		// read in every line and just print it
		SOS_Reader rdr = new SOS_Reader("C:\\Users\\Marcel\\Documents\\git\\Ähnlichkeitssuche\\Data\\folien.txt"); // Change this to your file's location
		// Later we change the rdr, so on "next" it returns the next line
		// without scanning the whole document before and saving it in the SOS_Reader object
		double t_j = 0.5;
		
		System.out.println("Min Value " + rdr.min_value());
//		int j = 10;
//		for(int i = 0;i < j;i ++) {
//			System.out.println(i);
//		}
//		last element : 9
		
		
//		Set of all Sets
		
		ArrayList<ArrayList<Integer>> SetofSets = rdr.getAllSets();
		ArrayList<Integer> r; //ArrayList for the current set from SetofSets
		double lb_r; // Lower bound for probing set
		
		
		// Inverted List:
		
		// Size:
		
		int InvListSize = rdr.size_inverted_list();
		System.out.println("Size of Inverted List :" + InvListSize);
		
		ArrayList<Integer>[] I = new ArrayList[InvListSize];
		int [] startValues = new int[InvListSize]; // Starting values for iteration on inverted listS; Initialized with all zero's
		
		int probing_prefix_length_r;
		int indexing_prefix_length_r;

		int currentNumber_of_r; //Current token of probing set
		int arrayIndex_InvertedList; //Index in the inverted list, where the current number belongs
		ArrayList<Integer> current_I_Array; // ArrayList for number from the Inverted list
		int currentStartingValue; //Where in the current_I_Array should i begin to look for possible matches
		
		int setOfSetsindex_of_current_inv_list_set; //the index of the current set from the for loop that runs through one ArrayList of the inverted list - This is the location of the set in SetOfSets
		
		HashMap<Integer, Integer> M; // M Map, with key = Candidate(Set) Index, value = number of intersecting tokens so far
		int c = 0;
		for(int i = 0; i < SetofSets.size(); i ++) {
			M = new HashMap<>(); // empty HashMap
			c++;
		
			
			//get current probing set
			r = SetofSets.get(i);
			System.out.println("Current Set" + r.toString());
			//get probing prefix length of set r
			probing_prefix_length_r = probing_prefix_length(r, t_j);
			//get lower bound of size for matching with set r
			lb_r = lbr( t_j, r.size());

			if(i != 0) {
				for(int p = 0; p < probing_prefix_length_r; p ++) { // Zeile 5
					
					currentNumber_of_r = r.get(p);
					
					// Now look for that current number of r in the inverted list


					arrayIndex_InvertedList = currentNumber_of_r - rdr.min_value();
					System.out.println("arrayIndex_InvertedList " + arrayIndex_InvertedList);
					current_I_Array = I[arrayIndex_InvertedList];
					System.out.println(current_I_Array);
					
					if(current_I_Array != null) {
						currentStartingValue = startValues[arrayIndex_InvertedList];
						
						
						for (int j = currentStartingValue; j < current_I_Array.size(); j ++) { 
							setOfSetsindex_of_current_inv_list_set = current_I_Array.get(j);
							
							if(SetofSets.get(setOfSetsindex_of_current_inv_list_set).size() < lb_r) { // Zeil 8
								// increment starting value in startingValues array at location [currentNumber_of_r - rdr.min]
								startValues[arrayIndex_InvertedList]++; // Zeile 10
								
							}else {
								
								if(M.get(setOfSetsindex_of_current_inv_list_set) == null) {
									M.put(setOfSetsindex_of_current_inv_list_set, 0);
								}
								M.put(setOfSetsindex_of_current_inv_list_set, M.get(setOfSetsindex_of_current_inv_list_set) + 1); // Zeile 14		
							}
						}
					}
		
					
				} // End of For loop from line 5 to 14
			}
			
			
			
			//Update Inverted List Index
			System.out.println("Size r :" + r.size());
			indexing_prefix_length_r = indexing_prefix_length(r.size(), t_j);
			System.out.println("EQO of current set:" + eqoverlap(t_j,  r.size(),   r.size()));
			System.out.println("Indexing Prefix length of current set: " + indexing_prefix_length_r);
			
//			System.out.println("Laenge indexing prefix" + indexing_prefix_length_r);
			// Loop over every element of the indexing prefix of set r
			// and add the INDEX of the set r to the hashmap key of the current element
			
			for(int p = 0; p < indexing_prefix_length_r; p++) { //Zeile 15
				currentNumber_of_r = r.get(p);
				System.out.println("Adding number " + currentNumber_of_r + " to the inverted list");
				//add this number to the right position in the inverted list array
				// Position = currentNumber_of_r - rdr.min //Try in your head
//				I[Position] is ArrayList of Integers giving the locations of the sets in the SetOfSets
				
				arrayIndex_InvertedList = currentNumber_of_r - rdr.min_value();
				System.out.println("Position in Inverted List: " + arrayIndex_InvertedList);
				

				if(I[arrayIndex_InvertedList] == null) {
					I[arrayIndex_InvertedList] = new ArrayList<Integer>();
					I[arrayIndex_InvertedList].add(i); //Zeile 17
				}else {
					I[arrayIndex_InvertedList].add(i); //Zeile 17
				}
				
			}//Zeile 16 fertig
			
			// get indexing prefix of set r:?
			
			

		}

		System.out.println("We have " +  c + " Sets in our file");
	
		size_array(I);
		
		InvList_toString(I);

	}

	
	private static void size_array(ArrayList<Integer>[] I) {
		
		System.out.println(I.length);
		
	}

	
	private static void InvList_toString(ArrayList<Integer>[] I) {


		for(int z = 0;z < I.length; z ++) {
			System.out.println("Sets for number " + z);
			ArrayList<Integer> indices = I[z];
			if(indices != null) {
				System.out.println(I[z].toString());
			}else {
				System.out.println("None");
			}
			
		}

	}
	
	private static double eqoverlap (double t_j, int set_size_probing, int set_size_comparison) {
		return (t_j / (1+ t_j)) * (set_size_probing + set_size_comparison);
	}
	//UBR And LBR
	private static double lbr(double t_j, int set_size_probing)
	{
		//Lower bound of set size s to reach jaccard dist t_j with set r
		return  (t_j * set_size_probing);
	}
	private static double ubr(double t_j, int set_size_probing)
	{
		//upper bound of set size s to reach jaccard dist t_j with set r
		return  (set_size_probing  / t_j);
	}
	//For both probing and indexing:
	// Maybe calculate the size of the probing set once before and then give that as a paramters, instead of the set
	private static int probing_prefix_length(ArrayList<Integer> probing_set, double t_j) 
	{
			int size_set_probing = probing_set.size();
			return size_set_probing - (int) Math.ceil(lbr(t_j, size_set_probing))  + 1;
	}
	private static int indexing_prefix_length(int probing_set_size, double t_j)
	{
		return probing_set_size - (int) Math.ceil(eqoverlap(t_j, probing_set_size, probing_set_size)) + 1;
	}
}


import java.util.ArrayList;
import java.util.HashMap;

public class AllPairs {

	public static void main(String[] args) {
		// read in every line and just print it
//		SOS_Reader rdr = new SOS_Reader("Data\\folien_mod.txt");
//		SOS_Reader rdr = new SOS_Reader("Data\\zipf.txt");
//		SOS_Reader rdr = new SOS_Reader("Data\\bms.txt");
//		SOS_Reader rdr = new SOS_Reader("Data\\bms.txt");
//		
		
		SOS_Reader rdr = new SOS_Reader(args[0]);
		
		// Later we change the rdr, so on "next" it returns the next line
		// without scanning the whole document before and saving it in the SOS_Reader object
		double t_j = Double.valueOf(args[1]);
		//bms t = 0.5 => 49929118 matches
		//bms t = 1.0 => 49929066 matches
		int pairs = 0;

		
//		System.out.println("Min Value " + rdr.min_value());
//		int j = 10;
//		for(int i = 0;i < j;i ++) {
//			System.out.println(i);
//		}
//		last element : 9
		
		
//		Set of all Sets
		
		ArrayList<ArrayList<Integer>> R = rdr.getAllSets();
		ArrayList<Integer> r; //ArrayList for the current set from SetofSets
		double lb_r; // Lower bound for probing set
		
		
		// Inverted List:
		
		// Size:
		
		int InvListSize = rdr.size_inverted_list();
//		System.out.println("Size of Inverted List :" + InvListSize);
		
		ArrayList<Integer>[] I = new ArrayList[InvListSize];
		int [] startValues = new int[InvListSize]; // Starting values for iteration on inverted listS; Initialized with all zero's
		
		int probing_prefix_length_r;
		int indexing_prefix_length_r;

		int currentNumber_of_r; //Current token of probing set
		int arrayIndex_InvertedList; //Index in the inverted list, where the current number belongs
		ArrayList<Integer> current_I_ArrayList; // ArrayList for number from the Inverted list
		int currentStartingValue; //Where in the current_I_Array should i begin to look for possible matches
		
		int R_index_of_current_inv_list_set; //the index of the current set from the for loop that runs through one ArrayList of the inverted list - This is the location of the set in SetOfSets
		
		HashMap<Integer, Integer> M; // M Map, with key = Candidate(Set) Index, value = number of intersecting tokens so far
		int c = 0;
	

		//Timing:


        // sum of square roots of integers from 1 to n using Math.sqrt(x).
//		System.out.println("go");
        StopwatchCPU timer1 = new StopwatchCPU();
        
       
        
//    	int size_list =  R.size();
		//
		for(int i = 0; i < R.size(); i ++) {
//		for(int i = 0; i < (int) Math.floor(99984.0/1); i ++) {
			M = new HashMap<>(); // empty HashMap
			c++;
		
//			System.out.println(100 *  (double) i / (double) (int) Math.floor((double)size_list/1) + " % done");
//			System.out.println(R.size());
			//get current probing set
			r = R.get(i);
//			System.out.println("Current Set r" + (i+1) + ": "+ r.toString());
			//get probing prefix length of set r
			probing_prefix_length_r = probing_prefix_length(r.size(), t_j);
			indexing_prefix_length_r = indexing_prefix_length(r.size(), t_j);
			//get lower bound of size for matching with set r
			lb_r = lbr( t_j, r.size());
			
//			System.out.println("Lower bound for match for current set: " + lb_r);

//			System.out.println("Probing Prefix Length: " + probing_prefix_length_r);
//			System.out.println("Indexing Prefix Length: " + indexing_prefix_length_r);
//			System.out.println("Lower bound: " + lb_r);
//			System.out.println("EQO_self: " + eqoverlap(t_j, r.size(), r.size()));
//			
			
			if(i != 0) {
				for(int p = 0; p < probing_prefix_length_r; p ++) { // Zeile 5
					
					currentNumber_of_r = r.get(p);
					
					// Now look for that current number of r in the inverted list


					arrayIndex_InvertedList = currentNumber_of_r - rdr.min_value();
//					System.out.println("arrayIndex_InvertedList " + arrayIndex_InvertedList);
					current_I_ArrayList = I[arrayIndex_InvertedList];
//					System.out.println(current_I_Array);
					
					if(current_I_ArrayList != null) {
						currentStartingValue = startValues[arrayIndex_InvertedList];
						
//						System.out.println("!!!  Rest of Array of Inverted List index for number " + currentNumber_of_r);
						
						for (int j = currentStartingValue; j < current_I_ArrayList.size(); j ++) { //Zeile 10
							R_index_of_current_inv_list_set = current_I_ArrayList.get(j);
//							System.out.println("!!!  r" +(R_index_of_current_inv_list_set+1));
							if(R.get(R_index_of_current_inv_list_set).size() < lb_r) { // Zeil 8
								// increment starting value in startingValues array at location [currentNumber_of_r - rdr.min]
								startValues[arrayIndex_InvertedList]++; // Zeile 10
								
							}else {
//								System.out.println("Size of possible candidate: " + R.get(R_index_of_current_inv_list_set).size());
								if(M.get(R_index_of_current_inv_list_set) == null) {
									M.put(R_index_of_current_inv_list_set, 0);
								}
								M.put(R_index_of_current_inv_list_set, M.get(R_index_of_current_inv_list_set) + 1); // Zeile 14		
							}
						}
					}
		
					
				} // End of For loop from line 5 to 14
			}
			
			
			
			//Update Inverted List Index
//			System.out.println("Size r :" + r.size());
			
//			System.out.println("EQO of current set:" + eqoverlap(t_j,  r.size(),   r.size()));
//			System.out.println("Indexing Prefix length of current set: " + indexing_prefix_length_r);

			
			
			// Loop over every element of the indexing prefix of set r
			// and add the INDEX of the set r to the hashmap key of the current element
			
			for(int p = 0; p < indexing_prefix_length_r; p++) { //Zeile 15
				currentNumber_of_r = r.get(p);
//				System.out.println("Adding number " + currentNumber_of_r + " to the inverted list");
				//add this number to the right position in the inverted list array
				// Position = currentNumber_of_r - rdr.min //Try in your head
//				I[Position] is ArrayList of Integers giving the locations of the sets in the SetOfSets
				
				arrayIndex_InvertedList = currentNumber_of_r - rdr.min_value();
//				System.out.println("Position in Inverted List: " + arrayIndex_InvertedList);
				

				if(I[arrayIndex_InvertedList] == null) {
					I[arrayIndex_InvertedList] = new ArrayList<Integer>();
					I[arrayIndex_InvertedList].add(i); //Zeile 17
				}else {
					I[arrayIndex_InvertedList].add(i); //Zeile 17
				}
				
			}//Zeile 16 fertig
			
			// get indexing prefix of set r:?
			
			//Print M:
//			System.out.println("  Printing Candidates for set r" + (i+1));
//			System.out.println("    " + M.toString());
			
			
			
			//Verify:
			pairs+=Verify(i, R, r, M, t_j);

			
		}

//		System.out.println("We have " +  c + " Sets in our file");
	
//		size_array(I);
		
//		InvList_toString(I);
		
		
//		//Print Start Values: startValues
//		System.out.println("Printing Starting Values");
//		print_array(startValues, rdr);

		
		System.out.println(pairs);
		
//		 double time1 = timer1.elapsedTime();
		System.out.println(timer1.elapsedTime());
		
		
	}

	
	private static int Verify(int index_set_r, ArrayList<ArrayList<Integer>> R, ArrayList<Integer> r, HashMap<Integer, Integer> M, double t_j) {
//		input : r: probing set
//				M: candidates map (candidate  -> overlap) 
//				tJ: Jaccard threshold

		
		int res = 0;
		boolean ret;
		
		
		int o; // Overlap so far
		
		int probing_pre_length_r; // probing prefix length of r;
		int indexing_pre_length_s; // indexing prefix length of s;

		int w_r; // Last token of prefix in r (probing set r)
		int w_s; // Last token of prefix in s (current candidate)
		
		ArrayList<Integer> s;
		
//		int print_index_s;
		
		double t; //eqoverlap(r, s, t_j)
		for(Integer index_s: M.keySet()) {
//			print_index_s = index_s + 1;
			
			// s is the index of the first element in M
			// the corresponding value of that key is the overlap
			o = M.get(index_s); //Get overlap of current set with probing set r calculated on the prefix of r
			s = R.get(index_s); //complete set s
			
//			System.out.println("  Verifying set r" + print_index_s + ": " + s.toString());
			
			probing_pre_length_r = probing_prefix_length(r.size(), t_j) ;
			indexing_pre_length_s = indexing_prefix_length(s.size(), t_j);

			w_r = r.get(probing_pre_length_r - 1);			// Last token of prefix in r
			w_s = s.get(indexing_pre_length_s - 1);			// Last token of prefix in s

			t = Math.ceil(eqoverlap(t_j, r.size(), s.size()));
			
//			System.out.println("     Overlap t needed to be reached: "  + t);
			if(w_r < w_s) {
				ret = verify_ssjoin_paper(index_s, index_set_r, r, s, t, o, probing_pre_length_r , o );
			}else {
				ret = verify_ssjoin_paper(index_s, index_set_r, r, s, t, o, o , indexing_pre_length_s );
			}
			if(ret) {
				res++;
			}
		}
		
		
//		System.out.println("  Verified Set r" + (index_set_r+1) + ": " + r.toString());
//		System.out.println();
		return res;
	}
	
	
	private static boolean verify_ssjoin_paper(int index_set_s, int index_set_r, ArrayList<Integer>r, ArrayList<Integer>s, double t, int olap, int pr, int ps) {
		
//	Input: 	r, s: 						sets to be verified (sorted arrays);
//			t: 							required overlap;
//			olap, pr, ps: 				overlap up to positions pr, ps in r, s
//	Result: true if #Same elements in r and s >=  t, i.e., (r, s) is in the result set

//		index_set_r++;
//		index_set_s++;
		int maxr = r.size() - pr + olap;
		int maxs = s.size() - ps + olap;
		
		while(maxr >= t && maxs >= t && olap  < t) {
			if(r.get(pr).equals(s.get(ps))) {
				pr++;				ps++;				olap++;
			}else if(r.get(pr) < s.get(ps)) {
				pr++;				maxr--;
			}else {
				ps++;				maxs--;
			}
		}
		
//		System.out.println("     Reached Overlap: " + olap);
		if(olap >= t) {
//			System.out.println("     r" + index_set_r + ": " + r.toString() +  
//					" and r" + index_set_s + ": " + s.toString() + " are a match");		
		}
		return olap >= t;
	}
	
	private static void print_array(int[] array, SOS_Reader rdr) {
		for(int i = 0; i < array.length; i ++) {
			System.out.println(i + rdr.min_value() + ": " + array[i]);
		}
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
	private static int probing_prefix_length(int probing_set_size, double t_j) 
	{
//			int size_set_probing = probing_set.size();
			return probing_set_size - (int) Math.ceil(lbr(t_j, probing_set_size))  + 1;
	}
	private static int indexing_prefix_length(int indexing_set_size, double t_j)
	{
		return indexing_set_size - (int) Math.ceil(eqoverlap(t_j, indexing_set_size, indexing_set_size)) + 1;
	}
}

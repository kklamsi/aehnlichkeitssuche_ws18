package project_klemensRaoul;


import java.util.*;
//import javax.swing.JOptionPane;
import java.util.stream.Collectors;


public class AllPairs {
	
	public static void main(String[] args) {
		
		double threshold = .5;
		
		// probably way to complicated...
		
		List<List<Integer>> dataA = new ArrayList<List<Integer>>();
		dataA.add(Arrays.asList(1,3,5));
		dataA.add(Arrays.asList(1,2,3,4));
		dataA.add(Arrays.asList(1,2,4,9,11));
		dataA.add(Arrays.asList(1,3,5,8,9,10,11,12,13,14));
		for (List<Integer> liste : dataA) {
		    for (Integer i : liste) {
		        System.out.print(i+" ");
		    }
		    System.out.println();
		}
		
		System.out.println(dataA.get(3).size());
		System.out.println(dataA.get(3).get(9));
		
		long start = System.nanoTime(); // start timing
		
		// invertedIndex:
		List<SortedSet<Integer>> index = new ArrayList<SortedSet<Integer>>(); /* I inverted list index covering prefix of sets */
		
		for(int r : dataA.get()) { /* process in ascending length order of r */
			Map<Integer, Integer> candidate = new HashMap<>(); 		/* dictionary for candidate set.  Key:  candidate,
																	value:  number of intersecting tokens found so far.  */
				
			int eqo; // t/(t+1)*(r.length + r.length)
			int lowerBound;	// threshold * r.length
			int probingPrefixLength; // r.length - lowerBound + 1
			int indexingPrefixLength; // r.length - eqo(r, r) + 1
				
				for(int p = 0; p < probingPrefixLength-1; p++) { /*	pi_r: probing prefix length of r */
					
					for(int s = 0; s < index; s++) { 
						
						if(s.length < lowerBound) { /* lb_r: length bound */
							// remove index entry s => shift to the next position
						}
						else if(s)
					}
								
				}
		}
		
		
		/*
		for(int i = 0; i < dataA.size(); i++) {
			for (int j = 0; j < dataA.get(i).size(); j++) {
				index.get(j).add(SortedSet.add(i));
			}
		}
		*/
		
			
		long end = System.nanoTime();
		System.out.println((end - start)*0.000000001 + " Sekunden");

	
	}
}

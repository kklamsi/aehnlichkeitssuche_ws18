package project_klemensRaoul;


import java.util.*;
//import javax.swing.JOptionPane;
import java.util.stream.Collectors;


public class AllPairs {
	
	public static void main(String[] args) {
		
		long start = System.nanoTime();
		
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
		
		List<SortedSet<Integer>> index = new ArrayList<SortedSet<Integer>>(); 
		
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

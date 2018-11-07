package project_klemensRaoul;


import java.util.*;
//import javax.swing.JOptionPane;
import java.util.stream.Collectors;


public class AllPairs {
	
	public static void main(String[] args) {
		
		long start = System.nanoTime();
		
		// initialize simple dataset:
		int[][] data = {{1,3,5}, 
						{1,2,3,4},
						{1,2,4,9,11},
						{1,3,5,8,9,10,11,12,13,14}};
		
		System.out.println(data[0][2]);
		//ArrayList<Integer>[];
		
		int[][] invertedInd = new int[data[data.length-1][data[data.length-1].length-1]][data.length];
		System.out.println(invertedInd[0][3]);
		
		
		for(int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				invertedInd[data[0][0]][i] = i;
			}
		}
		
		System.out.println(invertedInd[1][0]);
		
		/*
		//int[] r1 = {1,3,5};
		int[] r2 = {1,2,3,4};
		int[] r3 = {1,2,4,9,11};
		int[] r4 = {1,3,5,8,9,10,11,12,13,14};
		
		ArrayList<Integer> r1 = new ArrayList<Integer>();
		r1.add(1);
		//r1.add(2);
		//r1.add(4);
		//data2.add(r4);
		
		HashMap<String, ArrayList<Integer>> invertedIndex = new HashMap<String, ArrayList<Integer>>();
		invertedIndex.put("r1", r1);
		*/
		

		// set threshold:
		/*double threshold = 0;
		threshold = Double.parseDouble((String)JOptionPane.showInputDialog("Please enter a number between 0 and 1:"));
		System.out.println(threshold);*/
		
		// show data:
		for(int i = 0; i < data.length; i++) {
			System.out.println(Arrays.toString(data[i]));
		}
		
		
		// inverted list:
		
			/*
		  	Map<String, Character> myNewHashMap = new HashMap<>();
			for(Map.Entry<Character, String> entry : myHashMap.entrySet()){
    		myNewHashMap.put(entry.getValue(), entry.getKey());
			}
		 	https://stackoverflow.com/questions/20412354/reverse-hashmap-keys-and-values-in-java
		 	*/
		


		
		HashMap<Integer, SortedSet<String>> invertedList = new HashMap<Integer, SortedSet<String>>();
		SortedSet<String> helper = new TreeSet<>();

		//int k = 1;
		for (int k = 1; k < 15; k++) {
		for (int i = 0; i < data.length; i++) {
			
			for (int j = 0; j < data[i].length; j++) {
				if(data[i][j] == 1) {
					helper.add("r"+(i+1));
				}
	        	invertedList.put(data[i][j], helper);       	
	        }	
		} 
		}
		
		long end = System.nanoTime();
		
		int k = 1;
		
		System.out.println("key set: " + invertedList.keySet());
		System.out.println("rows with " + k + ": " + invertedList.get(1));
		System.out.println((end - start)*0.000000001 + " Sekunden");

	
	}
}

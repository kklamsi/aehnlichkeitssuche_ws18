package project_klemensRaoul;


import java.util.*;
//import javax.swing.JOptionPane;


public class AllPairs {
	
	public static void main(String[] args) {
		
		// initialize simple dataset:
		int[][] data = {{1,2,4}, 
						{1,3,4,5},
						{1,2,4,5,7},
						{2,3,5,6,7,9}};
		
		/*
		//int[] r1 = {1,2,4};
		int[] r2 = {1,3,4,5};
		int[] r3 = {1,2,4,5,7};
		int[] r4 = {2,3,5,6,7,9};
		
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
		HashMap<Integer, ArrayList<String>> invertedList = new HashMap<Integer, ArrayList<String>>();
		ArrayList<String> helper = new ArrayList<String>();

		int k = 1;

		for (int i = 0; i < data.length; i++) {
			
			for (int j = 0; j < data[i].length; j++) {
				if(data[i][j] == k) {
					helper.add("r"+(i+1));
				}
	        	invertedList.put(data[i][j], helper);       	
	        }		
		} 
		
		System.out.println("key set: " + invertedList.keySet());
		System.out.println("rows with " + k + ": " + invertedList.get(1));

	
	}
}

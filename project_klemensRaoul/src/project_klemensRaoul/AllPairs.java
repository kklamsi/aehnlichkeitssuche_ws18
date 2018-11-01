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
		
		int[] r1 = {1,2,4};
		int[] r2 = {1,3,4,5};
		int[] r3 = {1,2,4,5,7};
		int[] r4 = {2,3,5,6,7,9};
		
		ArrayList<int[]> data2 = new ArrayList<int[]>();
		data2.add(r1);
		data2.add(r2);
		data2.add(r3);
		data2.add(r4);
		
		HashMap<String, ArrayList<int[]>> invertedIndex = new HashMap<String, ArrayList<int[]>>();
		invertedIndex.put("r1", data2.add(r1));
		
		System.out.println(Arrays.toString(data2.get(1)));
		

		// set threshold:
		/*double threshold = 0;
		threshold = Double.parseDouble((String)JOptionPane.showInputDialog("Please enter a number between 0 and 1:"));
		System.out.println(threshold);*/
		
		// show data:
		for(int i = 0; i < data.length; i++) {
			System.out.println(Arrays.toString(data[i]));
		}
		
		
		/*inverted list:
		HashMap<Integer, ArrayList<Integer>> invertedList = new HashMap<Integer, ArrayList<Integer>>();
		
		for (int i = 0; i < data.length; i++) {
			ArrayList<Integer> helper = new ArrayList<Integer>();
			
	        for (int j = 0; j < data[i].length; j++) {
	        	invertedList.put(data[i], j);
	        }
 	    }*/
		
		
	}
}

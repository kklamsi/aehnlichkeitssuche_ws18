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


		// set threshold:
		/*double threshold = 0;
		threshold = Double.parseDouble((String)JOptionPane.showInputDialog("Please enter a number between 0 and 1:"));
		System.out.println(threshold);*/
		
		// show data:
		for(int i = 0; i < data.length; i++) {
			System.out.println(Arrays.toString(data[i]));
		}
		
		//inverted list:
		HashMap<Integer, ArrayList<Integer>> invertedList = new HashMap<Integer, ArrayList<Integer>>();
		
		for (int i = 0; i < data.length; i++) {           
	        for(int j = 0 < data[i].length; j++) {
	        	invertedList.put(i, j);
	        }
 	    }
		
		
	}
}

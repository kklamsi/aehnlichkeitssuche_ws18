package project_klemensRaoul;


//import java.util.ArrayList;
import java.util.Arrays;
//import javax.swing.JOptionPane;


public class AllPairs {
	
	public static void main(String[] args) {
		
		int[] r1 = {1,2,4};
		int[] r2 = {1,3,4,5};
		int[] r3 = {1,2,4,5,7};
		int[] r4 = {2,3,5,6,7,9};
		
		int[][] data = {{1,2,4}, 
						{1,3,4,5},
						{1,2,4,5,7},
						{2,3,5,6,7,9}
		};

		/*double threshold = 0;
		threshold = Double.parseDouble((String)JOptionPane.showInputDialog("Please enter a number between 0 and 1:"));
		System.out.println(threshold);*/
		
		System.out.println(Arrays.toString(data[0]));

		
		
	}

}

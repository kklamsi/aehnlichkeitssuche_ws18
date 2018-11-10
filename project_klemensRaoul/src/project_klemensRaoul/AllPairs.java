import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AllPairs {

    // equal overlap
    private static double eqo(List<Integer> r, List<Integer> s, double t){
        return (t/(1+t))*(r.size()+s.size());
    }

    private static int pi_r(List<Integer> r, double t){
        return r.size()-(int)Math.ceil(t*r.size())+1;
    }

    private static int pi_r_i(List<Integer> r,  double t){
        return r.size()-(int)Math.ceil(eqo(r,r,t))+1;
    }

    private static boolean verifySSJoin(List<Integer> r, List<Integer> s, int t, int o, int pr, int ps){
        int max_r = r.size() - pr + o;
        int max_s = s.size() - ps + o;

        while(max_r >= t && max_s >= t && o < t){
            if((int)r.get(pr) == (int)s.get(ps)){
                pr++; ps++; o++;
            } else if((int)r.get(pr) < (int)s.get(ps)){
                pr++; max_r--;
            } else {
                ps++; max_s--;
            }
        }

        return o >= t;
    }

    private static int verifyPairs(List<List<Integer>> R, List<Integer> r, HashMap<Integer, Integer> M, double threshold){
        int res = 0;
        boolean ret = false;

        // probing prefix length of r, indexing prefix length of s
        int p_r = 0;
        int p_s = 0;

        // last token of prefix in r (probing set r), last token of prefix in s (current candidate)
        // pi_r-th token in r, pi_s-th token in s
        int w_r = 0;
        int w_s = 0;

        // candidate from the hashmap for the probing set r
        List<Integer> s;

        // overlap so far
        int overlap;
        // required overlap for r and s
        int t;

        // looping over the candidates
        for (Map.Entry<Integer, Integer> entry : M.entrySet()) {
            // System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());

            s = R.get(entry.getKey());
            overlap = entry.getValue();
            t = (int)Math.ceil(eqo(r, s, threshold));

            p_r = pi_r(r, threshold);
            p_s = pi_r(s, threshold);
            w_r = r.get(p_r - 1);
            w_s = s.get(p_s - 1);

            if(w_r < w_s)
                ret = verifySSJoin(r, s, t, overlap, p_r, overlap);
            else
                ret = verifySSJoin(r, s, t, overlap, overlap, p_s);

            if(ret) res++;
        }

        return res;
    }

    private static long getCpuTime() {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean( );
        return bean.isCurrentThreadCpuTimeSupported( ) ?
                bean.getCurrentThreadCpuTime( ) : 0L;
    }

    public static void main(String[] args) {
        // reading the command line inputs: filename, threshold
        String filename = args[0];
        double threshold = Double.parseDouble(args[1]);

        // output: verified pairs and duration
        int pairs = 0;
        double duration = 0.0;
        double NANO = 1000000000.0;

        // index list size coming from the biggest integer in the set
        int maxListIndex = 1;

        // stored / probing set R
        List<List<Integer>> setR = new ArrayList<List<Integer>>();

        // File Reader
        // reading line by line until no line is left in the file
        // splitting every line by spaces and adding these integers to te integer list
        // finally add the integer list to the arraylist
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line = reader.readLine();

            while (line != null) {
                String[] line_elements = line.split(" ");
                ArrayList<Integer> splitIntegers = new ArrayList<>();

                // loop over the split integers, parse to integer, add to integer list
                // update maxIndex on last/biggest integer in line
                for(int i=0; i < line_elements.length; i++){
                    int newInteger = Integer.parseInt(line_elements[i]);
                    splitIntegers.add(newInteger);

                    // only check the last integer per line as it is the largest anyway
                    if(i==line_elements.length-1 && newInteger > maxListIndex) maxListIndex = newInteger;
                }

                // add the finished integer list to the arraylist of integer lists
                setR.add(splitIntegers);

                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // cpu timer start
        long start_time = getCpuTime( );

        // initialize the inverted index list and
        // a parallel running integer array for the starting positions of the index list
        int[] startIndexList = new int[maxListIndex];
        List<List<Integer>> indexList = new ArrayList<List<Integer>>(maxListIndex);
        // candidate set M
        HashMap<Integer, Integer> candidateSet;

        // by adding a dummy number and clearing it we set the size of the list
        // and can use its elements (integer lists) by indexing with ".get(index)" now
        for(int i=0; i < maxListIndex; i++){
            List<Integer> ini = new ArrayList<Integer>(Arrays.asList(0));
            indexList.add(ini);
            indexList.get(i).clear();
        }

        //System.out.println(indexList);

        // j: a reminder of the row number of the probing set
        // because the loop runs on iterators of the integer lists
        int j = 0;
        for(List<Integer> it : setR){
            // it: iterators of the integer lists in the set R

            candidateSet = new HashMap<Integer, Integer>();

            // pi_r and pi_r_i
            // probing prefix length, indexing prefix length
            int p_r   = pi_r(it,threshold);
            int p_r_i = pi_r_i(it,threshold);

            // probing prefix
            // looping over the elements of the probing integer list (of the set R) until pi_r
            for(int p=0; p < p_r; p++){
                // looping over the elements of the index list at point p, starting with i-th index (from the integer array)
                for(int i=startIndexList[it.get(p)-1]; i < indexList.get(it.get(p)-1).size(); i++){
                    int key = indexList.get(it.get(p)-1).get(i);

                    // length filter
                    if(setR.get(key).size() < threshold*it.size()){
                        startIndexList[it.get(p)-1]++;
                        //System.out.println("is in" + p);
                    } else{
                        // create a candidate if non-existant
                        if(!candidateSet.containsKey(key)) candidateSet.put(key, 0);
                        // increment the existing candidate
                        candidateSet.put(key, candidateSet.get(key) + 1);
                    }
                }
            }

            // index updating
            for(int p=0; p < p_r_i; p++){
                indexList.get(it.get(p)-1).add(j);
            }

            /* // testing start: probing set, index list, candidates, start index
            System.out.println("Probing Set "+j+": "+it);
            System.out.println("Index List "+ indexList);

            Set set2 = candidateSet.entrySet();
            Iterator iterator2 = set2.iterator();
            System.out.print("Candidates: ");
            while(iterator2.hasNext()) {
                Map.Entry mentry2 = (Map.Entry)iterator2.next();
                System.out.print("(r"+mentry2.getKey()+", "+mentry2.getValue()+") ");
            }

            System.out.print("\nStart Index: ");

            for(int i=0;i < startIndexList.length;i++){
                System.out.print(startIndexList[i]+" ");
            }

            System.out.println();
            System.out.println();
            // testing end */

            j++;

            // verification process
            // verifyPairs(List<List<Integer>> R, List<Integer> r, HashMap<Integer, Integer> M, double threshold)
            pairs += verifyPairs(setR, it, candidateSet, threshold);
        }

        duration = (getCpuTime() - start_time)/NANO;

        System.out.println(pairs);
        System.out.println(duration);
    }
}
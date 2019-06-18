import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class PercolationStats{

    public PercolationStats(int n, int trails){
        //Percolation p = new Percolation(n);
	for(int i = 0; i < trails; i ++){
	    System.out.println(StdRandom.uniform(0,n));
	}
    }
    public double mean(){
	return 0;
    }
    public double stddev(){
	return 0;
    }
    public double confidenceLo(){
	return 0;
    }
    public double confidenceHi(){
	return 0;	
    }

    public static void main(String[] args){
	System.out.println("Start");
    }
    

}

import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class PercolationStats{

    private int n;
    private int dim;
    
    public PercolationStats(int n, int trails){
	n = n;
	dim = (int) Math.sqrt(n);
	Percolation p = new Percolation(n);
	int it  = 0;
	while(! p.percolates()){
	    int x = StdRandom.uniform(dim);
	    int y = StdRandom.uniform(dim);
	    p.open(x,y);
	    it += 1;
	}
	System.out.println(p.numberOfOpenSites());
	System.out.println(it);	
	double sample = (double) p.numberOfOpenSites() / n;
	System.out.println(sample);
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
	PercolationStats ps = new PercolationStats(16,4);
	
    }
    

}

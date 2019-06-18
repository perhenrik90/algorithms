import java.lang.IllegalArgumentException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class PercolationStats{


    private int dim;
    private double [] samples;
    
    public PercolationStats(int n, int trails){
	dim = n;
	samples = new double[trails];

	for(int i = 0; i < trails; i++){
	    Percolation p = new Percolation(n);
	    int it  = 0;
	    while(! p.percolates()){
		int x = StdRandom.uniform(dim)+1;
		int y = StdRandom.uniform(dim)+1;
		p.open(x,y);
		it += 1;
	    }
	    double sample = (double) p.numberOfOpenSites() / (n*n);
	    samples[i] = sample;
	}

    }
    public double mean(){
	return StdStats.mean(samples);
    }
    public double stddev(){
	return StdStats.stddev(samples);
    }
    public double confidenceLo(){
	double z = (n - mean()) / stddev();
	return mean() + z * (stddev() / dim);
    }
    public double confidenceHi(){
	double z = (n - mean()) / stddev();	
	return mean() - z * (stddev() / dim);
	
    }

    public static void main(String[] args){
	// Parse input
	int n = Integer.parseInt(args[0]);
	int T = Integer.parseInt(args[1]);






















	PercolationStats ps = new PercolationStats(n,T);
	System.out.println("mean                    = "+ps.mean());
	System.out.println("stddev                  = "+ps.stddev());
	System.out.println("95% confidence interval = ["+ps.confidenceLo()+", "+ps.confidenceHi()+"]");
    }
    

}

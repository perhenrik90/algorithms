import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class PercolationStats{

    private final double [] samples;
    private final int T;
    private static final double Z = 1.96;
    
    public PercolationStats(int n, int trails){
	samples = new double[trails];
	T = trails;

	if(trails <= 0){
	    throw new IllegalArgumentException("Trails can not be less than equal zero.");
	}
	if(n <= 0){
	    throw new IllegalArgumentException("N must be higher than zero..");
	}
	
	for(int i = 0; i < trails; i++){
	    Percolation p = new Percolation(n);
	    int it  = 0;
	    while(! p.percolates()){
		int x = StdRandom.uniform(n)+1;
		int y = StdRandom.uniform(n)+1;
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
	return mean() - Z * stddev() / Math.sqrt(T);
    }
    public double confidenceHi(){
	return mean() + Z * stddev() / Math.sqrt(T);
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

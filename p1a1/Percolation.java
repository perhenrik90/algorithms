import java.lang.IllegalArgumentException;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {


    private WeightedQuickUnionUF uf; // Union find holder
    private int openSites = 0;
    private int [] sites;
    private int size = 0;
    private int dim = 0;
    private int virtual_top;
    private int virtual_bottom;

    private boolean percolates;
    
    public Percolation(int i){
	if(i <= 0){
	    throw new IllegalArgumentException("Percolation array can not be less than 1.");
	}
	size = i;
	dim = (int) Math.sqrt(size);
	virtual_top = i+1;
	virtual_bottom = i+2;
	
	uf = new WeightedQuickUnionUF(i+3);
	
	// Create sites
	sites = new int[i];
	for(int x = 0; x < i; x ++){

	    if(x < dim){
		System.out.println("Connecting virtual top");
		System.out.println(x);
		uf.union(virtual_top, x);
	    }
	    else if(x > size-dim-1){
		System.out.println("Connecting virtual bottom");
		System.out.println(x);
		uf.union(virtual_bottom, x);
	    }
	    sites[x] = 0;
	}

    }


    /******************
     * Open a site
     ******************/
    public void open(int row, int col){
	
	if(row*dim+col >= size){
	    throw new IllegalArgumentException("Trying to get a value outside the parculation array.");
	}
	
	if(col+1< dim)
	    uf.union(row*dim+col, row*(col+1));
	if(col-1>=0)
	    uf.union(row*dim+col, row*(col-1));
	if(row+1<dim)
	    uf.union(row*dim+col, (row+1)*col);
	if(row-1>=0)
	    uf.union(row*dim+col, (row-1)*col);
	
	sites[row*dim+col] = 1;
	openSites += 1;
	
	if(uf.connected(virtual_top, virtual_bottom)){
	    this.percolates = true;
	}
    }

    
    public boolean isOpen(int row, int col){
	if(row*dim+col >= size){
	    throw new IllegalArgumentException("Trying to get a value outside the parculation array.");
	}
	
	if(sites[row*dim+col] == 1){
	    return true;
	}
	return false;
    }

    public boolean isFull(int row, int col){
	if(row*dim+col >= size){
	    throw new IllegalArgumentException("Trying to get a value outside the parculation array.");
	}
	
	if(sites[row*dim+col] == 0){
	    return true;
	}
	return false;	
    }

    public int numberOfOpenSites(){
	return openSites;
    }

    public boolean percolates(){
	return percolates;
    }

    public static void main(String [] args){
	Percolation p = new Percolation(16);
	System.out.println(p.percolates());
	p.open(0,2);
	System.out.println(p.percolates());
	p.open(1,2);
	System.out.println(p.percolates());
	p.open(2,2);
	System.out.println(p.percolates());
	p.open(2,3);
	System.out.println(p.percolates());
	p.open(3,3);
	System.out.println(p.percolates());
    }
}

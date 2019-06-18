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

    private boolean percolates = false;
    
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
	    sites[x] = 0;
	    
	    if(x < dim){
		uf.union(virtual_top, x);
	    }
	    else if(x >= size-dim-1){
		uf.union(virtual_bottom, x);
	    }
	}

    }


    /******************
     * Open a site
     ******************/
    public void open(int row, int col){
	row = row -1 ;
	col = col -1 ;
	if(row*dim+col >= size){
	    throw new IllegalArgumentException("Trying to get a value outside the parculation array.");
	}
	if( isOpen(row+1,col+1)){
 	    return;
	}
       
	if(col+1< dim)
	    if(isOpen(row+1,col+2))	    
		uf.union(row*dim+col, row*(col+1));
	if(col-1>=0)
	    if(isOpen(row+1,col))	    
		uf.union(row*dim+col, row*(col-1));
	if(row+1<dim)
	    if(isOpen(row+2,col+1))
		uf.union(row*dim+col, (row+1)*col);
	if(row-1>=0)
	    if(isOpen(row,col+1))
		uf.union(row*dim+col, (row-1)*col);
	
	sites[row*dim+col] = 1;
	openSites += 1;
	
	if(uf.connected(virtual_top, virtual_bottom)){
	    this.percolates = true;
	}
    }

    
    public boolean isOpen(int row, int col){
	row = row -1 ;
	col = col -1 ;
	
	if(row*dim+col >= size){
	    throw new IllegalArgumentException("Trying to get a value outside the parculation array.");
	}

	if(sites[row*dim+col] == 1){
	    return true;
	}
	return false;
    }

    public boolean isFull(int row, int col){
	row = row -1 ;
	col = col -1 ;
	
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

    public static void main(String[] args){
	Percolation p = new Percolation(16);
	System.out.println(p.percolates());
	p.open(1,1);
	System.out.println(p.percolates());
	System.out.println(p.numberOfOpenSites());
	p.open(1,1);
	System.out.println(p.numberOfOpenSites());

	System.out.println(p.percolates());
	System.out.println(p.numberOfOpenSites());
	p.open(2,1);
	System.out.println(p.percolates());
	System.out.println(p.numberOfOpenSites());
	p.open(3,1);
	System.out.println(p.percolates());
	System.out.println(p.numberOfOpenSites());
	p.open(4,1);
	System.out.println(p.percolates());
	System.out.println(p.numberOfOpenSites());
    }
}

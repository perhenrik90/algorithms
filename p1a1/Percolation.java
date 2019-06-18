//import java.util.Scanner;
import java.lang.IllegalArgumentException;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {


    private final WeightedQuickUnionUF uf; // Union find holder
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
	size = i*i;
	dim = i;
	virtual_top = size+1;
	virtual_bottom = size+2;
	
	uf = new WeightedQuickUnionUF(size+3);
	
	// Create sites
	sites = new int[size];
	for(int x = 0; x < size; x ++){
	    sites[x] = 0;
	    
	    if(x < dim){
		uf.union(virtual_top, x);
	    }
	    else if(x >= size-dim){
		uf.union(virtual_bottom, x);
	    }
	}

    }


    /******************
     * Open a site
     ******************/
    public void open(int row, int col){
	int row_offset = row -1 ;
	int col_offset = col -1 ;
	
	if(row_offset*dim+col_offset >= size || row_offset<0 || col_offset<0){
	    throw new IllegalArgumentException("Trying to get a value outside the parculation array.");
	}
	if( isOpen(row,col)){
 	    return;
	}
       
	if(col_offset+1 < dim)
	    if(isOpen(row,col+1))	    
		uf.union(row_offset*dim+col_offset, row_offset*dim+(col_offset+1));
	if(col_offset-1>=0)
	    if(isOpen(row,col-1))	    
		uf.union(row_offset*dim+col_offset, row_offset*dim+(col_offset-1));
	if(row_offset+1<dim)
	    if(isOpen(row+1,col))
		uf.union(row_offset*dim+col_offset, (row_offset+1)*(dim)+col_offset);
	if(row_offset-1>=0)
	    if(isOpen(row-1,col))
		uf.union(row_offset*dim+col_offset, (row_offset-1)*(dim)+col_offset);
	
	sites[row_offset*dim+col_offset] = 1;
	openSites += 1;
	
	if(uf.connected(virtual_top, virtual_bottom)){
	    percolates = true;
	}
    }

    
    public boolean isOpen(int row, int col){
	int row_offset = row-1;
	int col_offset = col-1;
	if(row_offset*dim+col_offset >= size || row_offset<0 || col_offset<0){
	    throw new IllegalArgumentException("Trying to get a value outside the parculation array.");
	}

	if(sites[row_offset*dim+col_offset] == 1){
	    return true;
	}
	return false;
    }

    public boolean isFull(int row, int col){
	int row_offset = row -1 ;
	int col_offset = col -1 ;
	
	if(row_offset*dim+col_offset >= size || row_offset<0 || col_offset<0){
	    throw new IllegalArgumentException("Trying to get a value outside the parculation array.");
	}
	
	if(sites[row_offset*dim+col_offset] == 0){
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

    // public static void main(String[] args){

    // 	Scanner a = new Scanner(System.in);
    // 	int n = a.nextInt();
    // 	Percolation p = new Percolation(n);

    // 	while(a.hasNextInt()){
    // 	    int f = a.nextInt();
    // 	    int t = a.nextInt();
    // 	    System.out.println(f+" "+t);	    
    // 	    p.open(f,t);
    // 	    System.out.println(p.percolates());
    // 	}

    // } 
}

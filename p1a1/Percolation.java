//import java.util.Scanner;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    
    private final WeightedQuickUnionUF uf; // Union find holder
    private int openSites = 0;
    private final int [] sites;
    private final int size;
    private final int dim;
    private final int virtualTop;
    private final int virtualBottom;

    private boolean percolates = false;
    
    public Percolation(int i) {
	if(i <= 0){
	    throw new IllegalArgumentException("Percolation array can not be less than 1.");
	}
	size = i*i;
	dim = i;
	virtualTop = size+1;
	virtualBottom = size+2;
	
	uf = new WeightedQuickUnionUF(size+3);
	
	// Create sites
	sites = new int[size];
	for(int x = 0; x < size; x ++){
	    sites[x] = 0;
	    
	    if(x < dim){
		uf.union(virtualTop, x);
	    }
	    else if(x >= size-dim){
		uf.union(virtualBottom, x);
	    }
	}
    }

    /**************************************
     * Converts from to dimensions to one
     **************************************/
    private int toOneD(int row, int col){
	return (row*dim)+col;
    }

    /******************
     * Open a site
     ******************/
    public void open(int row, int col){
	int rowOffset = row -1 ;
	int colOffset = col -1 ;
	
	if(toOneD(rowOffset, colOffset)  >= size || rowOffset<0 || colOffset<0){
	    throw new IllegalArgumentException("Trying to get a value outside the parculation array.");
	}
	if(isOpen(row,col)){
 	    return;
	}
       
	if(colOffset+1 < dim)
	    if(isOpen(row,col+1))	    
		uf.union(toOneD(rowOffset, colOffset) , rowOffset*dim+(colOffset+1));
	if(colOffset-1 >= 0)
	    if(isOpen(row,col-1))	    
		uf.union(toOneD(rowOffset, colOffset) , rowOffset*dim+(colOffset-1));
	if(rowOffset+1 < dim)
	    if(isOpen(row+1,col))
		uf.union(toOneD(rowOffset, colOffset) , (rowOffset+1)*(dim)+colOffset);
	if(rowOffset-1 >= 0)
	    if(isOpen(row-1,col))
		uf.union(toOneD(rowOffset, colOffset) , (rowOffset-1)*(dim)+colOffset);
	
	sites[toOneD(rowOffset, colOffset) ] = 1;
	openSites += 1;
	
	if(uf.connected(virtualTop, virtualBottom)){
	    percolates = true;
	}
    }

    
    public boolean isOpen(int row, int col){
	int rowOffset = row-1;
	int colOffset = col-1;
	if(toOneD(rowOffset, colOffset)  >= size || rowOffset<0 || colOffset<0){
	    throw new IllegalArgumentException("Trying to get a value outside the parculation array.");
	}

	if(sites[toOneD(rowOffset, colOffset) ] == 1){
	    return true;
	}
	return false;
    }

    
    public boolean isFull(int row, int col){
	int rowOffset = row -1 ;
	int colOffset = col -1 ;
	
	if(toOneD(rowOffset, colOffset)  >= size || rowOffset<0 || colOffset<0){
	    throw new IllegalArgumentException("Trying to get a value outside the parculation array.");
	}

	if(isOpen(row,col)){
	    return uf.connected( virtualTop, toOneD(rowOffset, colOffset));
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

	Percolation p = new Percolation(5);

	System.out.println( p.isFull(1,1));
	p.open(1,1);
	System.out.println( p.isFull(1,1));
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

    } 
}

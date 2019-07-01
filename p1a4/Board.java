import java.util.Iterator;
import java.util.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Board {

    private int[][] board;
    private int N;
    
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles){
	board = tiles;
	N = tiles.length;
    }
                                           
    // string representation of this board
    public String toString(){
	String output = "";
	for(int[] row: board){
	    for(int e: row){
		output += e+" ";
	    }
	    output+="\n";
	}
	return output;
    }

    // board dimension n
    public int dimension(){
	return N;
    }

    // number of tiles out of place
    public int hamming(){
	int compare = 1;
	int missing = 0;
	
	for(int[] row : board){
	    for(int value: row){
		if(value != compare && compare <= (N*N)){
		    missing ++;
		}
		compare ++;
	    }
	}
	return missing;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){

	int compare = 1;
	int sum = 0;
	
	for(int[] row : board){
	    for(int value: row){
		if(value == compare){
		    sum += 0;
		}
		else{
		    if(value == 0){
			value = N*N;
		    }
		    sum += Math.abs(Math.abs(value / N)-Math.abs(compare / N) + Math.abs(value % N)-Math.abs(value % N));
		    // System.out.println("Sum: "+ sum);
		}
		compare ++;
	    }

	}
	return sum;
    }

    // is this board the goal board?
    public boolean isGoal(){

	int compare = 1;
	for(int[] row : board){
	    for(int value: row){
		System.out.println(compare+" "+value);
		if(value != compare && compare < N){
		    return false;
		}
		else if(value != 0 && compare == N*N){
		    return false;
		}
		compare ++;
	    }
	}
	return true;
    }

    // does this board equal y?
    public boolean equals(Object y){
	return true;
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
	return null;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin(){
	return null;
    }

    // unit testing (not graded)
    public static void main(String[] args){
	System.out.println("Starting board");

	// create initial board from file
	
	int n = StdIn.readInt();
	int[][] blocks = new int[n][n];
	for (int i = 0; i < n; i++)
	    for (int j = 0; j < n; j++)
		blocks[i][j] = StdIn.readInt();
	Board initial = new Board(blocks);
	System.out.println(initial.toString());
	System.out.println("dim: "+initial.dimension());
	System.out.println("goal: "+initial.isGoal());
	System.out.println("hamming: "+initial.hamming());
	System.out.println("man: "+initial.manhattan());	
    }

}

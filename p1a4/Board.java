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
	return 0;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
	return 0;
    }

    // is this board the goal board?
    public boolean isGoal(){
	return board[N-1][N-1] == 0;
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
    }

}

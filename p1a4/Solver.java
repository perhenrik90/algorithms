import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Solver {

    // Comparable

    private Move firstMove;
    
    private class Move implements Comparable<Move> {
        private Move prev_move = null;
        private Board current_board;
        private int countMoves = 0;

        public Move(Board board) {
            current_board = board;
        }

        public Move(Move previous, Board current) {
            prev_move = previous;
            current_board = current;
            countMoves = previous.countMoves + 1;
        }

        public int compareTo(Move that) {
            return this.current_board.manhattan() - that.current_board.manhattan()
		+ this.countMoves - that.countMoves;
        }
    }
    
    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial){
	
	// trow exception if argument is null
	if (initial == null)
	    throw new java.lang.NullPointerException();

	MinPQ<Move> moves = new MinPQ<Move>();
	firstMove = new Move(initial);
	moves.insert(firstMove);

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable(){
	return false;
    }

    // min number of moves to solve initial board
    public int moves(){
	return 0;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution(){
	return null;
    }

    // test client (see below) 
    public static void main(String[] args){
	// create initial board from file
	int n = StdIn.readInt();
	int[][] blocks = new int[n][n];
	for (int i = 0; i < n; i++)
	    for (int j = 0; j < n; j++)
		blocks[i][j] = StdIn.readInt();
	Board initial = new Board(blocks);

	// solve the puzzle
	Solver solver = new Solver(initial);

	// print solution to standard output
	if (!solver.isSolvable())
	    System.out.println("No solution possible");
	else {
	    System.out.println("Minimum number of moves = " + solver.moves());
	    for (Board board : solver.solution())
		System.out.println(board);
	}
    }

}

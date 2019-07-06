import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Collections;

public class Solver {

    // Comparable
    private Board init_board; 
    private Move firstMove;
    private Move lastMove;

    
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
	init_board = initial;

	
	// trow exception if argument is null
	if (initial == null)
	    throw new java.lang.NullPointerException();

	MinPQ<Move> moves = new MinPQ<Move>();
	firstMove = new Move(initial);
	moves.insert(firstMove);
	int i = 0;
	while(true){
	    lastMove = pickMove(moves);
	    if(lastMove != null && lastMove.current_board.isGoal()){
		return;
	    }
	i ++;
	if(i > initial.dimension()*initial.dimension()*initial.dimension()*1000) { lastMove = null; break;}
	}

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable(){
	if(lastMove != null){
	    return true;
	}
	return false;
    }

    private Move pickMove(MinPQ<Move> moves) {
        if (moves.isEmpty()){
	    return null;
	}
	
        Move bestMove = moves.delMin();
	
        if (bestMove.current_board.isGoal()){
	    return bestMove;
	}
	
        for (Board neighbor : bestMove.current_board.neighbors()) {

            if (bestMove.prev_move == null || !neighbor.equals(bestMove.prev_move.current_board)) {
		moves.insert(new Move(bestMove, neighbor));
	    }
            // if (bestMove.prev_move == null || !neighbor.equals(bestMove.prev_move.current_board)) {
            //     moves.insert(new Move(bestMove, neighbor));
            // }
        }
        return bestMove;
    }

    // min number of moves to solve initial board
    public int moves(){
	if(isSolvable()){
	    return lastMove.countMoves;
	}
	return -1;
	
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution(){
	if(!isSolvable()){
	    return null;
	}
	LinkedList<Board> path_list = new LinkedList<Board>();
	boolean checkMove = true;
	Move lm = lastMove;

	
	while(checkMove){

	    if(lm.prev_move == null){
		checkMove = false;
	    }
	    else{
		path_list.add(lm.current_board);
		lm = lm.prev_move;
	    }
	}

	path_list.add( init_board );
	Collections.reverse(path_list);
	return path_list;
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

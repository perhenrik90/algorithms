import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.LinkedList;
import java.util.Stack;

public class Solver {

    // Comparable
    private Board init_board; 
    private Move firstMove;
    private Move lastMove;
    private Stack<Board> old_boards;    
    
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
	old_boards = new Stack<Board>();
	
	// trow exception if argument is null
	if (initial == null)
	    throw new java.lang.NullPointerException();

	MinPQ<Move> moves = new MinPQ<Move>();
	firstMove = new Move(initial);
	moves.insert(firstMove);

	while(true){
	    lastMove = pickMove(moves);
	    old_boards.add(lastMove.current_board);
	    if(lastMove == null){
		return;
	    }

	    if(lastMove != null && lastMove.current_board.isGoal()){
		System.out.println(lastMove.current_board);
		return;
	    }

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
	System.out.println("S "+moves.size());
        if (moves.isEmpty()){
	    return null;
	}
	
        Move bestMove = moves.delMin();
	while( inOldBoards(bestMove.current_board)){

	    if(moves.isEmpty()){
		return null;
	    }
	    bestMove = moves.delMin();
	}
        if (bestMove.current_board.isGoal()) return bestMove;
	
        for (Board neighbor : bestMove.current_board.neighbors()) {

	    if( bestMove.prev_move != null || !neighbor.equals(bestMove.current_board) ){
		if( old_boards.search(neighbor) == -1){
		    moves.insert(new Move(bestMove, neighbor));

		}
	    }
            // if (bestMove.prev_move == null || !neighbor.equals(bestMove.prev_move.current_board)) {
            //     moves.insert(new Move(bestMove, neighbor));
            // }
        }
        return bestMove;
    }

    // min number of moves to solve initial board
    public int moves(){

	int moves = 0;
	boolean checkMove = true;
	Move lm = lastMove;

	while(checkMove){

	    if(lm == null){
		checkMove = false;
	    }
	    else{
		moves ++;
		lm = lm.prev_move;
	    }

	}
	return moves;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution(){

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
	return path_list;
    }

    private boolean inOldBoards(Board b2){
	for(Board b : old_boards){
	    if(b.equals(b2)){
		return true;
	    }
	}
	return false;
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

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class BruteCollinearPoints {


    private Point points [];
    
    private int n_segments = 0;
    private LineSegment segments [];
    
    public BruteCollinearPoints(Point[] points){
	points = points;

	for(int i = 0; i < points.length; i++){
	    Point first = points[i];
	    double s_compare = 0;
	    int i_compare = 1;
	    boolean colinear = true;
	    
	    for(int y = i+1; y < points.length; y ++){
		double s = first.slopeTo( points[y] );
		if(s_compare == 0){
		    s_compare = s;
		    i_compare ++;
		}
		else if(s_compare != s){
		    colinear = false;
		}
		
		if(i_compare == 4 && colinear){
		    System.out.println("bigno");
		}
	    }
	    System.out.println(i_compare);
	}
	System.out.println("Constructor finished");
    }// finds all line segments containing 4 points
    public           int numberOfSegments(){
	return 0;
    }// the number of line segments
    public LineSegment[] segments(){
	return null;
    }// the line segments


    public static void main(String [] args){

	int size = StdIn.readInt();
	Point [] ps = new Point[size];

	int i = 0;
	while(! StdIn.isEmpty()){

	    int x = StdIn.readInt();
	    int y = StdIn.readInt();
	    ps[i] = new Point(x,y);
	    i ++;
	}
	BruteCollinearPoints b = new BruteCollinearPoints(ps);
	
    }
}

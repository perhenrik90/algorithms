import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.LinkedList;

public class BruteCollinearPoints {


    private Point points [];
    
    private int n_segments = 0;

    
    private LinkedList<LineSegment> segments;
    
    public BruteCollinearPoints(Point[] points){
	points = points;
	segments = new LinkedList<LineSegment>();
	
	System.out.println(""+points.length);
	for(int a = 0; a < points.length; a ++){
	    for(int b = a+1; b < points.length; b ++){
		double segAB = points[a].slopeTo( points[b] );
		
		for(int c = b+1; c < points.length; c++){
		    double segBC = points[b].slopeTo( points[c]);
		    
		    for(int d = c+1; d < points.length; d++){
			double segCD = points[c].slopeTo( points[d]);

			if(segAB == segBC && segAB == segCD && segBC == segCD){
			    n_segments ++;
			    LineSegment sg = new LineSegment(points[a],points[d]);
			    segments.add(sg);
			}
			
		    }
		}
	    }
	}

    }

    public int numberOfSegments(){
	return segments.size();
    }
    public LineSegment[] segments(){
	LineSegment [] s = new LineSegment[segments.size()];
	return segments.toArray(s);
    }// the line segments


    public static void main(String [] args){

	int size = StdIn.readInt();
	Point [] ps = new Point[size];

	int i = 0;
	while(!StdIn.isEmpty()){

	    int x = StdIn.readInt();
	    int y = StdIn.readInt();
	    ps[i] = new Point(x,y);
	    i ++;

	}
	BruteCollinearPoints b = new BruteCollinearPoints(ps);

	System.out.println(b.numberOfSegments());
    }
}

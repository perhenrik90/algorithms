import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.LinkedList;
import java.util.Arrays;

public class BruteCollinearPoints {


    private final LinkedList<LineSegment> segments;
    
    public BruteCollinearPoints(Point[] points){
	checkNull(points);

	Arrays.sort(points);
	segments = new LinkedList<LineSegment>();
	
	for(int a = 0; a < points.length; a ++){
	    for(int b = a+1; b < points.length; b ++){
		double segAB = points[a].slopeTo( points[b] );
		
		for(int c = b+1; c < points.length; c++){
		    double segBC = points[b].slopeTo( points[c]);
		    
		    for(int d = c+1; d < points.length; d++){
			double segCD = points[c].slopeTo( points[d]);

			if(segAB == segBC && segAB == segCD && segBC == segCD){

			    LineSegment sg = new LineSegment(points[a],points[d]);
			    boolean add = true;
			    for(LineSegment x: segments){
				if(sg.toString().compareTo(x.toString())== 0){
				    add=false;
				    break;
				}
			    }
			    if(add){
				segments.add(sg);
			    }
			}
			
		    }
		}
	    }
	}

    }

    private void checkNull(Point[] points) {
        if (points == null) {
            throw new NullPointerException("The array \"Points\" is null.");
        }
        for (Point p : points) {
            if (p == null) {
                throw new NullPointerException("The array \"Points\" contains null element.");
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

	for(LineSegment ls: b.segments()){
	    System.out.println(ls.toString());
	}
	System.out.println("Segments: "+b.numberOfSegments());
    }
}

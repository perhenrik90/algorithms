import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.Arrays;
import java.util.LinkedList;

public class FastCollinearPoints {

    private final LinkedList<LineSegment> segments;
    
    public FastCollinearPoints(Point[] points){
	checkNull(points);
	checkDuplicates(points);
	segments = new LinkedList<LineSegment>();
	
	for(int i  = 0; i < points.length; i ++){

	    Point first = points[i];
	    int sim = 0;
	    double last_slope = Double.NEGATIVE_INFINITY;
	    Point last_point = null;
	    
	    Point [] ordered = Arrays.copyOfRange(points, i+1, points.length);
	    Arrays.sort(ordered);
	    Arrays.sort(ordered, first.slopeOrder());
	    // System.out.println("----");
	    for(Point q : ordered){
		// System.out.println( first.slopeTo(q));
		if(last_slope == first.slopeTo(q) || last_point == null){
		    sim += 1;
		    // System.out.println("nn: "+sim);
		    last_point = q;
		    last_slope = first.slopeTo(q);
		}
		else{
		    
		    if(sim >= 3){
			LineSegment sg = new LineSegment(first, last_point);
			segments.add(sg);
		    }
		    
		    sim = 1;
		    last_slope = first.slopeTo(q);
		    last_point = q;
		}
	    }
	    if(sim >= 3){
		LineSegment sg = new LineSegment(first, last_point);
		segments.add(sg);
	    }
	}
    }// finds all line segments containing 4 or more points
    public int numberOfSegments(){
	return segments.size();
	
    }// the number of line segments
    public LineSegment[] segments(){
	LineSegment [] s = new LineSegment[segments.size()];
	return segments.toArray(s);
    }// the line segments



    private void checkNull(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("The array \"Points\" is null.");
        }
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("The array \"Points\" contains null element.");
            }
        }
    }

    private void checkDuplicates(Point [] points){
	Point [] ordered = points.clone();
	Arrays.sort(ordered);

	Point last = null;
	for(Point p : ordered){

	    if(last != null){
		if(p.compareTo(last) == 0){
		    throw new IllegalArgumentException("The array \"Points\" contains duplicate elements.");
		}
		    
	    }
	    last = p;
	}
    }

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
	FastCollinearPoints fc = new FastCollinearPoints(ps);


	for(LineSegment ls: fc.segments()){
	    System.out.println(ls.toString());
	}
	System.out.println("Segments: "+fc.numberOfSegments());
	

    }
    
}

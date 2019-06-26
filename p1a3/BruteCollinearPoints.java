import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import java.util.LinkedList;
import java.util.Arrays;

public class BruteCollinearPoints {


    private final LinkedList<LineSegment> segments;
    
    public BruteCollinearPoints(Point[] points){
	checkNull(points);
	Point [] ordered = new Point[points.length];	
	System.arraycopy( points, 0, ordered, 0, points.length );

	checkDuplicates(ordered);
	Arrays.sort(ordered);
	segments = new LinkedList<LineSegment>();
	
	for(int a = 0; a < ordered.length; a ++){
	    for(int b = a+1; b < ordered.length; b ++){
		double segAB = ordered[a].slopeTo( ordered[b] );
		
		for(int c = b+1; c < ordered.length; c++){
		    double segBC = ordered[b].slopeTo( ordered[c]);
		    
		    for(int d = c+1; d < ordered.length; d++){
			double segCD = ordered[c].slopeTo( ordered[d]);

			if(segAB == segBC && segAB == segCD && segBC == segCD){

			    LineSegment sg = new LineSegment(ordered[a],ordered[d]);
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

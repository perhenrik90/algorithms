import java.util.Arrays;

public class FastCollinearPoints {

    private Point points [];

    public FastCollinearPoints(Point[] points){
	points = points;

	for(int i  = 0; i < points.length; i ++){
	    Point [] ordered = points.clone();

	    Arrays.sort(ordered, Point.slopeOrder());
	    
	}
    }// finds all line segments containing 4 or more points
    public int numberOfSegments(){
	return 0;
    }// the number of line segments
    public LineSegment[] segments(){
	return new LineSegment[1];
    }// the line segments



    public static void main(String [] args){
	System.out.println("Hello");
    }
    
}

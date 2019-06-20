
public class BruteCollinearPoints {


    private Point points [];
    private boolean colinear = true;
    
    public BruteCollinearPoints(Point[] points){
	points = points;

	Point first = points[0];
	double s_compare = 0;
	for(int i = 1; i < points.length; i+=1){
	    
	    double s = first.slopeTo( points[i] );
	    if(s_compare == 0){
		s_compare = s;
	    }
	    else if(s_compare != s){
		colinear = false;
	    }
	}
	System.out.println(colinear);
    }// finds all line segments containing 4 points
    public           int numberOfSegments(){
	return 0;
    }// the number of line segments
    public LineSegment[] segments(){
	return null;
    }// the line segments


    public static void main(String [] args){

	Point [] ps = new Point[8];
	
	for(int i = 0; i < 8; i ++){
	    ps[i] = new Point(i,i+2);
	}
	BruteCollinearPoints b = new BruteCollinearPoints(ps);
	
    }
}

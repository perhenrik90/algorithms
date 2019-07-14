import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {

    private final SET<Point2D> set;
    
    public PointSET(){
	set = new SET<Point2D>();
    }// construct an empty set of points 

    public boolean isEmpty(){
	return set.isEmpty();
    }// is the set empty? 

    public int size(){
	return set.size();
    }

    public void insert(Point2D p){
	set.add(p);
    }

    public boolean contains(Point2D p){
	return set.contains(p);
    }

    public void draw(){
	for(Point2D p: set){
	    StdDraw.point(p.x(),p.y());
	}
    }

    public Iterable<Point2D> range(RectHV rect){

	if(rect == null){
	    throw new IllegalArgumentException("Rect can not be null");
	}
	SET<Point2D> range_set = new SET<Point2D>();

	for(Point2D p : set){
	    if(rect.contains(p)){
		range_set.add(p);
	    }
	}
	return range_set;
    }// all points that are inside the rectangle (or on the boundary)
    
    public Point2D nearest(Point2D p){
	if(p == null){
	    throw new IllegalArgumentException("Point2D can not be null");
	}
	Point2D nearestPoint = null;
        double minDist = Double.MAX_VALUE;
        for (Point2D point : set) {

            double distance = point.distanceSquaredTo(p);

	    if (distance < minDist) {
                minDist = distance;
		nearestPoint = point;
            }
        }
	return nearestPoint;

    }// a nearest neighbor in the set to point p; null if the set is empty 

    public static void main(String[] args){
	System.out.println("Testing");

	PointSET ps = new PointSET();

	ps.insert( new Point2D(4,5));
	ps.insert( new Point2D(4,7));
	ps.draw();
    }
}

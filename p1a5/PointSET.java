import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {

    private SET<Point2D> set;
    
    public         PointSET(){

    }// construct an empty set of points 
    public           boolean isEmpty(){
	return false;
    }// is the set empty? 
    public               int size(){
	return 1;
    }
    public void insert(Point2D p){
	
    }
    public boolean contains(Point2D p){
	return false;
    }
    public void draw(){
	
    }
    public Iterable<Point2D> range(RectHV rect){
	return null;
    }// all points that are inside the rectangle (or on the boundary) 
    public Point2D nearest(Point2D p){
	return null;
    }// a nearest neighbor in the set to point p; null if the set is empty 

    public static void main(String[] args){
	System.out.println("Testing");
    }// unit testing of the methods (optional) 
}

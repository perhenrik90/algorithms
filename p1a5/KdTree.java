import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree{

    private class KdNode{
	private Point2D point;
	private boolean vertical;
	private KdNode l;
	private KdNode r;
	
	KdNode(Point2D p, boolean vertical){
	    point = p;
	    vertical = vertical;
	}

    }

    private KdNode root;
    private int size = 0;
    
    public KdTree(){
	root = null;
    }


    public boolean isEmpty(){
	return root == null;
    }// is the set empty? 

    public int size(){
	return size;
    }

    public void insert(Point2D p){
	if(root == null){
	    root = new KdNode(p, true);
	}
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
}

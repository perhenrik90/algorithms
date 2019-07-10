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

	public boolean insert(Point2D child){

	    if(child.x() == point.x() && child.y() == point.y()){
		return false;
	    }

	    // if vertical 
	    if(vertical == true){
		if(child.x() <= child.x()){
		    if(l == null){
			l = new KdNode(child, false);
			return true;
		    }
		    else{
			l.insert(child);
		    }
		}
		else{
		    if(r == null){
			r = new KdNode(child, false);
			return true;
		    }
		    else{
			r.insert(child);
		    }
		}
	    }
	    // if hor
	    else{
		if(child.y() <= child.y()){
		    if(l == null){
			l = new KdNode(child, true);
			return true;
		    }
		    else{
			l.insert(child);
		    }
		}
		else{
		    if(r == null){
			r = new KdNode(child, true);
			return true;
		    }
		    else{
			r.insert(child);
		    }
		}
	    }
	    
	    return false;
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
	    size ++;
	}
	else{
	    if(root.insert(p)){
		size += 1;
	    }
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


    public static void main(String [] args){
	KdTree t = new KdTree();

	t.insert( new Point2D(4,4));
	t.insert( new Point2D(3,8));
	System.out.println("Size: "+t.size());
    }
}

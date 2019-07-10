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
	    System.out.println(child);
	    if(child.equals(point)){
		return false;
	    }

	    // if vertical 
	    if(vertical == true){
		if(child.x() <= point.x()){
		    if(l == null){
			l = new KdNode(child, false);
			return true;
		    }
		    else{
			return l.insert(child);
		    }
		}
		else{
		    if(r == null){
			r = new KdNode(child, false);
			return true;
		    }
		    else{
			return r.insert(child);
		    }
		}
	    }
	    // if hor
	    else{
		if(child.y() <= point.y()){
		    if(l == null){
			l = new KdNode(child, true);
			return true;
		    }
		    else{
			return l.insert(child);
		    }
		}
		else{
		    if(r == null){
			r = new KdNode(child, true);
			return true;
		    }
		    else{
			return r.insert(child);
		    }
		}
	    }
	    

	}

	public boolean contians(Point2D search_point, boolean vertical){
	    
	    if(search_point.equals(point)){
		return true;
	    }

	    // if vertical 
	    if(vertical == true){
		if(search_point.x() <= point.x()){
		    if(l == null){
			return false;
		    }
		    else{
			return l.contians(search_point, false);
		    }
		}
		else{
		    if(r == null){
			return false;
		    }
		    else{
			return r.contians(search_point, false);
		    }
		}
	    }
	    // if hor
	    else{
		if(search_point.y() <= point.y()){
		    if(l == null){
			return false;
		    }
		    else{
			return l.contians(search_point, true);
		    }
		}
		else{
		    if(r == null){
			return false;
		    }
		    else{
			return r.contians(search_point, true);
		    }
		}
	    }
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
	if(root == null){
	    return false;
	}
	return root.contians(p, true);
    }

    public void draw(){
	// for(Point2D p: set){
	//     StdDraw.point(p.x(),p.y());
	// }	
    }

    public Iterable<Point2D> range(RectHV rect){
	if(rect == null){
	    throw new IllegalArgumentException("Rect can not be null");
	}
	return null;
    }// all points that are inside the rectangle (or on the boundary)
    
    public Point2D nearest(Point2D p){
	if(p == null){
	    throw new IllegalArgumentException("Point2D can not be null");
	}
	return null;

    }// a nearest neighbor in the set to point p; null if the set is empty


    public static void main(String [] args){
	KdTree t = new KdTree();

	t.insert( new Point2D(0.7,0.2));
	t.insert( new Point2D(0.5,0.4));
	t.insert( new Point2D(0.2,0.3));
	System.out.println("Size: "+t.size());

	System.out.println(t.contains( new Point2D(8,9)));
	System.out.println(t.contains( new Point2D(0.2,0.3)));

	
    }
}

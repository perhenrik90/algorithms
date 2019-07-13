import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree{

    private class KdNode{
	private final Point2D point;
	private final boolean horizontal;
	private KdNode l;
	private KdNode r;
	
	KdNode(Point2D p, boolean h){
	    point = p;
	    horizontal = h;
	}

	public void print(KdNode parrent, int i){
	    if(parrent != null){
		System.out.println(parrent.point+" | "+point+" : "+i+" hor: "+horizontal);
	    }

	    if(l != null){ l.print(this, i+1);}
	    if(r != null){ r.print(this, i+1);}
	}

	public void draw(){
	    StdDraw.point(point.x(),point.y());
	    if(l != null){
		l.draw();
	    }
	    if(r != null){
		r.draw();
	    }
	}
	
	public boolean insert(Point2D child){
	    
	    //System.out.println("In: "+point+" | "+child);
	    if(child.equals(point)){
		return false;
	    }

	    // if horizontal 
	    if(horizontal == true){
		if(child.x() < point.x()){
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
	    // if vertical
	    else{
		if(child.y() < point.y()){
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

	public boolean contains(Point2D search_point, boolean horizontal_func){
	    //System.out.println("S: "+point+" | "+search_point);	    
	    if(search_point.equals(point)){
		return true;
	    }

	    // if horizontal 
	    if(horizontal_func == true){
		if(search_point.x() < point.x()){
		    if(l == null){
			return false;
		    }
		    else{
			return l.contains(search_point, false);
		    }
		}
		else{
		    if(r == null){
			return false;
		    }
		    else{
			return r.contains(search_point, false);
		    }
		}
	    }
	    // if hor
	    else{
		if(search_point.y() < point.y()){
		    if(l == null){
			return false;
		    }
		    else{
			return l.contains(search_point, true);
		    }
		}
		else{
		    if(r == null){
			return false;
		    }
		    else{
			return r.contains(search_point, true);
		    }
		}
	    }


	}

	public Point2D nearest(Point2D search_node, boolean horizontal_func, KdNode nearest){
	    //System.out.print(point);
	    if(search_node.equals(point)){
		return point;
	    }

	    // if horizontal 
	    if(horizontal_func == true){
		if(search_node.x() < point.x()){

		    if(l == null){
			return nearest.point;
		    }
		    else{

			return l.nearest(search_node, false, l);
		    }
		}
		else{

		    if(r == null){
			return nearest.point;
		    }
		    else{
			return r.nearest(search_node, false, l);
		    }
		}
	    }
	    // if vertical
	    else{
		if(search_node.y() < point.y()){
		    if(l == null){
			return nearest.point;
		    }
		    else{
			return l.nearest(search_node, true, r);
		    }
		}
		else{

		    if(r == null){
			return nearest.point;
		    }
		    else{
			return r.nearest(search_node, true, r);
		    }
		}
	    }
	}

	// find rectangles in one node
	public SET<Point2D> range(RectHV rect, boolean horizontal_func){

	    SET<Point2D> set = new SET<Point2D>();

	    if(rect.contains(point)){
		set.add(point);
	    }
	    
	    // if horizontal 
	    if(horizontal_func == true){
		if(rect.xmin() <= point.x()){
		    if(l != null){
			set = set.union( l.range(rect, false ));
		    }
		}
		
		if(rect.xmax() >= point.x()){
		    if(r != null){
			set = set.union(r.range(rect, false));
		    }
		}
	    }
	    // if vertical
	    else {
		if(rect.ymin() <= point.y()){
		    if(l != null){
			set = set.union( l.range(rect, true ));
		    }
		}

		if(rect.ymax() >= point.y()){
		    if(r != null){
			set = set.union(r.range(rect, true));
		    }
		}
	    }
	    return set;
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

	if(p == null){
	    throw new IllegalArgumentException("P can not be null");
	}
	
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

	if(p == null){
	    throw new IllegalArgumentException("P can not be null");
	}

	if(root == null){
	    return false;
	}
	return root.contains(p, true);
    }

   public void draw(){
       //root.print(null, 0);
       root.draw();
       //root.print(root, 1);
	// for(Point2D p: set){
	//     StdDraw.point(p.x(),p.y());
	// }	
    }

    public Iterable<Point2D> range(RectHV rect){
	if(rect == null){
	    throw new IllegalArgumentException("Rect can not be null");
	}
	if(root == null){
	    return new SET<Point2D>();
	}
	return root.range(rect, true);
	
    }// all points that are inside the rectangle (or on the boundary)
    
    public Point2D nearest(Point2D p){
	if(p == null){
	    throw new IllegalArgumentException("Point2D can not be null");
	}

	if(root == null){
	    return null;
	}

	if(root.equals(p)){
	    return root.point;
	}

	return root.nearest(p ,true, root);

    }// a nearest neighbor in the set to point p; null if the set is empty


    public static void main(String [] args){
	KdTree t = new KdTree();
	t.insert( new Point2D(0.7,0.2));
	t.insert( new Point2D(0.5,0.4));
	t.insert( new Point2D(0.2,0.3));
	t.insert( new Point2D(0.4,0.7));
	t.insert( new Point2D(0.9,0.6));
	System.out.println("Size: "+t.size());

	System.out.println(t.contains( new Point2D(0.4,0.7)));
	System.out.println(t.contains( new Point2D(0.2, 0.3)));
	System.out.println(t.contains( new Point2D(0.5, 0.4)));
	
	System.out.println(t.nearest( new Point2D(0.2,0.4)));

	RectHV hv = new RectHV(0.1,0.1,0.6,0.6);
	for(Point2D p: t.range(hv)){
	    System.out.println("Subset of "+p);
	}
	
	t.draw();
    }
}

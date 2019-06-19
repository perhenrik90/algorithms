import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item>{

    private class Node{
	Item value;
	Node next = null;
	Node prev = null;

	Node(Item item){
	    value = item;
	}

	void setNext(Node item){
	    next = item;
	}
	void setPrev(Node item){
	    prev = item;
	}
	Item getItem(){
	    return value;
	}
    }

    private class DequeIterator implements Iterator<Item>{
	Node current;
	
	public boolean hasNext(){
	    if(current != null){
	    	return true;		
	    }
	    return false;
	}
	
	public Item next(){
	    Node c = current;

	    if(c == null){
		throw new NoSuchElementException("Next element is empty..");
	    }
	    current = c.next;
	    return c.getItem();
	}

	public void remove(){
	    throw new UnsupportedOperationException("Remove is unsuported");
	}

	public void setCurrent(Node c){
	    current = c;
	}
    }
    

    private Node first = null;
    private Node last = null;
    private int n  = 0;
    

    public boolean isEmpty(){
	if(first == null && last == null){
	    return true;
	}
	return false;
    }
    public int size(){
	return n;
    }

    public void addFirst(Item item){

	checkItem(item);
	if(first == null){
	    Node node = new Node(item);
	    first = node;

	    if(last == null){
		last = node;
	    }
	    n += 1;
	    return;
	}
	Node new_first = new Node(item);
	Node old_first = first;
	new_first.setNext(old_first);
	old_first.setPrev(new_first);
	first = new_first;
	n += 1;
    }

    public Item removeFirst(){
	if(first == null  || n <= 0){
	    throw new NoSuchElementException("Que is empty.");
	}
	Node out_first = first;

	if(out_first.next != null){
	    first = out_first.next;
	    first.prev = null;
	}
	else{
	    first = null;
	}
	n -= 1;
	return out_first.getItem();
    }
    public void addLast(Item item){
	checkItem(item);
	
	if(last == null){
	    Node node = new Node(item);
	    last = node;
	    
	    if(first == null){
		first = node;
	    }
	    n += 1;
	    return;
	}
	
	Node new_last = new Node(item);
	Node old_last = last;
	old_last.setNext(new_last);
	new_last.setPrev(old_last);
	last = new_last;
	n += 1;
    }

    public Item removeLast(){
	if(last == null || n <= 0){
	    throw new NoSuchElementException("Que is empty.");
	}
	n -= 1;
	Node out_last = last;
	if(out_last.prev != null){
	    last = out_last.prev;
	    last.next = null;
	}
	last = null;
	return out_last.getItem();
    }
    
    public Iterator<Item> iterator(){
    	DequeIterator di = new DequeIterator();
	di.setCurrent( first );
    	return di;
    }

    private boolean checkItem(Item item){
	if(item == null){
	    throw new IllegalArgumentException("Can not add a null element");
	}
	return true;
    }


    public static void main(String [] args){

	Deque<Integer> q = new Deque<Integer>();
	q.addLast(1);
	q.addLast(1);
	q.addLast(2);
	q.addLast(3);
	// q.addLast(4);
	// q.addFirst(6);
	// q.addFirst(null);
	q.removeFirst();
	q.removeFirst();

	for(int i : q){
	    System.out.println( i);
	}
	
	System.out.println(q.size());

    }

}







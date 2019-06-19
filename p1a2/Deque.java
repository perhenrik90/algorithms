import java.util.Iterator;




public class Deque<Item> implements Iterable<Item>{

    class Node{
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
	    current = c.next;
	    return c.getItem();
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
	return 0;
    }

    public void addFirst(Item item){
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
    public void addLast(Item item){
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
    public Iterator<Item> iterator(){
    	DequeIterator di = new DequeIterator();
	di.setCurrent( first );
    	return di;
    }



    public static void main(String [] args){

	Deque<Integer> q = new Deque<Integer>();
	q.addLast(1);
	q.addLast(2);
	q.addLast(3);
	q.addLast(4);


	Iterator x = q.iterator();

	for(int i : q){
	    System.out.println( i);
	}

    }

}







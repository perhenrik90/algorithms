import java.util.*;




public class Deque<Item> {

    class Node{
	Item item;
	Node next;
	Node prev;

	Node(Item item){
	    item = item;
	}

	void setNext(Node item){
	    
	    next = item;
	}
	void setPrev(Node item){
	    prev = item;
	}

    }
    

    private Node first;
    private Node last;
    private int n  = 0;
    
    public Deque(){

    }

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
	if(first == null && last == null){
	    Node node = new Node(item);
	    first = node;

	    n += 1;
	    return;
	}
	Node new_first = new Node(item);
	Node old_first = first;
	new_first.setNext(old_first);
	first = new_first;
	
    }
    public void addLast(Item item){

    }
    // public Iterator<Item> iterator(){
    // 	DequeIterator di = new DequeIterator();
    // 	return di;
    // }
    

}



// class DequeIterator implements Iterator<Item>{


//     public Item next(){
	
//     }
    
//     public boolean hasNext(){
// 	return true;
//     }
//     public Item next(){
// 	return null;
//     }

// }


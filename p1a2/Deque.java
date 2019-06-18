

public class Deque<Item> implements Iterable<Item>{

    public Deque(){
	
    }

    public boolean isEmpty(){
	return true;
    }
    public int size(){
	return 0;
    }

    private class DequeIterator implements Iterator<Item>{
	public boolean hasNext(){
	    return true;
	}
	public Item next(){
	    
	}
    }
}



//import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;


public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item [] items;
    private int array_size = 1;
    private int n = 0;

    private class RandomizedIterator implements Iterator<Item>{

	int index = 0;
	
	public RandomizedIterator(){
	    Random r = new Random();
	    // shuffle
	    for(int i = 0; i < n; i ++){
		int ri = r.nextInt(n);
		Item ritem = items[ri];
		items[ri] = items[i];
		items[i] = ritem;
	    }
	}

	public boolean hasNext(){
	    if(index < n)
		return true;
	    return false;
	}
	public Item next(){
	    Item item = items[index];
	    index += 1;
	    return item;
	}
	public void remove(){
	    throw new UnsupportedOperationException("Remove is unsuported");
	}
    }
    
    // construct an empty randomized queue
    public RandomizedQueue(){
        items = (Item[]) new Object[array_size];
    }
    
    // is the randomized queue empty?
    public boolean isEmpty(){
	if(n == 0){
	    return true;
	}
	return false;
    }
    // return the number of items on the randomized queue
    public int size(){
	return n;
    }

    private void resizeArray(){
	int new_size = array_size*2;
	Item [] new_items = (Item []) new Object[new_size];
	
	for(int i = 0; i < n; i ++){
	    new_items[i] = items[i];
	}
	items = new_items;
	array_size = new_size;
    }
    
    // add the item
    public void enqueue(Item item){
	
	if(item == null){
	    throw new IllegalArgumentException("Can not add a null element");
	}
	
	if(n >= array_size){
	    resizeArray();
	}

	if(items[n] == null){
	    items[n] = item;
	    n ++;
	}
    }

    // remove and return a random item    
    public Item dequeue(){
	
	if(n <= 0){
	    throw new NoSuchElementException("Next element is empty.");
	}

	Random r = new Random();
	int iw = r.nextInt(n);

	Item out = items[iw];
	items[iw] = items[n-1];
	items[n-1] = null;
	
	n --;
	return out;
    }
    
    // return a random item (but do not remove it)
    public Item sample(){

	if(n <= 0){
	    throw new NoSuchElementException("Next element is empty.");
	}
	
	Random r = new Random();
	return items[r.nextInt(n)];
    }
    public Iterator<Item> iterator(){
	return new RandomizedIterator();
    }

    
    public static void main(String[] args){
	RandomizedQueue<Integer> r = new RandomizedQueue<Integer>();

	r.enqueue(4);
	r.enqueue(5);
	r.enqueue(5);
	r.enqueue(1);

	for(int e : r){
	    System.out.println(e);
	}
	System.out.println("-------");
	for(int e : r){
	    System.out.println(e);
	}
   }// unit testing (optional)
}

import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class Permutation{

    public static void main(String [] args){
	RandomizedQueue<String> q = new RandomizedQueue<String>();
	while (!StdIn.isEmpty()) {
	    String value = StdIn.readString();
	    q.enqueue(value);
	}

	int k = Integer.parseInt(args[0]);
	Iterator<String> it = q.iterator();
	for(int i = 0; i < k; i++){
	    StdOut.println(it.next());
	}
    }
}

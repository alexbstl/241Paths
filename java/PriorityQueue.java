import java.util.ArrayList;

//
// PRIORITYQUEUE.JAVA
// A priority queue class supporting sundry operations needed for
// Dijkstra's algorithm.
//

class PriorityQueue<T> {

	// constructor
	ArrayList<Handle<T>> Queue;
	public PriorityQueue()
	{
		Queue = new ArrayList<Handle<T>>();
		Queue.add(0, null);
	}

	/**
	 * Returns true if the size of Queue is less than 2 or the 2nd item in the queue is null
	 * @return
	 */
	public boolean isEmpty()
	{
		if((Queue.size()<2)||(Queue.get(1) == null)){
			return true;
		}
		return false;
	}

	// Insert a pair (key, value) into the queue, and return
	// a Handle to this pair so that we can find it later in
	// constant time.
	//
	/**
	 * Insert a key,value (object) pair into the Priority Queue, and restores the heap properties
	 * @param key
	 * @param value
	 * @return Handle containing key and value
	 */
	Handle<T> insert(int key, T value)
	{

		Handle<T> insert = new Handle<T>(key,value);
		Queue.add(insert);
		int i = Queue.size()-1;
		while((i>1)&&(key < Queue.get(i/2).getKey())){
			swap(i,i/2);
			i/=2;
		}
		Queue.set(i, insert);
		insert.setIndex(i);
		return insert;
	}

	/**
	 * Returns the smallest key in the queue, which is at the top of the heap
	 * @return int value for smallest key in the queue
	 */
	public int min()
	{
		return Queue.get(1).getKey();
	}

	/**
	 * Extracts the first element in the queue and restores the heap property.
	 * @return Object held by first Handle in the queue
	 */
	public T extractMin()
	{
		Handle<T> remove = Queue.get(1);
		Queue.set(1, Queue.get(Queue.size()-1));
		Queue.get(1).setIndex(1);
		Queue.remove(Queue.size()-1);
		Heapify(1);
		remove.inQueue=false;
		return remove.getObject();
	}


	// Look at the (key, value) pair referenced by Handle h.
	// If that pair is no longer in the queue, or its key
	// is <= newkey, do nothing and return false.  Otherwise,
	// replace "key" by "newkey", fixup the queue, and return
	// true.
	//
	/**
	 *  Checks if the Handle's associated key is less than the newkey provided.  If it is not, it sets the handle's key to newkey and restores the heap property
	 *  for the queue.
	 * @param h handle to check
	 * @param newkey to compare h's key to
	 * @return true if newkey<h's key; false otherwise
	 */
	public boolean decreaseKey(Handle<T> h, int newkey)
	{
		if(h.inQueue){
			if(h.getKey()<= newkey){
				return false;
			}
			h.key=newkey;
			int i = h.index;
			while((i>1)&&(h.key < Queue.get(i/2).getKey())){
				swap(i,i/2);
				i/=2;
			}
			h.setIndex(i);
			return true;
		}
		return false;
	}
	/**
	 * Method for swapping two Handles in the queue
	 * @param a- integer location to swap
	 * @param b- integer location to swap
	 */
	private void swap(int a, int b){
		Handle<T> swap = Queue.get(a);
		Handle<T> current = Queue.get(b);
		Queue.set(a, current);
		current.setIndex(a);
		Queue.set(b, swap);
		swap.setIndex(b);
	}

	/**
	 * given a handle, returns the key associated with that handle
	 * @param h- handle
	 * @return int key
	 */
	public int handleGetKey(Handle<T> h)
	{
		return h.getKey();
	}

	/**
	 * Given a handle, return the object associated with that handle
	 * @param h - handle
	 * @return T object
	 */
	public T handleGetValue(Handle<T> h)
	{

		return h.getObject();
	}

	/**
	 * Restores the Heap property recursively beginning at a given index
	 * @param index
	 */
	public void Heapify(int index){
		if(Queue.size()>2*index+1){
			int j;
			if(Queue.get(2*index+1).getKey()>Queue.get(2*index).getKey()){
				j=(2*index);
			}else{
				j=(2*index)+1;
			}

			if(Queue.get(index).getKey()>Queue.get(j).getKey()){
				swap(index,j);
				Heapify(j);
			}
			return;
		}else if(Queue.size()==2*index+1){
			int j = 2*index;
			if(Queue.get(index).getKey()>Queue.get(j).getKey()){
				swap(index,j);
			}
		}
		return;
	}
	// Print every element of the queue in the order in which it appears
	// in the implementation (i.e. the array representing the heap).
	public String toString()
	{
		String a ="";
		for(int i=1;i<Queue.size();++i){
			a+=Queue.get(i).toString()+"\n";
		}
		return a;
	}
}

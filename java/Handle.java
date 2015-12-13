/**
 * Handles are objects held by a Priority Queue that contain references to an object and also the key for that object
 * @author alex
 *
 * @param <T>
 */
public class Handle<T> {

	int key;
	T object;
	int index;
	boolean inQueue=false;
	
	public Handle(int k, T object){
		this.key=k;
		this.object=object;
	}
	/**
	 * returns the key
	 * @return int key
	 */
	public int getKey(){
		return key;
	}
	/**
	 * Returns the object
	 * @return T object
	 */
	public T getObject(){
		return object;
	}
	/**
	 * sets the index value.  Index should be index in PriorityQueue's arrayList
	 * @param k
	 */
	public void setIndex(int k){
		inQueue=true;
		this.index=k;
	}
	public String toString(){
		return "("+this.getKey()+", "+object.toString()+")";
	}
}

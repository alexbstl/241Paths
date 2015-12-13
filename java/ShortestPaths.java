import java.util.ArrayList;




//
// SHORTESTPATHS.JAVA
// Compute shortest paths in a graph.
//
// Your constructor should compute the actual shortest paths and
// maintain all the information needed to reconstruct them.  The
// returnPath() function should use this information to return the
// appropriate path of edge ID's from the start to the given end.
//
// Note that the start and end ID's should be mapped to vertices using
// the graph's get() function.
//
// You can ignore the input and startTime arguments to the constructor
// unless you are doing the extra credit.
//
class ShortestPaths {
	/**
	 * Constructs the ShortestPath by creating a PriorityQueue and implementing Djikstra's Algorithm.  
	 * Pass 0 into startTime for schedule-less implementation.
	 */
	public Vertex start;
	private ArrayList<Vertex> airports;
	public ShortestPaths(Multigraph G, int startId,
			Input input, int startTime)
	{

		this.start=G.get(startId);
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		airports = new ArrayList<Vertex>();

		for(int i = 0; i<G.nVertices();++i){
			airports.add(G.get(i));
		}
		for(int i = 0; i<G.nVertices();++i){
			if(G.get(i).id()!=startId){
				Vertex insert = G.get(i);
				insert.setHandle(queue.insert(Integer.MAX_VALUE, insert));
			}
		}
		G.get(startId).setHandle(queue.insert(0, G.get(startId)));
		G.get(startId).parentEdge=null;
		int arrivaltime=startTime;
		int layover=0;
		int flightStart;
		while(!(queue.isEmpty())){
			if(queue.min()==Integer.MAX_VALUE){
				break;
			}
			Vertex u = queue.extractMin();
			Vertex.EdgeIterator ei = u.adj();
			if(u.parentEdge!= null){
				arrivaltime = input.flights[u.parentEdge.id()].endTime;
			}
			while(ei.hasNext()){
				Edge e = ei.next();
				Vertex target = e.to();
				flightStart = input.flights[e.id()].startTime;
				if(startTime!=0){
					layover = flightStart - arrivaltime;
					if(layover<45){
						layover +=1440;
					}
				}
				if(queue.decreaseKey(target.getHandle(), (u.getHandle().getKey()+e.weight()+layover))){
					target.parentEdge=e;
				}

			}
		}
	}


	/**
	 * Returns the shortest path from finish to start by retracing the parent edges from the end vertex
	 * @param endId
	 * @return Array of ints containing IDs of the edges of the shortest path
	 */
	public int [] returnPath(int endId)
	{

		Vertex current = airports.get(endId);
		ArrayList<Edge> path = new ArrayList<Edge>();
		while(current.parentEdge!=null){
			path.add(current.parentEdge);
			current=current.parentEdge.from();
		}
		int[] returnPath = new int[path.size()];
		for(int a=0; a<path.size(); a++){
			returnPath[path.size()-a-1] = path.get(a).id();
		}

		return returnPath;
	}
}


public class Port {
	
	private String name;		// The name of the port
	private int refuel_time;	// The refuel time of the port
	private int index;			// The index of the port corresponding to the adjacency matrix 
								// for its map
	
	public Port(String n, int t, int i) {
		
		name = n;
		refuel_time = t;
		index = i;
	}
	
	public String getName() { return name;}
	public int getTime() { return refuel_time;}
	public int getIndex() { return index;}
}

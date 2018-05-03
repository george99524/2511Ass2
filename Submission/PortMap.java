import java.util.ArrayList;

public class PortMap {
	
	private ArrayList<Port> port_list;		// A list of all ports(vertices) contained in the graph
	private int[][] edges;					// An adjacency matrix for the edges between all ports
	private int size;						// The number of ports in the graph
	
	public PortMap() {
		
		port_list = new ArrayList<Port>();
		edges = null;
		size = 0;
	}
	
	/**
	 * The method to add a port to the map
	 * @param n		The name of the port
	 * @param t		The refuel time of the port
	 */
	public void addPort(String n, int t) {
		
		Port p = new Port(n, t, size);
		port_list.add(p);
		size += 1;
	}
	
	/**
	 * Initiate the adjacency matrix
	 */
	public void createMatrix() {
		
		edges = new int[size][size];
	}
	
	/**
	 * The method to add a weighted edge between ports A and B
	 * @param a		The name of port A
	 * @param b		The name of port B
	 * @param t		The time taken(weight) to travel between the ports
	 */
	public void addEdge(String a, String b, int t) {
		
		if (edges==null) { createMatrix(); }
		
		int n = 0;
		int x = 0, y = 0;
		for (Port p : port_list) {
			if (p.getName().equals(a)) { x = n; }
			if (p.getName().equals(b)) { y = n; }
			n += 1;
		}
		
		edges[x][y] = t;
		edges[y][x] = t;
	}
	
	/**
	 * The method to print the adjacency matrix
	 */
	public void print() {
		
		for (int[] a : edges) {
			for (int b : a) {
				System.out.print(b + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	/**
	 * The method to get the object instance of a port when provided with the name
	 * @param name		The name of the port
	 * @return			returns the object instance of the port
	 */
	public Port getPort(String name) {
		
		for (Port p : port_list) {
			if (p.getName().equals(name)) return p;
		}
		return null;
	}
	
	/**
	 * The method to return the list of neighbours of a port
	 * @param p		The port to be observed
	 * @return		returns the list of neighbours around the port
	 */
	public ArrayList<Port> neighours(Port p) {
		ArrayList<Port> list = new ArrayList<Port>();
		for (Port temp : port_list) {
			if (temp!=p) list.add(temp);
		}
		return list;
	}
	
	public int size() { return size; }
	public int getDist(Port a, Port b) { return edges[a.getIndex()][b.getIndex()]; }
}

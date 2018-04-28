import java.util.ArrayList;

public class Map {
	
	private ArrayList<Port> port_list;
	private int[][] edges;
	private int size;
	
	public Map() {
		port_list = new ArrayList<Port>();
		edges = null;
		size = 0;
	}
	
	public void addPort(String n, int t) {
		Port p = new Port(n, t);
		port_list.add(p);
		size += 1;
	}
	
	public void createMatrix() {
		edges = new int[size][size];
	}
	
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
		
		//print();
	}
	
	public ArrayList<Port> connected(Port a) {
		ArrayList<Port> list = new ArrayList<Port>();
		// FINISH LATER
		return list;
	}
	
	public void print() {
		for (int[] a : edges) {
			for (int b : a) {
				System.out.print(b + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
}
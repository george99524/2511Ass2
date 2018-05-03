import java.util.ArrayList;

public class PortMap {
	
	private ArrayList<Port> port_list;
	private int[][] edges;
	private int size;
	
	public PortMap() {
		
		port_list = new ArrayList<Port>();
		edges = null;
		size = 0;
	}
	
	public void addPort(String n, int t) {
		
		Port p = new Port(n, t, size);
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
	
	public void print() {
		
		for (int[] a : edges) {
			for (int b : a) {
				System.out.print(b + " ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	public Port getPort(String name) {
		
		for (Port p : port_list) {
			if (p.getName().equals(name)) return p;
		}
		return null;
	}
	
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

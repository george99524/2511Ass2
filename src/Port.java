
public class Port {
	
	private String name;
	private int refuel_time;
	private int heuristic;
	
	public Port(String n, int t) {
		name = n;
		refuel_time = t;
		
		heuristic = t; //CHANGE LATER
	}
	
	public String getName() { return name;}
	public int getTime() { return refuel_time;}
	public int getHeuristic() { return heuristic;}
}

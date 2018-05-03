
public class Port {
	
	private String name;
	private int refuel_time;
	private int index;
	
	public Port(String n, int t, int i) {
		
		name = n;
		refuel_time = t;
		index = i;
	}
	
	public String getName() { return name;}
	public int getTime() { return refuel_time;}
	public int getIndex() { return index;}
}


public class Job {
	
	private Port start;		// The port where the shipment is picked up
	private Port dest;		// The port where the shipment is dropped off
	private int dist;		// The distance between the ports
	
	public Job(Port a, Port b, int d) {
		
		start = a;
		dest = b;
		dist = d;
	}
	
	public Port start() { return start; }
	public Port dest() { return dest; }
	public int dist() { return dist; }
}

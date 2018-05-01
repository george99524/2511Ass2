
public class Job {
	
	private Port start;
	private Port dest;
	private int dist;
	
	public Job(Port a, Port b, int d) {
		start = a;
		dest = b;
		dist = d;
	}
	
	public Port start() { return start; }
	public Port dest() { return dest; }
	public int dist() { return dist; }
}

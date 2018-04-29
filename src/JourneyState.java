import java.util.ArrayList;

public class JourneyState {
	
	private Port curr;
	private Port goal;
	private ArrayList<Port> path;
	private int total;
	
	public JourneyState(Port c, ArrayList<Port> p, int t) {
		//map = m;
		curr = c;
		path = p;
		total = t;
	}
	
	public void add(Port p, int dist) {
		Port temp = path.get(path.size()-1);
		
		path.add(curr);
		curr = p;
		total += dist + p.getTime();
	}
	
	public Port getCurr() { return curr; }
}

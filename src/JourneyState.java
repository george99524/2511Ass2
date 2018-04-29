import java.util.ArrayList;

public class JourneyState {
	
	private Port curr;
	private Port goal;
	private ArrayList<Port> path;
	private int total;
	
	public JourneyState(Port c, int t) {
		//map = m;
		curr = c;
		path = new ArrayList<Port>();
		total = t;
	}
	
	public void add(Port p, int dist) {
		Port temp = path.get(path.size()-1);
		
		path.add(curr);
		curr = p;
		total += dist + p.getTime();
	}
	
	public Port getCurr() { return curr; }
	public void setCurr(Port p) { curr = p; }
}

import java.util.ArrayList;

public class JourneyState {
	
	private Port curr;
	private ArrayList<Job> jobs;
	private ArrayList<Port> path;
	private int total;
	
	public JourneyState(Port a, ArrayList<Job> j, ArrayList<Port> p, int t) {
		
		curr = a;
		jobs = j;
		path = p;
		total = t;
	}
	
	public void printPath() {
		
		System.out.print("cost =  " + total + "\n");
		for (int i = 0; i<path.size() - 1; i += 1) {
			System.out.print("Ship " + path.get(i).getName() + " to " + path.get(i+1).getName() + "\n");
		}
		//System.out.print("Ship " + path.get(path.size()-1).getName() + " to " + curr.getName() + "\n");
	}
	
	public ArrayList<Job> getJobs() { 
		
		ArrayList<Job> copy = new ArrayList<Job>();
		for (Job j : jobs) copy.add(j);
		return copy; 
	}
	
	public ArrayList<Port> jobSites() { 
		ArrayList<Port> sites = new ArrayList<Port>();
		for (Job j : jobs) {
			if (!sites.contains(j.start())) { sites.add(j.start()); }
			if (!sites.contains(j.dest())) { sites.add(j.dest()); }
		}
		return sites; 
	}
	
	public ArrayList<Port> getPath() { 
		ArrayList<Port> copy = new ArrayList<Port>();
		for (Port p : path) copy.add(p);
		return copy; 
	}
	
	public int nJobs() { return jobs.size(); }
	public Port getCurr() { return curr; }
	public void setCurr(Port p) { curr = p; }
	public boolean isDone() { return jobs.isEmpty(); }
	public int cost() { return total; }
}

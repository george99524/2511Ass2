import java.util.ArrayList;

public class JourneyState {
	
	private Port curr;				// The current port
	private ArrayList<Job> jobs;	// The list of jobs left to be done
	private ArrayList<Port> path;	// The path travelled so far
	private int total;				// The total cost of the current journey
	
	public JourneyState(Port a, ArrayList<Job> j, ArrayList<Port> p, int t) {
		
		curr = a;
		jobs = j;
		path = p;
		total = t;
	}
	
	/**
	 * The method to print the path travelled so far by the state
	 * Also prints the cost of the path
	 */
	public void printPath() {
		
		System.out.print("cost = " + total + "\n");
		for (int i = 0; i<path.size() - 1; i += 1) {
			System.out.print("Ship " + path.get(i).getName() + " to " + path.get(i+1).getName() + "\n");
		}
	}
	
	/**
	 * The method to get the job list of the state
	 * @return	returns a copy of the job list
	 */
	public ArrayList<Job> getJobs() { 
		
		ArrayList<Job> copy = new ArrayList<Job>();
		for (Job j : jobs) copy.add(j);
		return copy; 
	}
	
	/**
	 * The method to get the list of all the ports needed to be travelled to to complete jobs
	 * (i.e. all distinct start and dest ports for each job)
	 * @return	returns all job sites
	 */
	public ArrayList<Port> jobSites() { 
		
		ArrayList<Port> sites = new ArrayList<Port>();
		for (Job j : jobs) {
			if (!sites.contains(j.start())) { sites.add(j.start()); }
			if (!sites.contains(j.dest())) { sites.add(j.dest()); }
		}
		return sites; 
	}
	
	/**
	 * The method to get the path of the state
	 * @return	returns a copy of the path
	 */
	public ArrayList<Port> getPath() { 
		
		ArrayList<Port> copy = new ArrayList<Port>();
		for (Port p : path) copy.add(p);
		return copy; 
	}
	
	public Port getCurr() { return curr; }
	public boolean isDone() { return jobs.isEmpty(); }
	public int cost() { return total; }
}

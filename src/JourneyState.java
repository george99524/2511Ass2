import java.util.ArrayList;

public class JourneyState {
	
	private Port curr;
	private ArrayList<Job> jobs;
	private ArrayList<Port> path;
	private int total;
	
	public JourneyState(Port a, ArrayList<Job> j) {
		//map = m;
		curr = a;
		jobs = j;
		path = new ArrayList<Port>();
		total = 0;
	}
	
	public void travel(Port p, int dist) {
		//Port temp = path.get(path.size()-1);
		//System.out.print(curr.getName()+" current\n");
		//System.out.print(p.getName()+" added to state\n");
		path.add(curr);
		curr = p;
		total += dist + p.getTime();
		//printPath();
	}
	
	public void printPath() {
		System.out.print("cost =  " + total + "\n");
		System.out.print("n =  " + path.size() + "\n");
		for (int i = 0; i<path.size() - 1; i += 1) {
			System.out.print("Ship " + path.get(i).getName() + " to " + path.get(i+1).getName() + "\n");
		}
		System.out.print("Ship " + path.get(path.size()-1).getName() + " to " + curr.getName() + "\n");
	}
	
	public void completeJob(Job j) {
		travel(j.dest(), j.dist());
		jobs.remove(j);
	}
	
	public ArrayList<Job> getJobs() { return jobs; }
	public int nJobs() { return jobs.size(); }
	public Port getCurr() { return curr; }
	public void setCurr(Port p) { curr = p; }
	//public Port getGoal() { return goal; }
}

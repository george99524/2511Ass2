import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import javafx.util.Pair;

public class Searcher {
	
	private Heuristic heuristic;
	private ArrayList<Job> jobs;
	private PriorityQueue<Pair<JourneyState, Integer>> queue;
	
	public Searcher() {
		// Set heuristic
		heuristic = new HeuristicStandard();
		queue = new PriorityQueue<Pair<JourneyState, Integer>>(new StateComparator());
	}
	
	public void findPath(Port start, ArrayList<Job> list, PortMap map) {
		
		ArrayList<JourneyState> seen = new ArrayList<JourneyState>();
		Map<Pair<ArrayList<Job>, Port>, Integer> g_state = new HashMap<Pair<ArrayList<Job>, Port>, Integer>();
		
		jobs = list;
		ArrayList<Port> a = new ArrayList<Port>();
		a.add(start);
		JourneyState state = new JourneyState(start, jobs, a, 0);
		
		generateStates(state, map, g_state);
		
		// Start node is expanded
		int expanded = 1;
		while(!state.isDone()) {
			// Node with lowest fValue[] is removed from the head of the queue
			Pair<JourneyState, Integer> c = queue.remove();
			state = c.getKey();
			if (seen.contains(state)) continue;
			
			seen.add(state);
			//seen2.add(c);
			expanded += 1;
			//System.out.print(state.getCurr().getName() + " " + state.cost() +" popped\n");
		
			
			// Generate new states for each neighbour
			generateStates(state, map, g_state);
		}
		System.out.print(expanded + " nodes expanded\n");
		state.printPath();
	}
	
	public void generateStates(JourneyState state, PortMap map, Map<Pair<ArrayList<Job>, Port>, Integer> g) {
		
		int cost;
		ArrayList<Port> a;
		ArrayList<Job> jobs_left;
		Job j;
		JourneyState temp;
		Port current = state.getCurr();
		for (Port p : state.jobSites()) {
			if (p!=current) {
				//System.out.print(state.jobSites().size()+"\n");
				//cost = current.getTime() + map.getDist(current, p);
				cost = current.getTime() + map.getDist(current, p);
				//System.out.print("cost = "+cost+"\n");
				a = state.getPath();
				a.add(p);
				
				jobs_left = state.getJobs();
				j = isJob(current, p);
				if (j!=null) {
					//System.out.print("Job Done\n");
					jobs_left.remove(j);
				}
				
				temp = new JourneyState(p, jobs_left, a, state.cost() + cost);
				//if (seen.contains(temp)) continue;
				// g(x) is accumulated cost up to the current state
				int gValue = temp.cost();
				Pair<ArrayList<Job>, Port> pair = new Pair<ArrayList<Job>, Port>(jobs_left, p);
				if (g.containsKey(pair)) {
					if (g.get(pair)>gValue) {
						g.put(pair, gValue);
					}else {
						continue;
					}
				}else {
					g.put(pair, gValue);
				}
				
				
				int fValue = gValue + heuristic.getHeur(temp, map);
				queue.add(new Pair<JourneyState, Integer>(temp, fValue));
			}
		}
	}
	
	public Job isJob(Port a, Port b) {
		
		for (Job j : jobs) {
			if (j.start()==a && j.dest()==b) return j;
		}
		return null;
	}
}

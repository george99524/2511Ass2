import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import javafx.util.Pair;

public class Searcher {
	
	private Heuristic heuristic;								// The heuristic used for the search
	private ArrayList<Job> jobs;								// The list of jobs required to be completed in the search
	private PriorityQueue<Pair<JourneyState, Integer>> queue;	// The queue of states to be expanded
	
	public Searcher() {
		// Set heuristic
		heuristic = new HeuristicStandard();
		queue = new PriorityQueue<Pair<JourneyState, Integer>>(new StateComparator());
	}
	
	/**
	 * The method to find the optimal path to complete all provided jobs
	 * Prints the optimal path to standard output with the cost and the number of expanded nodes
	 * 
	 * THIS FUNCTION ASSUMES THAT A PATH DEFINITELY EXISTS AND THE MAP IS CONNECTED
	 * @param start		The start port
	 * @param list		The list of jobs required to be done
	 * @param map		The map used to navigate the ports
	 */
	public void findPath(Port start, ArrayList<Job> list, PortMap map) {
		
		// A list of already expanded states
		ArrayList<JourneyState> seen = new ArrayList<JourneyState>();
		
		// A list of the g values for each combination of the list of jobs left to be done
		// and the current port
		// This is essentially comparing the g values of each state
		Map<Pair<ArrayList<Job>, Port>, Integer> g_state = new HashMap<Pair<ArrayList<Job>, Port>, Integer>();
		
		// Set the list of jobs to be done
		jobs = list;
		
		// Initiate path and initiate the first state
		ArrayList<Port> path = new ArrayList<Port>();
		path.add(start);
		JourneyState state = new JourneyState(start, jobs, path, 0);
		
		// Generate states
		generateStates(state, map, g_state);
		
		// Start node is expanded
		int expanded = 1;
		
		// Stop when the first completed state is reached
		// This is always a "best" solution because the queue is ordered by f(x), which is
		// the minimum cost to complete each state
		while(!state.isDone()) {
			// Pair with lowest fValue[] is removed from the head of the queue
			// Extract the state, count an expansion
			state = queue.remove().getKey();
			expanded += 1;
			
			// If current state is already seen, skip the state
			if (seen.contains(state)) continue;
			
			// Add the state to seen list
			seen.add(state);
			
			
			// Generate new states for each neighbour of the current port
			generateStates(state, map, g_state);
		}
		
		// Print the path and its information
		System.out.print(expanded + " nodes expanded\n");
		state.printPath();
	}
	
	/**
	 * The method to generate the states for each neighbour of the provided state
	 * @param state		The current state that is observed
	 * @param map		The map to be transversed
	 * @param g			The hashmap containing the g(x) values for each "state"
	 */
	public void generateStates(JourneyState state, PortMap map, Map<Pair<ArrayList<Job>, Port>, Integer> g) {
		// Get the current port from the state
		Port current = state.getCurr();
		
		// Instead of getting all connected ports as candidates for observation, since the map
		// is connected, we can instead observe all the possible jobsites (i.e. a port we MUST travel
		// to at some point)
		for (Port p : state.jobSites()) {
			// Skip looking at itself
			if (p!=current) {
				// Get cost (refuel + distance) to travel to neighbour
				int cost = current.getTime() + map.getDist(current, p);

				// Append the neighbour to the path
				ArrayList<Port> new_path = state.getPath();
				new_path.add(p);
				
				// If the movement we just made completed a job, remove it from the list
				ArrayList<Job> jobs_left = state.getJobs();
				Job j = isJob(current, p);
				if (j!=null) jobs_left.remove(j);
				
				// Create the new state
				JourneyState temp = new JourneyState(p, jobs_left, new_path, state.cost() + cost);
				
				// Get the g(x) value for the current state
				int gValue = temp.cost();
				// Add new entry for g(x) if it doesn't exist for the current state
				// Overwrite old entry if new path costs less
				// Skip the current state if the new path costs more
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
				
				// Calculate f(x) and add to the queue
				int fValue = gValue + heuristic.getHeur(temp, map);
				queue.add(new Pair<JourneyState, Integer>(temp, fValue));
			}
		}
	}
	
	/**
	 * The method to check if travelling from A and B is a job
	 * @param a		Port A
	 * @param b		Port B
	 * @return		returns the job that is completed by travelling from A to B  
	 * 				returns null otherwise
	 */
	public Job isJob(Port a, Port b) {
		
		for (Job j : jobs) {
			if (j.start()==a && j.dest()==b) return j;
		}
		return null;
	}
}

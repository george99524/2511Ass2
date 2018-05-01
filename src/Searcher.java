import java.util.ArrayList;
import java.util.PriorityQueue;
import javafx.util.Pair;

public class Searcher {
	
	private Heuristic heuristic;
	private ArrayList<Job> jobs;
	private PriorityQueue<Pair<Job, Integer>> queue;
	private JourneyState state;
	
	public Searcher(Heuristic h) {
		heuristic = h;
		queue = new PriorityQueue<Pair<Job, Integer>>(new JobComparator());
	}
	
	public void findPath(Port start, ArrayList<Job> list, Map map) {
		//ArrayList<Port> seen = new ArrayList<Port>();
		
		jobs = list;
		state = new JourneyState(start, jobs);
		
		// Cost of travelling to this node, -1 is infinity
		//int[] gValue = new int[map.size()];
		//java.util.Arrays.fill(gValue, -1);
		
		//gValue[start.getIndex()] = 0;
		
		// Cost to finish remaining jobs by travelling through this node
		// fValue = gValue + heuristic
		//int[] fValue = new int[map.size()];
		//java.util.Arrays.fill(fValue, -1);
				
		//fValue[start.getIndex()] = heuristic.getHeur(state, map, start);
		
		for (Job j : jobs) {
			// g(x) is the cost of travelling to the job and completing it
			int gValue = map.getDist(start, j.start()) + j.dist();
			int fValue = gValue + heuristic.getHeur(state, map, j);
			queue.add(new Pair<Job, Integer>(j, fValue));
		}
		
		// Start node is expanded
		int expanded = 1;
		while(!jobs.isEmpty()) {
			// Node with lowest fValue[] is removed from the head of the queue
			Pair<Job, Integer> c = queue.remove();
			Job current = c.getKey();
			//System.out.print(current.getName() + " " + c.getValue() +" popped\n");
		
			// Travel to the pickup location for the current job
			if (state.getCurr()!=current.start()) {
				state.travel(current.start(), map.getDist(state.getCurr(), current.start()));
				expanded += 1;
			}
			
			// Complete the job
			state.completeJob(current);
			jobs.remove(current);
			expanded += 1;
			
			for (Job j : jobs) {
				// g(x) is the cost of travelling to the job and completing it
				int gValue = map.getDist(state.getCurr(), j.start()) + j.dist();
				int fValue = gValue + heuristic.getHeur(state, map, j);
				queue.add(new Pair<Job, Integer>(j, fValue));
			}
		}
		System.out.print(expanded + " nodes expanded\n");
		state.printPath();
	}
}

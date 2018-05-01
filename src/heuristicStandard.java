
public class heuristicStandard implements Heuristic {
	
	// This heuristic simply sums up the cost of COMPLETING (i.e. travelling from start to dest) 
	// all remaining jobs after completing the supplied job
	//
	// This is admissible because it ignores the cost of travelling between ports in between different
	// jobs, therefore the heuristic will always underestimate the actual cost of completing the rest
	// This heuristic also ignores the time taken to refuel at each port
	public int getHeur(JourneyState s, Map m, Job j) {
		int sum = 0;
		for (Job temp : s.getJobs()) {
			if (temp!=j) sum += temp.dist();
		}
		return sum;
	}
}

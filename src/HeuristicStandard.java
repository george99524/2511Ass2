
public class HeuristicStandard implements Heuristic {
	
	// This heuristic simply sums up the cost of COMPLETING (i.e. travelling from start to dest) 
	// all remaining jobs after completing the supplied job (i.e. when it is removed from the list)
	//
	// This is admissible because it ignores the cost of travelling between ports in between different
	// jobs, therefore the heuristic will always underestimate (or be equal to) the actual cost of 
	// completing the rest
	// This heuristic also ignores the time taken to refuel at each port
	
	/**
	 * Method to get h(x) for an A* search Algorithm on a connected graph
	 * @param s		The state of the journey so far, includes a list of jobs left and the current position
	 * @param m		The map (graph) used for the journey
	 * @return
	 */
	public int getHeur(JourneyState s, PortMap m) {
		
		int sum = 0;
		int min = -1;
		for (Job temp : s.getJobs()) {
			int dist = m.getDist(s.getCurr(), temp.start());
			if (min==-1 || min>dist) min = dist;
			sum += temp.dist() + temp.start().getTime();
		}
		
		
		
		return sum + min;
	}
}

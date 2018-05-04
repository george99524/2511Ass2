
public class HeuristicStandard implements Heuristic {
	
	// This heuristic simply sums up the cost of COMPLETING (i.e. travelling from start to dest) 
	// all remaining jobs(including refuel) of the provided state and the distance to the nearest job
	// 
	// This is admissible because it ignores the cost of travelling between ports in between different
	// jobs, therefore the heuristic will always underestimate (or be equal to) the actual cost of 
	// completing the rest of the jobs
	
	/**
	 * Method to get h(x) for an A* search Algorithm on a connected graph
	 * @param s		The state of the journey so far, includes a list of jobs left and the current position
	 * @param m		The map (graph) used for the journey
	 * @return
	 */
	public int getHeur(JourneyState s, PortMap m) {
		
		int sum = 0;	// The sum of completing all the jobs (wiithout accounting travel time inbetween jobs)
		int min = -1;	// The distance to the nearest job (i.e. the start port for the job)
		for (Job temp : s.getJobs()) {
			
			int dist = m.getDist(s.getCurr(), temp.start());
			if (min==-1 || min>dist) min = dist;
			sum += temp.dist() + temp.start().getTime();
		}
		
		return sum + min;
	}
}

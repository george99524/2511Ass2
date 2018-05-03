
public interface Heuristic {
	
	/**
	 * Method to get h(x) for an A* search Algorithm on a connected graph
	 * @param s		The state of the journey so far, includes a list of jobs left and the current position
	 * @param m		The map (graph) used for the journey
	 * @return
	 */
	public int getHeur(JourneyState s, PortMap m);
}

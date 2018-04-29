import java.util.ArrayList;
import java.util.PriorityQueue;
import javafx.util.Pair;

public class AStarSearch {
	
	private Heuristic heuristic;
	
	public AStarSearch(Heuristic h) {
		heuristic = h;
	}
	
	public void findPath(Port start, Port dest, Map map) {
		ArrayList<Port> seen = new ArrayList<Port>();
		PriorityQueue<Pair<Port, Integer>> to_visit = new PriorityQueue<Pair<Port, Integer>>(new PortComparator());
		
		JourneyState state = new JourneyState(start, new ArrayList<Port>(), 0);
		
		// Cost of Start node to other nodes, -1 is infinity
		int[] gValue = new int[map.size()];
		java.util.Arrays.fill(gValue, -1);
		
		gValue[start.getIndex()] = 0;
		
		// Cost of travelling from start node to finish THROUGH this node
		// fValue = gValue + heuristic
		int[] fValue = new int[map.size()];
		java.util.Arrays.fill(fValue, -1);
				
		fValue[start.getIndex()] = heuristic.getHeur(state, map);
		
		to_visit.add(new Pair<Port, Integer>(start, 0));
		seen.add(start);
		
		while(!to_visit.isEmpty()) {
			Port current = to_visit.remove().getKey();
			seen.add(current);
			
			state.add(current, map.getDist(state.getCurr(), current));
			
			for (Port neighbour : map.connected(current)) {
				if (seen.contains(neighbour)) continue;
				
				Pair<Port, Integer> temp = new Pair<Port, Integer>(neighbour, heuristic.getHeur(state, map, neighbour));
				if (!to_visit.contains(temp)) to_visit.add(temp);
				to_visit.add(new Pair<Port, Integer>(neighbour))
			}
		}
	}
}

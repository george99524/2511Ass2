import java.util.ArrayList;
import java.util.PriorityQueue;
import javafx.util.Pair;

public class Search {
	
	private Heuristic heuristic;
	
	public Search(Heuristic h) {
		heuristic = h;
	}
	
	public void findPath(Port start, Port dest, Map map) {
		ArrayList<Port> seen = new ArrayList<Port>();
		PriorityQueue<Pair<Port, Integer>> to_visit = new PriorityQueue<Pair<Port, Integer>>(new PortComparator());
		
		JourneyState state = new JourneyState(start, dest);
		
		// Cost of Start node to other nodes, -1 is infinity
		int[] gValue = new int[map.size()];
		java.util.Arrays.fill(gValue, -1);
		
		gValue[start.getIndex()] = 0;
		
		// Cost of travelling from start node to finish THROUGH this node
		// fValue = gValue + heuristic
		int[] fValue = new int[map.size()];
		java.util.Arrays.fill(fValue, -1);
				
		fValue[start.getIndex()] = heuristic.getHeur(state, map, start);
		
		to_visit.add(new Pair<Port, Integer>(start, 0));
		seen.add(start);
		int expanded = 0;
		while(!to_visit.isEmpty()) {
			//System.out.print("\n\n\n");
			//for (Pair<Port, Integer> p : to_visit) {
			//	System.out.print(p.getKey().getName()+" "+p.getValue()+"\n");
			//}
			//System.out.print("\n\n\n");
			// Node with lowest fValue[] is removed from the head of the queue
			Pair<Port, Integer> c = to_visit.remove();
			Port current = c.getKey();
			//System.out.print(current.getName() + " " + c.getValue() +" popped\n");
			expanded += 1;
			if (current!=start) {
				seen.add(current);
				//System.out.print(current.getName()+"\n");
				//System.out.print(state.getCurr().getIndex() + current.getIndex()+"hi\n");
				state.add(current, map.getDist(state.getCurr(), current));
			}
			
			if (state.getCurr().equals(dest)) {
				System.out.print(expanded + " nodes expanded\n");
				state.printPath();
				return;
			}
			
			for (Port neighbour : map.connected(current)) {
				//System.out.print("hi\n");
				if (seen.contains(neighbour)) continue;
				
				//Pair<Port, Integer> temp = new Pair<Port, Integer>(neighbour, heuristic.getHeur(state, map, neighbour));
				//if (!to_visit.contains(temp)) to_visit.add(temp);
				
				// gValue is the total distance travelled to get to the neighbour node
				int temp_gValue = gValue[current.getIndex()] + map.getDist(current, neighbour);
				int index = neighbour.getIndex();
				// Not a better path
				if (gValue[index] != -1 && temp_gValue >= gValue[index]) continue;
				
				// CHANGE LATER
				//state.setCurr(neighbour);
				
				gValue[index] = temp_gValue;
				fValue[index] = gValue[index] + heuristic.getHeur(state, map, neighbour);
				//System.out.print(neighbour.getName() + " "+gValue[index] + " " + fValue[index]+"\n");
				Pair<Port, Integer> temp = new Pair<Port, Integer>(neighbour, fValue[index]);
				to_visit.add(temp);
			}
		}
	}
}

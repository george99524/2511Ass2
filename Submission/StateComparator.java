import java.util.Comparator;
import javafx.util.Pair;

public class StateComparator implements Comparator<Pair<JourneyState, Integer>> {
	
	public int compare(Pair<JourneyState, Integer> a, Pair<JourneyState, Integer> b) {
		
		if (a.getValue() > b.getValue()) { return 1; }
		else if (a.getValue() < b.getValue()) { return -1; }
		else return 0;
	}
}

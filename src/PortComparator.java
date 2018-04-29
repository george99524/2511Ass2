import java.util.Comparator;
import javafx.util.Pair;

public class PortComparator implements Comparator<Pair<Port, Integer>> {
	public int compare(Pair<Port, Integer> a, Pair<Port, Integer> b) {
		if (a.getValue() > b.getValue()) { return 1; }
		else if (a.getValue() < b.getValue()) { return -1; }
		return 0;
	}
}

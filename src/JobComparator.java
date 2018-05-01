import java.util.Comparator;
import javafx.util.Pair;

public class JobComparator implements Comparator<Pair<Job, Integer>> {
	public int compare(Pair<Job, Integer> a, Pair<Job, Integer> b) {
		if (a.getValue() > b.getValue()) { return 1; }
		else if (a.getValue() < b.getValue()) { return -1; }
		else return 0;
	}
}

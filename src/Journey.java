import java.util.ArrayList;

public class Journey {
	
	private Map map;
	private Port start;
	private Port end;
	Strategy strategy;
	
	public Journey(Map m, Port s, Port e, Strategy a) {
		map = m;
		start = s;
		end = e;
		strategy = a;
	}
	
	public ArrayList<Port> findPath() {
		return strategy.search(map, start, end);
	}
}

import java.util.ArrayList;

public interface Strategy {
	
	public ArrayList<Port> search(Map m, Port p1, Port p2);
}

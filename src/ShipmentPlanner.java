import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShipmentPlanner {
	
	PortMap map;				// The map(graph) created
	ArrayList<Job> jobs;		// A list of jobs requested
	Searcher s;					// The path searcher
	
	public ShipmentPlanner(PortMap m, ArrayList<Job> list, Searcher search) {
		map = m;					
		jobs = list;			
		s = search;						
	}
	
	public static void main(String args[]) {
		
		
		PortMap map = new PortMap();
		ArrayList<Job> jobs = new ArrayList<Job>();
		Searcher s = new Searcher();
		
		Scanner sc = null;
	      try
	      {
	          sc = new Scanner(new File(args[0]));
	          int time;
	          String name, name1, name2;
	          
	          while(sc.hasNext()) {
		        	String command = sc.next();
		        	switch (command) {
		        		case "#": 
		        			sc.nextLine();
		        			break;
		        		case "Refuelling":
		        			time = sc.nextInt();
		        			name = sc.next();
		        			map.addPort(name, time);
		        			break;
		        		case "Time":
		        			time = sc.nextInt();
		        			name1 = sc.next();
		        			name2 = sc.next();
		        			map.addEdge(name1, name2, time);
		        			break;
		        		case "Shipment":
		        			name1 = sc.next();
		        			name2 = sc.next();
		        			Port a = map.getPort(name1);
		        			Port b = map.getPort(name2);
		        			jobs.add(new Job(a, b, map.getDist(a, b)));
		        			break;
		        	}
	          }
	      }
	      catch (FileNotFoundException e)
	      {
	          System.out.println(e.getMessage());
	      }
	      finally
	      {
	          if (sc != null) sc.close();
	      }
	      
	      ShipmentPlanner planner = new ShipmentPlanner(map, jobs, s);
	      // Search for the most efficient path
	      planner.findPath(map.getPort("Sydney"), jobs, map);
	}
	
	public void findPath(Port start, ArrayList<Job> list, PortMap map) {
		s.findPath(map.getPort("Sydney"), jobs, map);
	}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShipmentPlanner {
	
	public static void main(String args[]) {
		
		PortMap map = new PortMap();					// The map(graph) created
		ArrayList<Job> jobs = new ArrayList<Job>();		// A list of jobs requested
		
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
	      
	      // Search for the most efficient path
	      Searcher s = new Searcher();
	      s.findPath(map.getPort("Sydney"), jobs, map);
	}
}

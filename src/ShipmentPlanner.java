import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShipmentPlanner {
	
	public static void main(String args[]) {
		Map map = new Map();
		
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
		        			//System.out.print("Scanned " + time + " " + name +  "\n");
		        			break;
		        		case "Time":
		        			time = sc.nextInt();
		        			name1 = sc.next();
		        			name2 = sc.next();
		        			map.addEdge(name1, name2, time);
		        			//System.out.print("Scanned " + time + " " + name1 + " " + name2 + "\n");
		        			break;
		        		case "Shipment":
		        			name1 = sc.next();
		        			name2 = sc.next();
		        			//Strategy s = AStar();
		        			//System.out.print("Scanned " + name1 + " " + name2 +  "\n");
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
	}
}

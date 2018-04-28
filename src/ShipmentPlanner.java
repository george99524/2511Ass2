import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ShipmentPlanner {
	
	public static void main(String args[]) {
		Scanner sc = null;
	      try
	      {
	          sc = new Scanner(new File(args[0]));
	          // Read input from the scanner here
	          
	          while(sc.hasNext()) {
		        	String command = sc.next();
		        	switch (command) {
		        		case "#": 
		        			sc.nextLine();
		        			break;
		        		case "Cinema":
		        			cinema = sc.next();
		        			row = sc.next();
		        			seats = sc.nextInt();
		        			break;
		        		case "Session":
		        			cinema = sc.next();
		        			time = sc.next();
		        			
		        	
		
		        			break;
		        		case "Request":
		        			booking_id = sc.nextInt();
		        			cinema = sc.next();
		        			time = sc.next();
		        			tickets = sc.nextInt();
		        			
		      
		        			break;
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

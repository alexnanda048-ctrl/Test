/* Alex Nanda
 Emergency Route Planning
 COP3503 Computer Science 2
 EmergencyRoute.java
*/

import java.util.ArrayList;

public class EmergencyRoute
{
	public static RoutePlan planRoute(int destination, int startingFuel,
			ArrayList<SupplyDepot> depots)
	{
		
		//initialize variables and arrays
		ArrayList<Integer> stops = new ArrayList<>();
		int track = 0;
		int count = 0;
		int check = -1;
		int currentPosition = 0;
		int compare = 0;
		int distance = 0;
		
		//continue searching till at destaination or error
		while(currentPosition < destination) 
		{
			//dont check for depot if the end can be reached
			if (currentPosition + startingFuel >= destination) 
			{
				//if successfully ended return stop count and stop ID array
		        return new RoutePlan(count, stops);
		    }
			
			//search entire depot list for ideal stop
			for(int i = 0; i<depots.size(); i++) 
			{
				//if stop can be reached and the most ideal stop track depot
				if(startingFuel + currentPosition >= depots.get(i).getPosition() && 
						compare <= depots.get(i).getFuel() && currentPosition < depots.get(i).getPosition()) 
				{
					//if both depot offer same fuel amount chose lesser distance
					if(compare == depots.get(i).getFuel() && distance < depots.get(i).getPosition()) 
					{
						continue;
					}
					
					//track depot, its fuel and distance for future calculations
					else 
					{
						compare = depots.get(i).getFuel();
						check=1; //proves successful run
						track = i;
						distance = depots.get(i).getPosition();
					}
					
				}
				
			}
			
			//if not possible from the jump
			if(check == -1) 
			{
				//return -1 stops and empty stop ID array
				RoutePlan result = new RoutePlan(check, stops);
				return result;
			}
			
			//if greedy cant make it to final destination
			else if(check == 0) 
			{
				//return amount of stops and stop ID array
				RoutePlan result = new RoutePlan(count, stops);
				return result;
			}
			
			//if viable next stop
			else 
			{
				//set new fuel amount, position, and add depot ID to array
				startingFuel = startingFuel - (depots.get(track).getPosition() - currentPosition)
						+ depots.get(track).getFuel();
				currentPosition = depots.get(track).getPosition();
				stops.add(depots.get(track).getDepotID());
				count++;
			}
			
			//reset counting variables for next run
			check = 0;
			compare = 0;
			track = 0;
			
		}
		
		//if successfully ended return stop count and stop ID array
		RoutePlan result = new RoutePlan(count, stops);
		return result;
		
 	}
}
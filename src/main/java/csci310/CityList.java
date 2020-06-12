package csci310;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CityList {
	
	private List<CityResult> cities;
	
	public CityList(List<CityResult> cities)
	{
		this.cities = cities;
	}
	
	public List<CityResult> getCities()
	{
		return cities;
	}
	
	public void sortDistance(boolean nearToFar)
	{
		
		if (nearToFar)
		{
			Collections.sort(cities, new Comparator<CityResult>() {
				
				@Override
				public int compare(CityResult cr1, CityResult cr2)
				{
					int dist1 = cr1.getDistance();
					int dist2 = cr2.getDistance();
					
					if (dist1 < dist2)
					{
						return -1;
					}
					else if (dist1 == dist2)
					{
						return cr1.getName().compareTo(cr2.getName());
					}
					else
					{
						return 1;
					}
				}
				
			});
			
		}
		else
		{
				Collections.sort(cities, new Comparator<CityResult>() {
				
				@Override
				public int compare(CityResult cr1, CityResult cr2)
				{
					int dist1 = cr1.getDistance();
					int dist2 = cr2.getDistance();
					
					if (dist1 < dist2)
					{
						return 1;
					}
					else if (dist1 == dist2)
					{
						return cr2.getName().compareTo(cr1.getName());
					}
					else
					{
						return -1;
					}
				}
				
			});
			
		}
	}
	

}

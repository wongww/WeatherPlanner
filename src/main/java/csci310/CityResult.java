package csci310;

public class CityResult extends City {
	
	
	private double currentTemp;
	private int avgMinTemp;
	private int avgMaxTemp;
	private int distance;
	private int likes;
	
	// Constructor for activity planning page
	public CityResult(String name, String country, double currentTemp, int dist)
	{
		// Default ZIP code, and maximum & minimum temperatures to zero for activity planning page
		super(name, country, "0");
		this.avgMinTemp = 0;
		this.avgMaxTemp = 0;
		this.currentTemp = currentTemp;
		this.distance = dist;
	}
	
	// Constructor for vacation planning page
	public CityResult(String name, String country, String zipCode, int avgMinTemp, int avgMaxTemp, int dist)
	{
		super(name, country, zipCode);
		
		this.avgMinTemp = (int) ((avgMinTemp-273.15)*(9.0/5.0) + 32.0);
		this.avgMaxTemp = (int) ((avgMaxTemp-273.15)*(9.0/5.0) + 32.0);
		
		this.distance = dist;
	}
	
	public double getCurrentTemp()
	{
		return currentTemp;
	}
	
	public double getCurrentTempF() {
		
		int convert = (int) ((currentTemp-273.15)*(9.0/5.0) + 32.0);
		System.out.println(currentTemp + " " + convert);
		return convert;
	}
	
	public double getCurrentTempC()
	{
		int convert = (int) (currentTemp - 273);
		return convert;
	}
	
	public int getAvgMinTempF()
	{
		return avgMinTemp;
	}
	
	public int getAvgMinTempC()
	{
		int convert = (int) ((avgMinTemp - 32) * (5.0/9.0));
		return convert;
	}
	
	public int getAvgMaxTempF()
	{
		return avgMaxTemp;
	}
	
	public int getAvgMaxTempC()
	{
		int convert = (int) ((avgMaxTemp - 32) * (5.0/9.0));
		return convert;
	}
	
	public int getDistance()
	{
		return distance;
	}
	/*
	public void setLikes(int likes)
	{
		this.likes = likes;
	}
	
	public int getLikes()
	{
		return likes;
	}
	
	public void addLike()
	{
		likes++;
	}
	
	public void removeLike()
	{
		likes--;
	}
	*/
	
}


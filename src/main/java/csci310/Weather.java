package csci310;

public class Weather {
	
	private String date;
	private int high;
	private int low;
	private String weatherIcon;
	
	public Weather(String date, int high, int low, String icon)
	{
		this.date = date;
		this.high = high;
		this.low = low;
		this.weatherIcon = icon;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public int getHighF()
	{
		return high;
	}
	
	public int getHighC()
	{
		int convert = (int) ((high - 32) * (5.0/9.0));
		return convert;
	}
	
	public int getLowF()
	{
		return low;
	}
	
	public int getLowC()
	{
		int convert = (int) ((low - 32) * (5.0/9.0));
		return convert;
	}
	
	public String getWeatherIcon()
	{
		return weatherIcon;
	}
	

}

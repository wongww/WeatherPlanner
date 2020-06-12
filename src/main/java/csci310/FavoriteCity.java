package csci310;

import java.util.List;

public class FavoriteCity extends City{
	
	private List<Weather> fiveDay;
	private List<String> images;
	private int likes;
	
	public FavoriteCity(String name, String country, String zipCode, List<Weather> fiveDay, List<String> images)
	{
		super(name, country, zipCode);
		this.fiveDay = fiveDay;
		this.images = images;
	}
	
	public List<Weather> getFiveDay()
	{
		return fiveDay;
	}
	
	public List<String> getImages()
	{
		return images;
	}
	
	public void setFiveDay(List<Weather> forecast)
	{
		if(forecast.size() != 5) {
			return;
		}
		fiveDay = forecast;
	}
	
	public void setImages(List<String> imageList)
	{
		if(imageList.size() != 5) {
			return;
		}
		images = imageList;
	}
	
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
}

package business;

import java.util.Random;

import javax.ejb.EJB;

import beans.Weather;

public class WeatherManager implements WeatherInterface {
	
	@EJB
	Weather weather;

	public Weather generateWeather() {
		Random rand = new Random();
		int temp;
		weather = new Weather();
		String[] cities = {"Phoenix", "Los Angeles", "Austin"};
		
		int index = rand.nextInt(3);
		boolean cloudy = false;
		weather.setCity(cities[index]);
		
		if (weather.getCity().equalsIgnoreCase("austin")) {
			temp = (int)Math.floor(Math.random()*(110-40+1)+40);
			weather.setCloudy(true);
			weather.setTemp(temp);
		} else if (weather.getCity().equalsIgnoreCase("phoenix")){
			weather.setCloudy(cloudy);
			temp = (int)Math.floor(Math.random()*(120-50+1)+50);
			weather.setTemp(temp);
		} else if (weather.getCity().equalsIgnoreCase("los angeles")) {
			weather.setCloudy(cloudy);
			temp = (int)Math.floor(Math.random()*(85-60+1)+60);
			weather.setTemp(temp);
		}
		return weather;
	}

}

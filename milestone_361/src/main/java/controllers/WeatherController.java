package controllers;

import java.io.Serializable;
import java.util.Random;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.interceptor.Interceptors;

import beans.LoggingInterceptor;
import beans.User;
import beans.Weather;
import database.DatabaseAccess;

@Named
@ViewScoped
@Interceptors(LoggingInterceptor.class) 
public class WeatherController implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DatabaseAccess db;
	Weather weather;

	public String login(User user) {
		db = new DatabaseAccess();
		FacesContext.getCurrentInstance().getAttributes().put("user", user);
//		System.out.println(user.getUsername());
		if(db.validate(user)) {
//			System.out.println("inside first if statement");
			User temp = db.getUser(user);
			if (temp != null) {
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user", temp);
			}
			return "Home.xhtml";
		} else {
			return "Register.xhtml";
		}
		
	}
	
	public String register(User user) {
		db = new DatabaseAccess();
		FacesContext.getCurrentInstance().getAttributes().put("user", user);
		
		if (db.validate(user)) {
			return "Login.xhtml";
		} else {
			System.out.println(user.getUsername());
			System.out.println(user.getPassword());
			System.out.println(user.getFirstName());
			System.out.println(user.getLastName());
			System.out.println(user.getEmail());
			db.registerUser(user);
			return "Home.xhtml";
		}
	}
	
	public String generateWeather() {
		Random rand = new Random();
		int temp;
		weather = new Weather();
		String[] cities = {"Phoenix", "Los Angeles", "Austin"};
		
		int index = rand.nextInt(2);
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
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("weather", weather);
		return "WeatherReport.xhtml";
	}
}

package controllers;

import java.util.Random;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import beans.User;
import beans.Weather;
import database.DatabaseAccess;
import database.DatabaseAccessInterface;

@ManagedBean
@ViewScoped
public class WeatherController {
	
	
	DatabaseAccess db;
	Weather weather;

	public String login(User user) {
		db = new DatabaseAccess();
		FacesContext.getCurrentInstance().getAttributes().put("user", user);
		System.out.println(user.getUsername());
		if(db.validate(user)) {
			System.out.println("inside first if statement");
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
		weather = new Weather();
		String[] cities = {"Phoenix", "Los Angeles", "Austin"};
		int temp = rand.nextInt((100 - 0) + 1);
		int index = rand.nextInt((2 - 0) + 1);
		boolean cloudy = false;
		weather.setCity(cities[index]);
		weather.setTemp(temp);
		if (weather.getCity().equalsIgnoreCase("austin")) {
			weather.setCloudy(true);
		} else {
			weather.setCloudy(cloudy);
		}
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("weather", weather);
		return "WeatherReport.xhtml";
	}
}

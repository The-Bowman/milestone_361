package controllers;

import java.io.Serializable;
import java.security.Principal;
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
import business.WeatherInterface;
import business.WeatherManager;
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
	WeatherManager wi;


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
		wi = new WeatherManager();
		Weather weather = wi.generateWeather();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("weather", weather);
		return "WeatherReport.xhtml";
	}
	
	public String logout() {
		
		// invalidate session - logout user
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "Login.xhtml";				
	}
	
	public String getUserName() {
		// Get the logged in Principle
		Principal principle= FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
		if(principle == null)
		{
			return "";
		}
		else
		{
			return principle.getName();
		}
	}
	
	
}

package beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class Weather {

	private boolean cloudy;
	private int temp;
	private String city;

	/**
	 * 
	 */
	public Weather() {
		this.city = "";
		this.temp = 0;
		this.cloudy = false;
	}

	public Weather(String city, int temp, boolean cloudy) {
		this.city = city;
		this.temp = temp;
		this.cloudy = cloudy;
	}

	/**
	 * @return
	 */
	public boolean isCloudy() {
		return cloudy;
	}

	/**
	 * @param cloudy
	 */
	public void setCloudy(boolean cloudy) {
		this.cloudy = cloudy;
	}

	/**
	 * @return
	 */
	public int getTemp() {
		return temp;
	}

	/**
	 * @param temp
	 */
	public void setTemp(int temp) {
		this.temp = temp;
	}

	/**
	 * @return
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

}

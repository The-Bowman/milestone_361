package database;

import javax.ejb.Local;

import beans.User;

@Local
public interface DatabaseAccessInterface {

	// LoginService Methods
	/**
	 * @param user
	 * @return
	 */
	public boolean validate(User user);

	/**
	 * @param user
	 * @return
	 */
	public User getUser(User user);

	// RegisterService Methods
	/**
	 * @param user
	 * @return
	 */
	public int registerUser(User user);
	
}

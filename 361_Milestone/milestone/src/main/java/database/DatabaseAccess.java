package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.User;

public class DatabaseAccess implements DatabaseAccessInterface {

	// SQL Connection Properties
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private String dbURL = "jdbc:mysql://localhost:3306/weather";
	private String username = "root";
	private String pword = "root";

	@Override
	public boolean validate(User user) {
		boolean status = false;
		try {
			System.out.println("inside validate in Database Access");
			conn = DriverManager.getConnection(dbURL, username, pword);
			stmt = conn.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			rs = stmt.executeQuery();
			status = rs.next();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return status;
	}

	@Override
	public User getUser(User user) {
		if (validate(user)) {
			try {
				conn = DriverManager.getConnection(dbURL, username, pword);
				stmt = conn.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
				stmt.setString(1, user.getUsername());
				stmt.setString(2, user.getPassword());
				rs = stmt.executeQuery();
				rs.next();
				User temp = new User();
				temp.setFirstName((String) rs.getObject("first_name"));
				temp.setLastName((String) rs.getObject("last_name"));
				temp.setUsername((String) rs.getObject("username"));
				temp.setEmail((String) rs.getObject("email"));
				temp.setPassword((String) rs.getObject("password"));
				return temp;
			} catch (SQLException e) {
				System.out.println(e);
			}
		}
		return null;
	}

	@Override
	public int registerUser(User user) {
		int result = 0;
		try {
			System.out.println("Inside registerUser");
			conn = DriverManager.getConnection(dbURL, username, pword);
			stmt = conn.prepareStatement("INSERT INTO user"
					+ "  (username, password, first_name, last_name, email) VALUES " + " (?, ?, ?, ?, ?);");
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.setString(3, user.getFirstName());
			stmt.setString(4, user.getLastName());
			stmt.setString(5, user.getEmail());
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import beans.UserBean;
import util.DBConnection;

public class SignupDao {
	private static Logger LOGGER = Logger.getLogger(SignupDao.class.getName());
	
	public static boolean createUser(UserBean userBean) throws Exception{
		try {
			Connection connection = DBConnection.getConnection();
			System.out.println(connection);
			String query = "INSERT INTO user (username, email, password, firstname, lastname) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			//de waarden die door de servlet in userBean worden gestopt worden hier opgehaald om ze in de database te kunnen zetten
			preparedStatement.setString(1,  userBean.getUserName());
			preparedStatement.setString(2,  userBean.getEmail());
			preparedStatement.setString(3,  userBean.getPassword());
			preparedStatement.setString(4,  userBean.getFirstName());
			preparedStatement.setString(5,  userBean.getLastName());
			preparedStatement.executeUpdate();
			return true;
		}catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Error while signing up" + e.getMessage());
			throw new Exception("Error while signing in.");
		}
	}
	
}

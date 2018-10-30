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
	
	public static UserBean createUser(UserBean userBean) throws Exception{
		try {
			Connection connection = DBConnection.getConnection();
			System.out.println(connection);
			String query = "INSERT INTO user (username, email, password, firstname, lastname) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1,  userBean.getUserName());
			preparedStatement.setString(2,  userBean.getEmail());
			preparedStatement.setString(3,  userBean.getPassword());
			preparedStatement.setString(2,  userBean.getFirstName());
			preparedStatement.setString(2,  userBean.getLastName());
			ResultSet resultSet = preparedStatement.executeQuery();
			
		}catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Error while logging in" + e.getMessage());
		}
		throw new Exception("Error while logging in.");
	}
	
}

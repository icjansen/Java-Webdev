package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import beans.LoginBean;
import beans.UserBean;
import util.DBConnection;

public class LoginDao {
	private static Logger LOGGER = Logger.getLogger(LoginDao.class.getName());

	public static UserBean authenticateUser(LoginBean loginBean) throws Exception {
		try {
			Connection connection = DBConnection.getConnection();
			System.out.println(connection);
			//alle data die bij het ingevoerde emailadres horen worden opgehaald
			String query = "SELECT username, email, password FROM user WHERE email = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, loginBean.getEmail());
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				//hier worden het ingevoerde wachtwoord en het wachtwoord uit de database vergeleken, als deze overeenkomen wordt de gebruikersnaam in userBean gezet
				if (loginBean.getPassword().equals(resultSet.getString("password"))) {
					UserBean userBean = new UserBean();
					userBean.setUserName(resultSet.getString("username"));
					System.out.println(resultSet.getString("username"));
					// userBean.setEmail(loginBean.getEmail());
					System.out.println(userBean.getUserName());
					return userBean;
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Error while logging in" + e.getMessage());
		}
		throw new Exception("Error while logging in.");
	}
}
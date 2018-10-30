package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import beans.UserBean;
import util.DBConnection;

public class UserDao {
	private static Logger LOGGER = Logger.getLogger(UserDao.class.getName());
	  public static ArrayList<UserBean> getUsers() throws Exception {
	    ArrayList<UserBean> list = new ArrayList<UserBean>();
	    try {
	      Connection connection = DBConnection.getConnection();
	      String query = "SELECT * FROM users LIMIT 0,100";
	      PreparedStatement preparedStatement = connection.prepareStatement(query);
	      ResultSet resultSet = preparedStatement.executeQuery();
	      while (resultSet.next()) {
	          UserBean userBean = new UserBean();
	          userBean.setUserName(resultSet.getString("Username"));
	          userBean.setId(resultSet.getInt("Id"));
	          list.add(userBean);
	      }
	      LOGGER.log(Level.INFO, "Retrieved " + list.size() + " users from database.");
	      return list;
	    } catch (SQLException e) {
	         LOGGER.log(Level.WARNING, "Error while retrieving " + " users: " + e.getMessage());
	   }
	  throw new Exception("Error while retrieving users.");
	 }
}

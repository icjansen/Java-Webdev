package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UserBean;
import dao.UserDao;
import util.DBConnection;


//we waren er nog niet aan toegekomen om een admin-page te maken, deze servlet is op dit moment dus nog nutteloos

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  private static Logger LOGGER = Logger.getLogger(AdminServlet.class.getName());
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    LOGGER.log(Level.INFO, "Admin servlet requested (get).");
    doPost(request, response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
////		doGet(request, response);
//		Connection conn = DBConnection.getConnection();
////		System.out.println("Connectie: " + conn);
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    LOGGER.log(Level.INFO, "Admin servlet requested (post).");
	    // Get list of users
	    List<UserBean> list;
	    try {
	       list = UserDao.getUsers();
	       request.setAttribute("listUsers", list);
	    } catch (Exception e) {
	       System.out.println("Error while getting userlist: " + e.getMessage());
	    }
	    // Forward the request
	    request.getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
	  }
}

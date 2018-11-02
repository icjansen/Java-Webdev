package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LoginBean;
import beans.UserBean;
import dao.LoginDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// Forward to the login form with an empty loginBean object.
		request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		

		//hier worden de in het formulier ingevoerde waarden in loginBean gestopt, voor gebruik in de LoginDao
		LoginBean loginBean = new LoginBean();
		loginBean.setEmail(request.getParameter("email").toLowerCase());
		loginBean.setPassword(request.getParameter("password"));
	
		UserBean userBean = null;
		try {
			userBean = LoginDao.authenticateUser(loginBean);
			// Store userBean in session
			//als de gebruiker in LoginDao geverifieerd is, wordt de gebruiker hier in de sessie gezet en doorgestuurd naar de index
			HttpSession session = ((HttpServletRequest) request).getSession(true);
			session.setAttribute("userBean", userBean);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("niet goed gegaan");
			request.setAttribute("errorMessage", "Email or password unknown  - please try again.");

			request.setAttribute("loginBean", loginBean);

			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}

	}

}

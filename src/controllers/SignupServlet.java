package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBean;
import dao.SignupDao;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("WEB-INF/signup.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		//na het verzenden van het ingevulde formulier worden de waarden uit dit formulier in userBean gezet
		UserBean userBean = new UserBean();
		userBean.setUserName(request.getParameter("username"));
		userBean.setEmail(request.getParameter("email"));
		userBean.setPassword(request.getParameter("password"));
		userBean.setFirstName(request.getParameter("firstname"));
		userBean.setLastName(request.getParameter("lastname"));
		
		try {
			//de gebruiker wordt gemaakt en meteen in de sessie gezet, daarna wordt de gebruiker doorgestuurd naar de index
			boolean result = SignupDao.createUser(userBean);
			System.out.println(result);
			HttpSession session = ((HttpServletRequest) request).getSession(true);
			session.setAttribute("userBean", userBean);
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Registreren niet gelukt.");
		}
	}

}

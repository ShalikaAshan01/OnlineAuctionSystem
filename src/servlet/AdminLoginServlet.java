package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AdministratorAccount;
import service.PersonServiceImpl;
import util.DBConnectionUtil;

/**
 * Servlet implementation class AdminLoginServlet
 */
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Admin/adminLoginView.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		AdministratorAccount user = null;
		boolean hasError = false;
		String errorString = null;
		
		if(userName == null || password == null || userName.length() == 0) {
			hasError = true;
			errorString = "Required username and password!";
		}
		else {
			user = new AdministratorAccount();
			user.setUserName(userName);
			user.setPassword(password);
			try {
				Connection conn =DBConnectionUtil.getDBConnection();
				user = PersonServiceImpl.findUser(conn, userName, password);
				if(user == null) {
					hasError = true;
					errorString = "User Name or password invalid";
				}
			}catch(SQLException|ClassNotFoundException e) {
				e.printStackTrace();
				hasError = true;
				errorString = e.getMessage();
			}
		}
		if(hasError) {
			
			request.setAttribute("errorString", errorString);
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/Admin/adminLoginView.jsp");
			dispatcher.forward(request, response);
		}
		
		else {
			HttpSession session = request.getSession();
			session.setAttribute("admin", user);
			response.sendRedirect(request.getContextPath() + "/adminHome");
		}
	}

}

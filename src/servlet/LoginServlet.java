package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PersonModel;
import service.PersonService;
import service.PersonServiceImpl;
/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * redirect user if request method is post
		 */
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/UserloginView.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session =request.getSession();
		session.removeAttribute("errorMessage");
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		final String  errorMessage= "<div style=\"text-align: center\"><i class=\"fa fa-warning\" style=\"color:red\"></i>&nbsp;Invalid Login,Please try again</div>";
		
		
		/*which link user clicked*/
		String from = (String) session.getAttribute("from");
		
		/*
		 *check user clicked link null or not if it is null link set to homepage 
		 */
		if(from == null) {
			from = "index.jsp";
		}else {
			session.removeAttribute("from");
		}
		if(username != null && password != null ) {
			 PersonService user = new PersonServiceImpl();
			 PersonModel loggedinUser;
			 /*
			  * assign database result to Loggedin user object
			  */
			 loggedinUser = user.login(username, password);
			 if(loggedinUser != null) {
				 	/**if loginnedUser object not null,then store user details into session attribute */
					session.setAttribute("loggedinUser", loggedinUser);
					response.sendRedirect(from);
					//RequestDispatcher dispatcher = request.getRequestDispatcher(from);
		            //dispatcher.forward( request, response);
			 }else {
				 /* if loginnedUser object is null forward error message**/
				 request.setAttribute("errorMessage", errorMessage);
				 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/UserloginView.jsp");
		         dispatcher.forward( request, response);
			 }	 
		 }
		
		
	}
}
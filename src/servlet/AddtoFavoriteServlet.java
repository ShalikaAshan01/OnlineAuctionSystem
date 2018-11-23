package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ProductService;
import service.ProductServiceImpl;

/**
 * Servlet implementation class AddtoFavoriteServlet
 */
public class AddtoFavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddtoFavoriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cidtxt = request.getParameter("cid");
		String pidtxt = request.getParameter("pid");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		try {
			int pid = Integer.parseInt(pidtxt);
			int cid = Integer.parseInt(cidtxt);
			/*if user id=0(it mean he is not login to the site)
			 *Redirect to login page  
			 **/
			if(cid==0) {
				session.setAttribute("from", "/view?pid="+pid);
				response.sendRedirect("login");
			}
			
			ProductService favorite = new ProductServiceImpl();
			int result = favorite.addtofavorite(cid, pid);
			/**
			 * diplay alert
			 */
			if(result>0) {
				response.sendRedirect("/OnlineAuctionSystem/view?pid="+pid);
				//RequestDispatcher dispatcher = request.getRequestDispatcher("/view?pid="+pid);
				//dispatcher.forward( request, response);
			}else {
				 out.println("<script type='text/javascript'>");
				 out.print("alert('Something went to wrong');");
				 out.println("location='index.jsp';");
				 out.println("</script>");
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Messages/productNotFound.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

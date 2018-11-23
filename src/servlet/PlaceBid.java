package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PersonModel;
import service.ProductService;
import service.ProductServiceImpl;

/**
 * Servlet implementation class PlaceBid
 */
public class PlaceBid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceBid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * if requested method is get redirect to home page
		 */
		RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String pidtxt = request.getParameter("pid");
		String bidpricetxt = request.getParameter("bidPrice");
		String oldBidtxt = request.getParameter("oldbid");
		PersonModel loggedinPerson;
		String url = "/view?pid=" + pidtxt;
		
		try {
			Integer pid = Integer.parseInt(pidtxt);
			float bidprice = Float.parseFloat(bidpricetxt);
			float oldbid = Float.parseFloat(oldBidtxt);
			if(null !=session.getAttribute("loggedinUser")){
				loggedinPerson= (PersonModel) session.getAttribute("loggedinUser");
				ProductService placebid = new ProductServiceImpl();
				
				if(oldbid<bidprice) {
					int result[] = placebid.placebid(pid, bidprice, loggedinPerson.getCid());
					
					/*
					 * check bid is placed or not and redirect
					 * */
					if(result[0]>0 && result[1]>0) {
						 response.sendRedirect("/OnlineAuctionSystem/"+url);
						//RequestDispatcher dispatcher = request.getRequestDispatcher(url);
						//dispatcher.forward(request, response);
					}else {
						RequestDispatcher dispatcher = request.getRequestDispatcher(url);
						dispatcher.forward( request, response); 
					}
					
				}else {
					RequestDispatcher dispatcher = request.getRequestDispatcher(url);
					dispatcher.forward( request, response); 
				}
			}else {
				session.setAttribute("from", url);
				response.sendRedirect("login");
			}
			
		}catch(NumberFormatException e) {
			 RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			 dispatcher.include( request, response); 
		}
		
	}

}

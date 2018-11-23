package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PersonModel;
import model.ProductModel;
import service.ProductService;
import service.ProductServiceImpl;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/view")
public class ProductViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * view user selected product
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pid = request.getParameter("pid");
		HttpSession session = request.getSession();
		session.removeAttribute("SelectedProduct");
		session.removeAttribute("maxbid");
		PersonModel loggedinPerson;

		
		try {
			int id = Integer.parseInt(pid);
			
			ProductService product = new ProductServiceImpl();
			ProductModel details = new ProductModel();
			details = product.selectByID(id);
			int maxcid = product.getMaxBidAmount(id);
			/*
			 * check product result is null or not
			 * */
			if(details !=null) {
				
				
				if(null !=session.getAttribute("loggedinUser")){
					 loggedinPerson= (PersonModel) session.getAttribute("loggedinUser");
					 	 
					 	 if(loggedinPerson.getCid()==details.getWinner()) {
					 		 request.setAttribute("won", "true");
					 	 }
					 	 /*
						 *if seller id and customer id equals it will redirect to added product page 
						 */
					 	if(details.getSellerId() == loggedinPerson.getCid()) {
					 		request.setAttribute("SelectedProduct", details);
							RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Product/sellerProductViewjsp.jsp");
							rd.forward(request, response);
						}
					 	/*
					 	 * if maximum amount of the bid have been placed by this customer
					 	 * show message
					 	 */
					 	if(maxcid == loggedinPerson.getCid()) {
					 		request.setAttribute("maxbid", "true");
					 	}
						
				}
				/*
				 * if product is suspend request forward to another page 
				 */
				if(details.getStatus().equals("suspend")) {
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Messages/productNotFound.jsp");
					rd.forward(request, response);
				}
				/**
				 * Display product details
				 */
				
				request.setAttribute("SelectedProduct", details);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Product/productView.jsp");
				rd.forward(request, response);
				
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/Messages/productNotFound.jsp");
				rd.forward(request, response);
			}
		}catch(NumberFormatException e){
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
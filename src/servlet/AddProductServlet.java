package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PersonModel;
import model.ProductModel;
import service.ProductService;
import service.ProductServiceImpl;

/**
 * Servlet implementation class AddProductServlet
 */
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
    	rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pname = request.getParameter("name");
		String category = request.getParameter("category");
		String bidPeriod = request.getParameter("bidPeriod");
		String condition = request.getParameter("condition");
		String price = request.getParameter("price");
		String brand = request.getParameter("brand");
		String model = request.getParameter("model");
		String description = request.getParameter("description");
		String seller = request.getParameter("seller");
		String addedDate;
		String addedTime;
		DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDateTime today = LocalDateTime.now();
		addedDate = date.format(today);
		addedTime = time.format(today);
		boolean result = true;
		int retvalue = 0;
		PrintWriter out = response.getWriter();
		/*
		 * validate inputs
		 */
		if(pname.isEmpty() || pname==null) {
			result = false;
		}
		if(bidPeriod.isEmpty() || bidPeriod==null) {
			result = false;
		}
		if(price.isEmpty() || price==null) {
			result = false;
		}
		if(brand.isEmpty()|| brand==null) {
			result = false;
		}
		if(model.isEmpty() || model == null) {
			result = false;
		}
		if(description.isEmpty() || model == null) {
			result = false;
		}
		if(result){
			try {
				ProductModel product = new ProductModel();
				product.setPname(pname);
				product.setCategory(category);
				product.setPcondition(condition);
				product.setBrand(brand);
				product.setModel(model);
				product.setDescription(description);
				product.setAddedTime(addedTime);
				product.setAddeddate(addedDate);
				product.setSellerId(Integer.parseInt(seller));
				product.setLastBidPrice(Float.parseFloat(price));
				product.setBidPeriod_days(Integer.parseInt(bidPeriod));
				
				ProductService add = new ProductServiceImpl();
				retvalue = add.addProduct(product);
			}catch(NumberFormatException e) {
				response.sendRedirect("addProduct");
			}
		}
		else {
			response.sendRedirect("index.jsp");
		}
		/**
		 * redirect to homepage
		 */
		if(retvalue > 0) {
			response.sendRedirect("index.jsp");
		}else {
			response.sendRedirect("index.jsp");
		}
		
		
	}
}

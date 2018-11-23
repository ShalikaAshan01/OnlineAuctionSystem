package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProductService;
import service.ProductServiceImpl;

/**
 * Servlet implementation class payment
 */
public class payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public payment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pidtxt = request.getParameter("pid");
		String cidtxt = request.getParameter("cid");
		String pricetxt = request.getParameter("price");
		PrintWriter out = response.getWriter();
		try {
			
			int pid = Integer.parseInt(pidtxt);
			int cid = Integer.parseInt(cidtxt);
			double price = Double.parseDouble(pricetxt);
			ProductService pay = new ProductServiceImpl();
			int result = pay.makePayment(pid, cid, price);
			if(result > 0) {
				out.println("<script type='text/javascript'>");
				 out.print("alert('Your order has been placed');");
				 out.println("location='index.jsp';");
				 out.println("</script>");
				 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/checkout.jsp");
				 dispatcher.include( request, response);
				
			}else {
				 out.println("<script type='text/javascript'>");
				 out.print("alert('Payment not completed');");
				 out.println("location='index.jsp';");
				 out.println("</script>");
				 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/checkout.jsp");
				 dispatcher.include( request, response);
			}
			
		}catch(NumberFormatException e){
  			 out.println("<script type='text/javascript'>");
			 out.print("alert('Payment not completed');");
			 out.println("location='index.jsp';");
			 out.println("</script>");
			 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/checkout.jsp");
			 dispatcher.include( request, response);
		}
	}

}

package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ProductService;
import service.ProductServiceImpl;

/**
 * Servlet implementation class updateProductByAdmin
 */
@WebServlet("/updateProductByAdmin")
public class updateProductByAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateProductByAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductService update =  new ProductServiceImpl();
		PrintWriter out = response.getWriter();
		String pidtxt = request.getParameter("pid");
		try {
			int pid = Integer.parseInt(pidtxt);
			int result = update.updateProductByAdmin(pid);
			if(result > 0) {
				
				 out.println("<script type='text/javascript'>");
				 out.print("alert('Product Added successfully');");
				 out.println("location='adminshowallproduct';");
				 out.println("</script>");
				 RequestDispatcher dispatcher = request.getRequestDispatcher("adminshowallproduct");
				 dispatcher.include( request, response);
				 
			}else {
				
				 out.println("<script type='text/javascript'>");
				 out.print("alert('Cannot Add product');");
				 out.println("location='adminshowallproduct';");
				 out.println("</script>");
				 RequestDispatcher dispatcher = request.getRequestDispatcher("adminshowallproduct");
				 dispatcher.include( request, response);
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
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

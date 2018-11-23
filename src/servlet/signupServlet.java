package servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class signup
 */
public class signupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public boolean error;

    /**
     * Default constructor. 
     */
    public signupServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/signUpView.jsp");
		 dispatcher.forward( request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.removeAttribute("unregUser");
		PersonModel per = new PersonModel();
		per.setFname(request.getParameter("firstname").trim());
		
		per.setLname(request.getParameter("lastname").trim());
		
		per.setEmail(request.getParameter("email").trim());
		
		per.setAddrl1(request.getParameter("addressline1").trim());
		
		per.setAddrl2(request.getParameter("addressline2").trim());
		
		per.setAddrl3(request.getParameter("addressline3").trim());
		
		per.setDob(request.getParameter("dob").trim());
		
		String phone = request.getParameter("phoneNumber").trim();
		
		per.setGender(request.getParameter("gender"));
		
		per.setDob(request.getParameter("dob"));
		
		/*validate phone number*/
		 try {
			Integer number = Integer.parseInt(phone);
			if(phone.length()==10) {
				per.setPhone(phone);
				error=false;
			}else {
				error = true;
				per.setPhone("");
				request.setAttribute("phoneError", "*Phone numer length is invalid");
			}
		 }catch (NumberFormatException e) {
			 per.setPhone("");
			 error = true;
			 request.setAttribute("phoneError", "*Enter a valid phone number");
		}
		
		
				 if(per.getFname().isEmpty() || null == per.getFname() ) {
					 request.setAttribute("FnameError", "*First Name is Required");
					 error = true;
				 }
				 
				 if(per.getLname().isEmpty() || null == per.getLname() ) {
					 request.setAttribute("LnameError", "*Last Name is Required");
					 error = true;
				 }
				 
				 if(per.getEmail().isEmpty() || null == per.getEmail() ) {
					 request.setAttribute("EmailError", "*Email is Required");
					 error = true;
				 }
				 
				 if(per.getAddrl1().isEmpty() || null == per.getAddrl1() ) {
					 request.setAttribute("AddressError1", "*Address Line 1 is Required");
					 error = true;
				 }
				 
				 if(per.getAddrl2().isEmpty() || null == per.getAddrl2() ) {
					 request.setAttribute("AddressError2", "*Address Line 2 is Required");
					 error = true;
				 }
				 
				 if(per.getAddrl3().isEmpty() || null == per.getAddrl3() ) {
					 request.setAttribute("AddressError3", "*Address Line 3 is Required");
					 error = true;
				 }
				 
				/*
				 * validate passwords 
				 */
				String password = request.getParameter("password").trim();
				
				String conpassword = request.getParameter("conpassword").trim();
				
				boolean passError = false;
				
				String alert ="";
				
				PrintWriter out = response.getWriter();
				
				if(password.length()<8) {
		            passError = true;
		            alert = "alert('Password must be at least 8 characters');";
				}else if(!password.equals(conpassword) ){
					passError = true;
					alert = "alert('Password and Confirm Password does not match');";
				}
				per.setPassword(password);
				
				 /*
				  * if user inputs are not valid show errors using session attributes
				  */
				 if(error) {
					 request.setAttribute("unregUser", per);
					 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/signUpView.jsp");
					 dispatcher.forward( request, response);
				 }
				 /*
					 * show alert if password doesn't meet minimum requirement
					 */
				 else if(passError) {
							error = true;
							request.setAttribute("unregUser", per);
							out.println("<script type='text/javascript'>");
							out.print(alert);
							out.println("location='#';");
							out.println("</script>");
							RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/signUpView.jsp");
							dispatcher.include( request, response);
						}
				 else{
					 session.removeAttribute("unregUser");
					 PersonService account = new PersonServiceImpl();
					 int result =account.SignUP(per);
					 if(result>0) {
						 session.setAttribute("loggedinUser", per);
						 out.println("<script type='text/javascript'>");
						 out.print("alert('Your Accoun has been created Successfully');");
						 out.println("location='index.jsp';");
						 out.println("</script>");
					 }else {
						 out.println("<script type='text/javascript'>");
						 out.print("alert('Somthing going to Wrong');");
						 out.println("location='index.jsp';");
						 out.println("</script>");
						 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/signUpView.jsp");
						 dispatcher.include( request, response); 
					 }
				 }
		}
		
	}
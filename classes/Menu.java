/*  Login.java
    Sample login servlet
    Alan Riggins    
    CS645
    Spring 2017
 */

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import sdsu.*;



public class Menu extends HttpServlet { 
          
    public void doPost(HttpServletRequest request,
              HttpServletResponse response)
                        throws IOException, ServletException  {
        processRequest(request, response);         
        }

    public void doGet(HttpServletRequest request,
              HttpServletResponse response)
                        throws IOException, ServletException  { 
        //throw new ServletException("GET protocol is not supported, POST only");
		processRequest(request, response);         
        } 
        
    private void processRequest(HttpServletRequest request,
              HttpServletResponse response) 
                        throws IOException, ServletException {
        String toDo= "";
        /*if(!request.getMethod().equals("POST")) {
            response.sendRedirect("/jadrn050/jsp/login_err.jsp"); 
            return;
            }  */   
        String skuinput = (String) request.getParameter("skuinput");
        String date = (String) request.getParameter("adddate");
		int quantity = Integer.parseInt( request.getParameter("addquantity"));    
		Vector<String[]> skuData=PasswordUtilities.isValidSKU(skuinput);
		HttpSession session = request.getSession(false);
        if(skuData != null) {
			
			if(PasswordUtilities.addInData(skuinput,date,quantity)){
				session.setAttribute("message","Successful result from database");
			}
			else{
				session.setAttribute("message","Error inserting Data");
			}
            toDo = "/WEB-INF/jsp/menu.jsp";
	    //toDo = "/jsp/menu.jsp";
            //HttpSession session = request.getSession(true);
            session.setAttribute("sku", skuData.elementAt(0)[0]); 
	    //session.setAttribute("message","Successful result from database");   
	    //response.sendRedirect("/jadrn050/jsp/menu.jsp");                     
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(toDo); 
            dispatcher.forward(request, response);  
        }
		else{
			session.setAttribute("skumessage","Enter the existing SKU");
			toDo = "/WEB-INF/jsp/menu.jsp";
			//response.sendRedirect("/jadrn050/jsp/menu.jsp"); 
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(toDo); 
            dispatcher.forward(request, response);
			//response.sendRedirect("/jadrn050/jsp/login_err.jsp"); 
            //return;
		}
		
    }      
}




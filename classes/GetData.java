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




public class GetData extends HttpServlet { 
          
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
        System.out.println(skuinput);		
		Vector<String[]> skuData=PasswordUtilities.isValidSKU(skuinput);
		System.out.println(skuData);
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.setHeader("Cache-control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "-1");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");
        response.setHeader("Access-Control-Max-Age", "86400");
        if(skuData != null) {
			
		//String sku = request.getParameter("sku");

        
	   String[] objs = skuData.get(0);
	   
	   objs[1]=PasswordUtilities.getCategory(Integer.parseInt(objs[1]));
	   objs[2]=PasswordUtilities.getVendor(Integer.parseInt(objs[2]));
	   String jsons="";
for (String o : objs) {
    jsons+=o;
	jsons+=",";
}


	out.print(jsons);


        out.close();
			

        }
		else{
			
			//session.setAttribute("skumessage","Enter the existing SKU");
			out.print("Enter a Valid SKU");
			out.close();
			/*toDo = "/WEB-INF/jsp/menu.jsp";
			//response.sendRedirect("/jadrn050/jsp/menu.jsp"); 
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(toDo); 
            dispatcher.forward(request, response);
			//response.sendRedirect("/jadrn050/jsp/login_err.jsp"); 
            //return;*/
		}
		
    }      
}




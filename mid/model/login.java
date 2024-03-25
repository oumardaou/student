package auca.model;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class login extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	

        String id = request.getParameter("id");
        String password = request.getParameter("password"); 

        String role = request.getParameter("role");

        if("admin".equalsIgnoreCase(role)) {
            response.sendRedirect("Research.html"); 
        } else if("student".equalsIgnoreCase(role)) {
            response.sendRedirect("academic.html"); // Redirects to the student page
        } else if("teacher".equalsIgnoreCase(role)) {
            response.sendRedirect("teacher.html"); // Redirects to the home page
        } else {
            response.getWriter().println("Invalid role. Please enter a valid role.");
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Redirect GET requests to the doPost method
        doPost(request, response);
    }
}

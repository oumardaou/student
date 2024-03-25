package auca.model;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {

   String DB_URL = "jdbc:postgresql://localhost:5433/Auca";
 String USER = "postgres";
  String PASS = "Omo99707199";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implement the logic to insert new data
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO student (student_id, first_name, last_name, date_of_birth) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, Integer.parseInt(request.getParameter("student_id")));
            stmt.setString(2, request.getParameter("first_name"));
            stmt.setString(3, request.getParameter("last_name"));
            stmt.setString(4, request.getParameter("date_of_birth"));
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implement the logic to retrieve and display data
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM student WHERE student_id = ?");
            stmt.setInt(1, Integer.parseInt(request.getParameter("student_id")));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                response.getWriter().println("Student ID: " + rs.getInt("student_id"));
                response.getWriter().println("First Name: " + rs.getString("first_name"));
                response.getWriter().println("Last Name: " + rs.getString("last_name"));
                response.getWriter().println("Date of Birth: " + rs.getDate("date_of_birth"));
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implement the logic to update existing data
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("UPDATE student SET first_name = ?, last_name = ?, date_of_birth = ? WHERE student_id = ?");
            stmt.setString(1, request.getParameter("first_name"));
            stmt.setString(2, request.getParameter("last_name"));
            stmt.setDate(3, java.sql.Date.valueOf(request.getParameter("date_of_birth")));
            stmt.setInt(4, Integer.parseInt(request.getParameter("student_id")));
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Implement the logic to delete data
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM student WHERE student_id = ?");
            stmt.setInt(1, Integer.parseInt(request.getParameter("student_id")));
            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

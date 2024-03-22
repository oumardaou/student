package auca.model;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class StudentServlet extends HttpServlet {
    private final String DB_URL = "jdbc:postgresql://localhost:5433/postgres";
    private final String USER = "postgres";
    private final String PASS = "Omo99707199";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve student(s) from the database
        String studentId = request.getParameter("id");
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            ResultSet rs;
            if (studentId != null) {
                // Retrieve a single student's details
                String sql = "SELECT * FROM students WHERE id = " + studentId;
                rs = stmt.executeQuery(sql);
                // Process and return the student's details
            } else {
                // Retrieve all students' details
                String sql = "SELECT * FROM students";
                rs = stmt.executeQuery(sql);
                // Process and return the list of students
            }
            // Convert ResultSet to the desired format and send it in the response
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Register a new student in the database
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String sql = "INSERT INTO students (name, email) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.executeUpdate();
            // Acknowledge the registration
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Update an existing student's details in the database
        String studentId = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String sql = "UPDATE students SET name = ?, email = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, Integer.parseInt(studentId));
            pstmt.executeUpdate();
            // Acknowledge the update
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Delete a student's details from the database
        String studentId = request.getParameter("id");
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, Integer.parseInt(studentId));
            pstmt.executeUpdate();
            // Acknowledge the deletion
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database errors
        }
    }
}

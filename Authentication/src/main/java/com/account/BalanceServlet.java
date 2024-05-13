package com.account;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.*;

/**
 * Servlet implementation class LoginServlet
 */
public class BalanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		
		
		String accno = request.getParameter("accno");
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, "system", "2860");
			
			String query = "select * from accounts where accno=? ";
			PreparedStatement ps = con.prepareStatement(query);
			
			
			ps.setString(1, accno);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				String username = rs.getString("username");
				String balanceValue = rs.getString("balance");
				out.println("<h2>Name: " + username + "</h2>");
				out.println("<h2>Balance: " + balanceValue + "</h2>");
				
			}
			else {
				out.println("<h4>Login Failed</h4>");
			}
		}
		catch(Exception e) {
			out.println("<h4 style='color:red'> Exception : "+e.getMessage()+"</h4>");
			RequestDispatcher rd = request.getRequestDispatcher("register.html");
			rd.include(request, response);
		}

	}

	

}

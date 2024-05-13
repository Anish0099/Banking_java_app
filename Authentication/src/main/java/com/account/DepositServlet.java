package com.account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DepositServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int user= Integer.parseInt(request.getParameter("user"));
		String amount = request.getParameter("amount");
		
		
		String driver= "oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		try {
			Class.forName(driver);
			Connection con= DriverManager.getConnection(url, "system", "2860");
			String query="update accounts set balance = balance + ? where accno = ?";
			PreparedStatement ps= con.prepareStatement(query);
			ps.setString(1, amount);
			ps.setInt(2, user);
			int count=ps.executeUpdate();
			if(count==1) {
				
				out.println("<h1>Successful deposited<h1>");
				out.println("<a href='home.html'>Back to home</a>");
			}
			else{
				out.println("<h1>Not Successful deposited<h1>");
			}
			
		}catch(Exception e) {
			System.out.println("Exception: " + e);
		}
		
		
		
		
	}

}

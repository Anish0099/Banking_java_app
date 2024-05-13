package com.account;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.sql.Connection;
import java.sql.*;

/**
 * Servlet implementation class RegisterServlet
 */
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int accno= Integer.parseInt(request.getParameter("accno"));
		String username= request.getParameter("user");
		String balance= request.getParameter("balance");
		
		String driver= "oracle.jdbc.driver.OracleDriver";
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
			try {
				Class.forName(driver);
				Connection con= DriverManager.getConnection(url, "system", "2860");
				String query ="insert into accounts values(?,?,?)";
				PreparedStatement ps= con.prepareStatement(query);
				ps.setInt(1, accno);
				ps.setString(2, username);
				ps.setString(3, balance);
				int count=ps.executeUpdate();
				if(count==1) {
					
					out.println("<h1>Successful<h1>");
				}
				else{
					out.println("<h1>Not Successful<h1>");
				}
				
			}
			catch(Exception e)
			{
				System.out.println("Exception"+e);
			
			}
		
		
	}

}
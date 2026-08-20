import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
@WebServlet("/registerservlet")
public class registerservlet extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		
		String userEmail = req.getParameter("email");
		String userPassword = req.getParameter("password");
		String userFullName = req.getParameter("fullname");
		String userGender = req.getParameter("gender");
		String userAddress = req.getParameter("address");
		String userCity = req.getParameter("city");
		String userCountry = req.getParameter("country");
		
		String date = req.getParameter("date");
		String month = req.getParameter("month");
		String year = req.getParameter("year");
		
		String dob = year + "-" + month + "-" + date;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ass2", "root", "Golu");
			
			PreparedStatement pst = con.prepareStatement("SELECT email FROM register WHERE email = ? AND password=?");
			pst.setString(1, userEmail);
			pst.setString(2, userPassword);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				// Email already exists -> create session and go to dashboard
//				HttpSession session = req.getSession(true);
//				session.setAttribute("fullname",rs.getString("fullname"));
				res.sendRedirect("dashboard");
			} else {
				PreparedStatement ps = con.prepareStatement("INSERT INTO register (email, password, fullname, gender, address, city, country, dob) VALUES (?,?,?,?,?,?,?,?)");
				ps.setString(1, userEmail);
				ps.setString(2, userPassword);
				ps.setString(3, userFullName);
				ps.setString(4, userGender);
				ps.setString(5, userAddress);
				ps.setString(6, userCity);
				ps.setString(7, userCountry);
				ps.setString(8, dob);
				
				
				
				int i = ps.executeUpdate();
				if (i > 0) {
//					HttpSession session = req.getSession(true);
//					session.setAttribute("fullName", userFullName);
					out.print("<h2><center>Register Succsessfully</h2></center>");
					out.print("<h3><center>Welcome "+userFullName+"</h3></center>");
//					req.getRequestDispatcher("login.html").include(req, res);
					out.print("<a href='login.html'> Click here to login</a>");
				} else {
//					res.sendRedirect("register.html");
					out.print("<h3> Registration failed Please try again.</h3>");
					req.getRequestDispatcher("register.html").include(req, res);
				}
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
//			res.sendRedirect("login.html");
			out.print("<h3>Error"+e.getMessage()+"</h3>");
		}
	}
}

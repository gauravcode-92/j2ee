import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@SuppressWarnings("serial")
@WebServlet("/loginservlet")

public class loginservlet extends HttpServlet 
{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String userEmail=req.getParameter("email");
		String userPassword=req.getParameter("password");
		
		try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost/ass2","root","Golu");
				out.print("Connected");
				PreparedStatement pst=con.prepareStatement("SELECT * from register where email=?");
				pst.setString(1, userEmail);
				ResultSet rs=pst.executeQuery();
				
				if(rs.next())
				{
					String pass=rs.getString("password");
					
					if(pass.equals(userPassword))
					{
						HttpSession ses=req.getSession(true);
						String username=rs.getString("fullname");
						ses.setAttribute("fullname", username);
						res.sendRedirect("dashboard");
					}
					else
					{
						out.print("<h3> Invalid Email or Password </h3>");
						req.getRequestDispatcher("login.httml").include(req, res);
						
					}
				}
				else 
				{
						out.print("<h3> Invalid Email or Passworrd </h3>");
						req.getRequestDispatcher("login.html").include(req, res);
				}
			
				con.close();
				
				
				
			}
		catch(Exception e)
		{
			out.print(e);
			e.printStackTrace();
			out.print("<h4> An error. Please try again </h4>");
			req.getRequestDispatcher("login.html").include(req,res);
		}
		
	}

}

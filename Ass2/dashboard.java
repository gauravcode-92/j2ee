import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@SuppressWarnings("serial")
@WebServlet("/dashboard")
public class dashboard extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("fullname") != null) {
			String name = (String) session.getAttribute("fullname");
			out.print("<h2>Welcome back, " + name + "</h2>");
			out.print("<a href='logout'><button>Logout</button></a>");
		} else {
			response.sendRedirect("login.html");
		}
	}
}

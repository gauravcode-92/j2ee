import java.oi.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/logout")

public class logout extends HttpServlet
{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
    {
        HttpSession ses=req.getSession(false);
        if(ses!=null)
        {
            ses.invalidate();
        }
        res.senfRedirect("login.html");
        
    }
}
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.sendRedirect("");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false); 
        if (session == null) {
            response.sendRedirect("login");
            return;
        }
        
        boolean isAdmin = Boolean.parseBoolean((String) session.getAttribute("isadmin"));
        if (!isAdmin) {
            response.sendRedirect("");
            return;
        }
        
        int userid = -1;
        try {
            userid = Integer.parseInt(request.getParameter("userid"));
        } catch (Exception e) {
            System.out.println(e);
        }
        
        if (userid == -1) {
            response.sendRedirect("");
            return;
        }
        
        boolean studentDeleted = StudentDao.deleteStudent(userid);
        if (!studentDeleted) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(Util.errorString("Could not be deleted."));
            return;
        }
        
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        if (page <= 0)
            response.sendRedirect("");
        
        response.sendRedirect("?page=" + page);
    }

}

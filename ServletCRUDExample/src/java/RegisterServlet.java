import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegisterServlet extends HttpServlet {
    protected void showRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(Util.boilerplateStart("Register"));
        request.getRequestDispatcher("nav-login.html").include(request, response);
        out.println("<div class='register-login-form'>");
        request.getRequestDispatcher("register.html").include(request, response);
        out.println("</div>");
        out.println(Util.boilerplateEnd());

    }
    
    protected void showRegister(HttpServletRequest request, HttpServletResponse response, String error)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(Util.boilerplateStart("Register"));
        request.getRequestDispatcher("nav-login.html").include(request, response);
        out.println("<div class='register-login-form'>");
        request.getRequestDispatcher("register.html").include(request, response);
        out.println(Util.errorString(error));
        out.println("</div>");
        out.println(Util.boilerplateEnd());

    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        showRegister(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        
        if (username.equals("")) {
            String error = "Username cannot be empty.";
            showRegister(request, response, error);
            return;
        }
        
        if (UserDao.checkUsername(username)) {
            String error = "Username already exists.";
            showRegister(request, response, error);
            return;
        }
        
        if (!password.equals(repassword)) {
            String error = "Passwords do not match.";
            showRegister(request, response, error);
            return;
        }
        
        boolean reg = UserDao.registerUser(username, password);
        if (!reg) {
            String error = "Unknown error occured.";
            showRegister(request, response, error);
            return;
        }
        
        HttpSession sess = request.getSession();
        sess.setAttribute("username", username);
        sess.setAttribute("userid", Integer.toString(UserDao.getUserID(username)));
        sess.setAttribute("isadmin", Boolean.toString(UserDao.isAdmin(username)));
        
        response.sendRedirect("");
    }
}

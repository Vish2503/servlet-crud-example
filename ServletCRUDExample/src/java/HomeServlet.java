import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomeServlet extends HttpServlet {
    protected void showHome(HttpServletRequest request, HttpServletResponse response, Student student)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(Util.boilerplateStart("Home"));
        request.getRequestDispatcher("nav.html").include(request, response);
        out.println(Util.studentTableBoilerplateStart(false));
        out.println(Util.studentRow(student, false, 0));
        out.println(Util.studentTableBoilerplateEnd());
        out.println(Util.boilerplateEnd());
    }
    
    protected void showAdmin(HttpServletRequest request, HttpServletResponse response, List<Student> students, int page)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(Util.boilerplateStart("Home"));
        request.getRequestDispatcher("nav.html").include(request, response);
        out.println(Util.studentTableBoilerplateStart(true));
        for (Student s: students)
            out.println(Util.studentRow(s, true, page));
        out.println(Util.studentTableBoilerplateEnd());
        out.println(Util.pagination(page));
        out.println(Util.boilerplateEnd());
    }
    
    protected void showHomeEdit(HttpServletRequest request, HttpServletResponse response, Student student)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(Util.boilerplateStart("Home"));
        request.getRequestDispatcher("nav.html").include(request, response);
        out.println(Util.studentTableBoilerplateStart(false));
        out.println(Util.studentRowEditable(student, false, 0));
        out.println(Util.studentTableBoilerplateEnd());
        out.println(Util.boilerplateEnd());
    }
    
    protected void showAdminEdit(HttpServletRequest request, HttpServletResponse response, List<Student> students, int userid, int page)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(Util.boilerplateStart("Home"));
        request.getRequestDispatcher("nav.html").include(request, response);
        out.println(Util.studentTableBoilerplateStart(true));
        for (Student s: students) {
            if (s.getUserid() == userid)
                out.println(Util.studentRowEditable(s, true, page));
            else
                out.println(Util.studentRow(s, true, page));
        }
        out.println(Util.studentTableBoilerplateEnd());
        out.println(Util.boilerplateEnd());
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); 
        if (session == null) {
            response.sendRedirect("login");
            return;
        }
        
        boolean isAdmin = Boolean.parseBoolean((String) session.getAttribute("isadmin"));
        if (isAdmin) {
            
            int page = 1;
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
            if (page <= 0)
                response.sendRedirect("");
            
            List<Student> students = StudentDao.getStudents(5, 5 * (page - 1));
            
            if (students == null) {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println(Util.errorString("Unknown Error Occured.")); 
                return;
            }
            
            showAdmin(request, response, students, page);
        } else {
            
            int userid = Integer.parseInt((String) session.getAttribute("userid"));
            Student student = StudentDao.getStudent(userid);

            if (student == null) {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println(Util.errorString("Unknown Error Occured.")); 
                return;
            }
            
            showHome(request, response, student);
        }  
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false); 
        if (session == null) {
            response.sendRedirect("login");
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
                
        boolean isAdmin = Boolean.parseBoolean((String) session.getAttribute("isadmin"));
        if (isAdmin) {
            
            int page = 1;
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
            if (page <= 0)
                response.sendRedirect("");
            
            List<Student> students = StudentDao.getStudents(5, 5 * (page - 1));
            
            if (students == null) {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println(Util.errorString("Unknown Error Occured.")); 
                return;
            }
            
            showAdminEdit(request, response, students, userid, page);
        } else {
            int actualUserid = Integer.parseInt((String) session.getAttribute("userid"));
            if (userid != actualUserid) {
                response.sendRedirect("");
                return;
            }
            
            Student student = StudentDao.getStudent(userid);

            if (student == null) {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println(Util.errorString("Unknown Error Occured.")); 
                return;
            }
            
            showHomeEdit(request, response, student);
        }
        
    }
}

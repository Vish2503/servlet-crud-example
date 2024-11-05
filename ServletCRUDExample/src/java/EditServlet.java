import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.sendRedirect("");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        
        Student student = StudentDao.getStudent(userid);
        
        String rollno = request.getParameter("rollno");
        if (rollno != null)
            student.setRollno(rollno);
        
        String name = request.getParameter("name");
        if (name != null)
            student.setName(name);

        String gender = request.getParameter("gender");
        if (gender != null)
            student.setGender(gender);

        String age = request.getParameter("age");
        if (age != null)
            student.setAgeFromString(age);

        String email = request.getParameter("email");
        if (email != null)
            student.setEmail(email);

        String mobile = request.getParameter("mobile");
        if (mobile != null)
            student.setMobile(mobile);

        String degree = request.getParameter("degree");
        if (degree != null)
            student.setDegree(degree);

        String batch = request.getParameter("batch");
        if (batch != null)
            student.setBatch(batch);

        String section = request.getParameter("section");
        if (section != null)
            student.setSection(section);

        String gpa = request.getParameter("gpa");
        if (gpa != null)
            student.setGpaFromString(gpa);
        
        boolean studentUpdated = StudentDao.updateStudent(student);
        if (!studentUpdated) {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(Util.errorString("Could not be updated."));
            return;
        }
        
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

            response.sendRedirect("?page=" + page);
        } else {
            response.sendRedirect("");
        }
    }
}

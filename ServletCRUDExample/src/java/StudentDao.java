import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    public static Connection getConnection() {
        Connection con = null;
        try {
            // https://stackoverflow.com/questions/51586401/glassfish-keystore-error-after-adding-mysql-connector
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_crud_example?autoReconnect=true&useSSL=false", "root", "root");
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    
    public static boolean insertStudent(Student student) {
        Connection con = null;
        try {
            con = StudentDao.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO student (userid, rollno, name, gender, age, email, mobile, degree, batch, section, gpa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, student.getUserid());
            ps.setString(2, student.getRollno());
            ps.setString(3, student.getName());
            ps.setString(4, student.getGender());
            ps.setInt(5, student.getAge());
            ps.setString(6, student.getEmail());
            ps.setString(7, student.getMobile());
            ps.setString(8, student.getDegree());
            ps.setString(9, student.getBatch());
            ps.setString(10, student.getSection());
            ps.setFloat(11, student.getGpa());
            
            int recordsAdded = ps.executeUpdate();
                        
            if (recordsAdded == 1)
               return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return false;
    }
    
    public static boolean updateStudent(Student student) {
        Connection con = null;
        try {
            con = StudentDao.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE student SET rollno = ?, name = ?, gender = ?, age = ?, email = ?, mobile = ?, degree = ?, batch = ?, section = ?, gpa = ? WHERE userid = ?");
            ps.setString(1, student.getRollno());
            ps.setString(2, student.getName());
            ps.setString(3, student.getGender());
            ps.setInt(4, student.getAge());
            ps.setString(5, student.getEmail());
            ps.setString(6, student.getMobile());
            ps.setString(7, student.getDegree());
            ps.setString(8, student.getBatch());
            ps.setString(9, student.getSection());
            ps.setFloat(10, student.getGpa());
            ps.setInt(11, student.getUserid());
            
            int recordsUpdated = ps.executeUpdate();
                        
            if (recordsUpdated == 1)
               return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return false;
    }
    
    public static boolean deleteStudent(int userid) {
        Connection con = null;
        try {
            con = StudentDao.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM student WHERE userid = ?");
            ps.setInt(1, userid);
            
            int recordsDeleted = ps.executeUpdate();
                        
            if (recordsDeleted == 1)
               return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return false;
    }
    
    public static Student getStudent(int userid) {
        Connection con = null;
        try {
            con = StudentDao.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT rollno, name, gender, age, email, mobile, degree, batch, section, gpa FROM student WHERE userid = ?");
            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();
            
            Student student = new Student();
            student.setUserid(userid);
            
            if (!rs.next()) {
                // no entry for this user till now, add an empty student
                boolean studentInserted = StudentDao.insertStudent(student);
                if (!studentInserted)
                    throw new Exception("Could not add a new student entry for this user.");
                return student;
            }
            
            student.setRollno(rs.getString(1));
            student.setName(rs.getString(2));
            student.setGender(rs.getString(3));
            student.setAge(rs.getInt(4));
            student.setEmail(rs.getString(5));
            student.setMobile(rs.getString(6));
            student.setDegree(rs.getString(7));
            student.setBatch(rs.getString(8));
            student.setSection(rs.getString(9));
            student.setGpa(rs.getFloat(10));
            
            return student;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public static List<Student> getStudents() {
        Connection con = null;
        try {
            con = StudentDao.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT userid, rollno, name, gender, age, email, mobile, degree, batch, section, gpa FROM student ORDER BY userid");
            ResultSet rs = ps.executeQuery();
            
            List<Student> students = new ArrayList<>();
            while (rs.next()) {               
                Student student = new Student();
                
                student.setUserid(rs.getInt(1));
                student.setRollno(rs.getString(2));
                student.setName(rs.getString(3));
                student.setGender(rs.getString(4));
                student.setAge(rs.getInt(5));
                student.setEmail(rs.getString(6));
                student.setMobile(rs.getString(7));
                student.setDegree(rs.getString(8));
                student.setBatch(rs.getString(9));
                student.setSection(rs.getString(10));
                student.setGpa(rs.getFloat(11));
                
                students.add(student);
            }
            
            return students;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public static List<Student> getStudents(int limit, int offset) {
        Connection con = null;
        try {
            con = StudentDao.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT userid, rollno, name, gender, age, email, mobile, degree, batch, section, gpa FROM student ORDER BY userid LIMIT " + limit + " OFFSET " + offset);
            ResultSet rs = ps.executeQuery();
            
            List<Student> students = new ArrayList<>();
            while (rs.next()) {               
                Student student = new Student();
                
                student.setUserid(rs.getInt(1));
                student.setRollno(rs.getString(2));
                student.setName(rs.getString(3));
                student.setGender(rs.getString(4));
                student.setAge(rs.getInt(5));
                student.setEmail(rs.getString(6));
                student.setMobile(rs.getString(7));
                student.setDegree(rs.getString(8));
                student.setBatch(rs.getString(9));
                student.setSection(rs.getString(10));
                student.setGpa(rs.getFloat(11));
                
                students.add(student);
            }
            
            return students;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public static int getCount() {
        Connection con = null;
        try {
            con = StudentDao.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM student");
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                return -1;
            }
            
            int count = rs.getInt(1);
            
            return count;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        } finally {
            try {
                if (con != null)
                    con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

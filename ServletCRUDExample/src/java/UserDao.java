import java.sql.*;

public class UserDao {
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
    
    public static boolean checkUsername(String username) {
        Connection con = null;
        try {
            con = UserDao.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT username FROM user WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            // https://stackoverflow.com/questions/867194/java-resultset-how-to-check-if-there-are-any-results
            if (!rs.isBeforeFirst()) {
                return false;
            }
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
        return true;
    }
    
    public static boolean checkPassword(String username, String password) {
        Connection con = null;        
        try {
            con = UserDao.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT password FROM user WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
                        
            if (!rs.next())
                throw new Exception("No users with this username found.");
            String realPassword = rs.getString(1);
            
            if (!password.equals(realPassword))
                return false;
            
            if (rs.next())
                throw new Exception("Two users with same username found.");
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
        return true;
    }
    
    public static boolean isAdmin(String username) {
        Connection con = null;        
        try {
            con = UserDao.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT isAdmin FROM user WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            if (!rs.next())
                throw new Exception("No users with this username found.");
            String isadmin = rs.getString(1);
            
            if (isadmin.equals("T"))
                return true;
            
            if (rs.next())
                throw new Exception("Two users with same username found.");
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
    
    public static int getUserID(String username) {
        Connection con = null;
        try {
            con = UserDao.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT UserID FROM user WHERE username = ?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            
            if (!rs.next())
                throw new Exception("No users with this username found.");
            int userid = rs.getInt(1);
            
            return userid;
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
    
    public static boolean registerUser(String username, String password) {
        Connection con = null;
        try {
            con = UserDao.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO user (username, password) VALUES (?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            
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
}

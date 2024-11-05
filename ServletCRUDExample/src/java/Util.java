public class Util {
    public static String errorString(String error) {
        return "<p class='error'>"
                + "ERROR: " + error
                + "</p>";
    }
    
    public static String boilerplateStart(String title) {
            return "<!DOCTYPE html>"
                    + "<html>"
                    + "     <head>"
                    + "         <title>" + title + "</title>"
                    + "         <meta charset='UTF-8'>"
                    + "         <meta name='viewport' content='width=device-width, initial-scale=1.0'>"
                    //          Google fonts: Roboto and Rubik.
                    + "         <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">"
                    + "         <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>" 
                    + "         <link href=\"https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Rubik:wght@500&display=swap\" rel=\"stylesheet\">"
                    + "         <link rel='stylesheet' type='text/css' href='style.css'>"
                    + "     </head>"
                    + "     <body>";
    }
    
    public static String boilerplateEnd() {
            return "        </body>"
                    + "</html>";
    }
    
    public static String studentTableBoilerplateStart(boolean isAdmin) {
        return "<h3 class='table-name'> Student Details </h3>"
             + "<div class='table-container'>"
                + "<table>"
                + "     <thead>"
                + "          <tr>"
                + (isAdmin? "    <th>UserID</th>": "")
                + "              <th>RollNo</th>"
                + "              <th>Name</th>"
                + "              <th>Gender</th>"
                + "              <th>Age</th>"
                + "              <th>Email</th>"
                + "              <th>Mobile</th>"
                + "              <th>Degree</th>"
                + "              <th>Batch</th>"
                + "              <th>Section</th>"
                + "              <th>GPA</th>"
                + "              <th>Options</th>"
                + "          </tr>"
                + "     </thead>"
                + "     <tbody>";
    }
    
    public static String studentRow(Student student, boolean isAdmin, int page) {
        return "<tr>"
      + (isAdmin? "<td>" + (student.getUserid() == -1 ? "ERROR: Should not have been empty." : student.getUseridAsString()) + "</td>": "")
                + "<td>" + (student.getRollno().isEmpty() ? "-" : student.getRollno()) + "</td>"
                + "<td>" + (student.getName().isEmpty() ? "-" : student.getName()) + "</td>"
                + "<td>" + (student.getGender().isEmpty() ? "-" : student.getGender()) + "</td>"
                + "<td>" + (student.getAge() == -1 ? "-" : student.getAgeAsString()) + "</td>"
                + "<td>" + (student.getEmail().isEmpty() ? "-" : student.getEmail()) + "</td>"
                + "<td>" + (student.getMobile().isEmpty() ? "-" : student.getMobile()) + "</td>"
                + "<td>" + (student.getDegree().isEmpty() ? "-" : student.getDegree()) + "</td>"
                + "<td>" + (student.getBatch().isEmpty() ? "-" : student.getBatch()) + "</td>"
                + "<td>" + (student.getSection().isEmpty() ? "-" : student.getSection()) + "</td>"
                + "<td>" + (student.getGpa() == -1 ? "-" : student.getGpaAsString()) + "</td>"
                + "<td> <div>" 
                + "     <form action='' method='POST'> " + "<input type='hidden' name='page' value='" + page + "'>" + "<input type='hidden' name='userid' value='" + student.getUseridAsString() + "'> <input type='submit' value='Edit'> </form>" 
      + (isAdmin? "     <form action='delete' method='POST'> " + "<input type='hidden' name='page' value='" + page + "'>" + "<input type='hidden' name='userid' value='" + student.getUseridAsString() + "'> <input type='submit' value='Delete'> </form>": "")
                + "</div></td>" 
             + "</tr>";
    }
    
    public static String studentRowEditable(Student student, boolean isAdmin, int page) {
        return "<form action='edit' method='POST'>"
                + "<tr>"
      + (isAdmin? "     <td>" + (student.getUserid() == -1 ? "ERROR: Should not have been empty." : student.getUseridAsString()) + "</td>": "")
      + (isAdmin? "     <td>" + "<input type='text' name='rollno' value='" + (student.getRollno().isEmpty() ? "-" : student.getRollno()) + "'></td>"
                : "     <td>" + (student.getRollno().isEmpty() ? "-" : student.getRollno()) + "</td>")
                + "     <td>" + "<input type='text' name='name' value='" + (student.getName().isEmpty() ? "-" : student.getName()) + "'></td>"
                + "     <td>" + "<input type='text' name='gender' value='" + (student.getGender().isEmpty() ? "-" : student.getGender()) + "'></td>"
                + "     <td>" + "<input type='text' name='age' value='" + (student.getAge() == -1 ? "-" : student.getAgeAsString()) + "'></td>"
                + "     <td>" + "<input type='text' name='email' value='" + (student.getEmail().isEmpty() ? "-" : student.getEmail()) + "'></td>"
                + "     <td>" + "<input type='text' name='mobile' value='" + (student.getMobile().isEmpty() ? "-" : student.getMobile()) + "'></td>"
      + (isAdmin? "     <td>" + "<input type='text' name='degree' value='" + (student.getDegree().isEmpty() ? "-" : student.getDegree()) + "'></td>"
                : "     <td>" + (student.getDegree().isEmpty() ? "-" : student.getDegree()) + "</td>")
      + (isAdmin? "     <td>" + "<input type='text' name='batch' value='" + (student.getBatch().isEmpty() ? "-" : student.getBatch()) + "'></td>"
                : "     <td>" + (student.getBatch().isEmpty() ? "-" : student.getBatch()) + "</td>")
      + (isAdmin? "     <td>" + "<input type='text' name='section' value='" + (student.getSection().isEmpty() ? "-" : student.getSection()) + "'></td>"
                : "     <td>" + (student.getSection().isEmpty() ? "-" : student.getSection()) + "</td>")
      + (isAdmin? "     <td>" + "<input type='text' name='gpa' value='" + (student.getGpa() == -1 ? "-" : student.getGpaAsString()) + "'></td>"
                : "     <td>" + (student.getGpa() == -1 ? "-" : student.getGpaAsString()) + "</td>")
                + "     <td>" + "<input type='hidden' name='page' value='" + page + "'>" + "<input type='hidden' name='userid' value='" + student.getUseridAsString() + "'> <input type='submit' value='Save'>" + "</td>"
                + "</tr>"
             + "</form>";
    }
    
    public static String studentTableBoilerplateEnd() {
        return "       </tbody>"
            + " </table>"
            + "</div>";
    }
    
    public static String pagination(int currentPage) {
        String pagination = "<div class='center'>"
                + "             <div class='pagination'>"
                + "                 <a href='?page=1'>&laquo;</a>";
        
        // ceiling division
        int maxPage = (StudentDao.getCount() + 4) / 5;
        for (int page = Math.max(1, currentPage - 2); page <= Math.min(maxPage, currentPage + 2); page++) {
            pagination += "         <a href='?page=" + page + "' " + (page == currentPage? "class='active'": "") +">" + page + "</a>";
        }
        
        pagination += "             <a href='?page=" + maxPage + "'>&raquo;</a>"
                + "             </div>"
                + "         </div>";
        
        return pagination;
    }
}

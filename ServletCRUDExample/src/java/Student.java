public class Student {
    int userid;
    String rollno;
    String name;
    String gender;
    int age;
    String email;
    String mobile;
    String degree;
    String batch;
    String section;
    float gpa;

    public Student() {
        this.userid = -1;
        this.rollno = "";
        this.name = "";
        this.gender = "";
        this.age = -1;
        this.email = "";
        this.mobile = "";
        this.degree = "";
        this.batch = "";
        this.section = "";
        this.gpa = -1;
    }

    public int getUserid() {
        return userid;
    }
    
    public String getUseridAsString() {
        return Integer.toString(userid);
    }
    
    public void setUserid(int userid) {
        this.userid = userid;
    }
    
    public void setUseridFromString(String userid) {
        try {
            this.userid = Integer.parseInt(userid);
        } catch (NumberFormatException e) {
            this.userid = -1;
        }
    }
    
    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }
    
    public String getAgeAsString() {
        return Integer.toString(age);
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public void setAgeFromString(String age) {
        try {
            this.age = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            this.age = -1;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public float getGpa() {
        return gpa;
    }
    
    public String getGpaAsString() {
        return Float.toString(gpa);
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }
    
    public void setGpaFromString(String gpa) {
        try {
            this.gpa = Float.parseFloat(gpa);
        } catch (NumberFormatException e) {
            this.gpa = -1;
        }
    }
    
}

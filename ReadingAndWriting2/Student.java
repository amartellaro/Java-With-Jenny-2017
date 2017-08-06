package ReadingAndWriting2;

public class Student
{
    String lastname;
    String firstname;
    String studentid;
    
    public Student(String lastname, String firstname, String studentid)
    {
        this.lastname = lastname;
        this.firstname = firstname;
        this.studentid = studentid;
    }
    
    public String getLastname()
    {
        return lastname;
    }
    
    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }
    
    public String getFirstname()
    {
        return firstname;
    }
    
    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }
    
    public String getStudentid()
    {
        return studentid;
    }
    
    public void setStudentid(String studentid)
    {
        this.studentid = studentid;
    }
}

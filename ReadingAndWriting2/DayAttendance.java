package ReadingAndWriting2;

public class DayAttendance
{
    private String date;
    private String presence;
    private Student student;
    
    public DayAttendance(Student student, String date, String presence)
    {
        this.student = student;
        this.date = date;
        this.presence = presence;
    }
    
    public String getStudent()
    {
        String studentid = student.getStudentid();
        return studentid;
    }
    
    public void setStudent(Student student)
    {
        this.student = student;
    }
    
    public String getDate()
    {
        return date;
    }
    
    public void setDate(String date)
    {
        this.date = date;
    }
    
    public String getPresence()
    {
        return presence;
    }
    
    public void setPresence(String presence)
    {
        this.presence = presence;
    }
}

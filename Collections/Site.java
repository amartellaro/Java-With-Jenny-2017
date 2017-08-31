package Collections;

public class Site
{
    String taskName;
    String classification;
    
    public Site(String taskName, String classification)
    {
        this.taskName = taskName;
        this.classification = classification;
    }
    
    public String getTaskName()
    {
        return taskName;
    }
    
    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    public String getClassification()
    {
        return classification;
    }
    
    public void setClassification(String classification)
    {
        this.classification = classification;
    }
}

package entity;

public class PersonalTask extends Task{

    private String event;
    private String location;
    private String projectName;

    public PersonalTask(String taskName, boolean complete, String event, String location) {
        super(taskName, complete);
        this.event = event;
        this.location = location;
        this.projectName = "Personal";
    }

    public String getEvent() {return event;}

    public String getLocation() {return location;}

    public String getProjectName() {return projectName;}
}

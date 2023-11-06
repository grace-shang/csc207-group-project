package src.entity;

public class PersonalTask extends Task{

    private String event;
    private String location;

    public PersonalTask(String taskName, boolean complete, String event, String location) {
        super(taskName, complete);
        this.event = event;
        this.location = location;
    }

    public String getEvent() {return event;}

    public String getLocation() {return location;}
}

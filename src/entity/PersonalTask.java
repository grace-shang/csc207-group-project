package src.entity;

public class PersonalTask extends Task{

    String event;
    String location;

    public PersonalTask(String courseName, boolean complete, String event, String location) {
        super(courseName, complete);
        this.event = event;
        this.location = location;
    }

    public String getEvent() {return event;}

    public String getLocation() {return location;}


}

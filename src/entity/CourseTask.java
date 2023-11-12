package entity;

public class CourseTask extends Task{

    private Course course;

    // Default constructor
    public CourseTask(String taskName, Course course) {
        super(taskName);
        this.course = course;
    }

    // Overloaded constructor
    public CourseTask(String taskName, boolean complete, Course course) {
        super(taskName, complete);
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }
}

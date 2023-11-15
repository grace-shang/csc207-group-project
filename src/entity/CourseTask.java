package entity;

public class CourseTask extends Task{

    private Course course;
    private String projectName;

    // Default constructor
    public CourseTask(String taskName, Course course) {
        super(taskName);
        this.course = course;
        this.projectName = "Courses";
    }

    // Overloaded constructor
    public CourseTask(String taskName, boolean complete, Course course) {
        super(taskName, complete);
        this.course = course;
        this.projectName = "Courses";
    }

    public Course getCourse() {
        return course;
    }
}

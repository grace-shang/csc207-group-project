package entity;

public class CourseTask extends Task{

    private final Course course;
    private final String projectName;

    // Default constructor
    public CourseTask(String taskName, long taskId, Course course) {
        super(taskName, taskId);
        this.course = course;
        this.projectName = "Courses";
    }

    // Overloaded constructor
    public CourseTask(String taskName, boolean complete, long taskId, Course course) {
        super(taskName, complete, taskId);
        this.course = course;
        this.projectName = "Courses";
    }

    public Course getCourse() {
        return course;
    }
}

package src.entity;

public class CourseTask extends Task{

    private Course course;

    public CourseTask(String taskName, boolean complete, Course course) {
        super(taskName, complete);
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }
}

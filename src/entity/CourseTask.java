package src.entity;

public class CourseTask extends Task{

    private Course course;

    public CourseTask(String courseName, boolean complete, Course course) {
        super(courseName, complete);
        this.course = course;

    }

    public Course getCourse() {
        return course;
    }

}

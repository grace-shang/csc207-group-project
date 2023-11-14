package entity;

public class AllTaskFactory implements TaskFactory {
    @Override
    public Task create(String taskName) {
        return new Task(taskName, "project");
    }
}

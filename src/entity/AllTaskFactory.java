package entity;

public class AllTaskFactory implements TaskFactory {
    @Override
    public Task create(String taskName, boolean completion, long taskId) {
        return new Task(taskName, false, taskId);
    }
}

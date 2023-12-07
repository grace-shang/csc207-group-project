package entity;

public class AllTaskFactory implements TaskFactory {
    @Override
    public Task create(String taskName, Boolean completion, long taskId) {
        return new Task(taskName, completion, taskId);
    }
}

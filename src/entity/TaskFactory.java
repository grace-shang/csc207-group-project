package entity;

public interface TaskFactory {
    Task create(String taskName, boolean completion, long taskId);
}

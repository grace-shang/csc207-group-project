package entity;

public interface TaskFactory {
    /**
     * @param taskName is the task name
     * @param completion is the boolean value of whether it is completed or not
     * @param taskId the task id needed to create the entity
     * @return
     */
    Task create(String taskName, Boolean completion, long taskId);
}

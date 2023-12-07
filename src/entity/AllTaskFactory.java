package entity;

public class AllTaskFactory implements TaskFactory {
    /**
     * Creates a task object
     * @param taskName is the task name
     * @param completion is the boolean value of whether it is completed or not
     * @param taskId the task id needed to create the entity
     * @return an object of Task
     */
    @Override
    public Task create(String taskName, Boolean completion, long taskId) {
        return new Task(taskName, completion, taskId);
    }
}

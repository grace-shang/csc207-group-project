package entity;

public class Task implements TaskI{

    String taskName;
    boolean complete;

    long taskId;

    /**
     * Constrcuts a task with complete variable being always false
     * @param taskName a string containing the name of the task
     * @param taskId the ID of the task from the API
     */
    public Task(String taskName, long taskId) {
        this.taskName = taskName;
        this.complete = false;
        this.taskId = taskId;

    }

    /**
     * Constructs a task with a given complete variable
     * @param taskName a string containing the name of the task
     * @param isCompleted a boolean containing whether the task is complete (true) or not (false)
     * @param taskId the ID of the task from the API
     */
    public Task(String taskName, boolean isCompleted, long taskId) {
        this.taskName = taskName;
        this.complete = isCompleted;
        this.taskId = taskId;
    }

    /**
     * A getter for the task name
     * @return a string containing the task name
     */
    public String getName() {return taskName;}

    /**
     * A getter for the completion of a task
     * @return a boolean containing whether the task is complete or not
     */
    public boolean getComplete() {return complete;}

    /**
     * A setter for the task name
     * @param name a string containing the new name of the task
     */
    public void setName(String name) {this.taskName = name;}

    /**
     * A setter for the completion of a task
     * @param complete a boolean containing the new completion of the task
     */
    public void setComplete(boolean complete) {this.complete = complete;}

    /**
     * A setter for the task ID
     * @param taskId the new task ID to be set
     */
    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    /**
     * A getter for the task ID
     * @return a long containing the task ID
     */
    public long getTaskId() {
        return taskId;
    }

}

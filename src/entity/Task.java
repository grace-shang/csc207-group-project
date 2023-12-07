package entity;

public class Task implements TaskI{

    String taskName;
    boolean complete;

    long taskId;

    /**
     * default value of complete is false in this instance
     * @param taskName the task name needed to create the task
     * @param taskId the task id needed to create the task
     */
    // Default constructor. Default value of complete is false.
    public Task(String taskName, long taskId) {
        this.taskName = taskName;
        this.complete = false;
        this.taskId = taskId;

    }

    /**
     * @param taskName the task name needed to create the task
     * @param isCompleted Overloaded constructor with the optional parameter of isCompleted
     * @param taskId the task id needed to create the task
     */
    // Overloaded constructor with the optional parameter of isCompleted.
    public Task(String taskName, boolean isCompleted, long taskId) {
        this.taskName = taskName;
        this.complete = isCompleted;
        this.taskId = taskId;
    }

    /**
     * @return the task name in the entity
     */
    public String getName() {return taskName;}

    /**
     * @return whether the task is completed or not
     */
    public boolean getComplete() {return complete;}

    /**
     * @param name setter that sets the task name
     */
    public void setName(String name) {this.taskName = name;}

    /**
     * @param complete setter that changes the completion to whatever the boolean value is
     */
    public void setComplete(boolean complete) {this.complete = complete;}

    /**
     * @param taskId sets the task id with the long given
     */
    @Override
    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    /**
     * @return the taskId of the entity
     */
    @Override
    public long getTaskId() {
        return taskId;
    }

}

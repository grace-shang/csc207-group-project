package entity;

public class Task implements TaskI{

    String taskName;
    boolean complete;

    long taskId;

    // Default constructor. Default value of complete is false.
    public Task(String taskName, long taskId) {
        this.taskName = taskName;
        this.complete = false;
        this.taskId = taskId;

    }

    // Overloaded constructor with the optional parameter of isCompleted.
    public Task(String taskName, boolean isCompleted, long taskId) {
        this.taskName = taskName;
        this.complete = isCompleted;
        this.taskId = taskId;
    }

    public String getName() {return taskName;}

    public boolean getComplete() {return complete;}

    public void setName(String name) {this.taskName = name;}

    public void setComplete(boolean complete) {this.complete = complete;}

    @Override
    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    @Override
    public long getTaskId() {
        return taskId;
    }

}

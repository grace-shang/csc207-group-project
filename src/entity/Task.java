package entity;

class Task implements TaskI{

    String taskName;
    boolean complete;



    // Default constructor. Default value of complete is false.
    public Task(String taskName, String projectName, boolean completion) {
        this.taskName = taskName;
        this.complete = completion;

    }

    // Overloaded constructor with the optional parameter of isCompleted.
    public Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.complete = isCompleted;
    }

    public String getName() {return taskName;}

    public boolean getComplete() {return complete;}

    public void setName(String name) {this.taskName = name;}

    public void setComplete(boolean complete) {this.complete = complete;}

}

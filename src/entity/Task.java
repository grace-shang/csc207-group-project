package entity;

class Task implements TaskI{

    String taskName;
    boolean complete;

    public Task(String taskName) {
        this.taskName = taskName;
        this.complete = false;
    }

    public String getName() {return taskName;}

    public boolean getComplete() {return complete;}

    public void setName(String name) {this.taskName = name;}

    public void setComplete(boolean complete) {this.complete = complete;}

}

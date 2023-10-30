package entity.src.entity;

abstract class Task {

    String taskName;
    boolean complete;

    public Task(String taskName, boolean complete) {
        this.taskName = taskName;
        this.complete = complete;
    }

    public String getName() {return taskName;}

    public boolean getComplete() {return complete;}

    public void setName(String name) {this.taskName = name;}

    public void setComplete(boolean complete) {this.complete = complete;}

}

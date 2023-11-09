package entity;

class Task implements TaskI{

    String taskName;

    String projectName;
    boolean complete;

    public Task(String taskName, boolean complete, String projectName) {
        this.taskName = taskName;
        this.complete = complete;
        this.projectName = projectName;
    }

    public String getName() {return taskName;}

    @Override
    public String getProjectName() {
        return projectName;
    }

    public boolean getComplete() {return complete;}

    public void setName(String name) {this.taskName = name;}

    public void setComplete(boolean complete) {this.complete = complete;}

}

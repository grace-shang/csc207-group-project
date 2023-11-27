package interface_adapter.display_task;

import java.util.Set;

public class DisplayTaskState {

    private Set<String> tasks = null;
    private String taskNames = null;

    public DisplayTaskState(DisplayTaskState copy) {
        tasks = copy.tasks;
        taskNames = copy.taskNames;
    }

    public DisplayTaskState() {
    }

    public Set<String> getTasks() {return tasks;}

    public void setTasks(Set<String> tasks) {this.tasks = tasks;}

    public String getTaskNames() {
        return taskNames;
    }

    public static void setTaskNames(String taskNames) {
        this.taskNames = taskNames;
    }

}

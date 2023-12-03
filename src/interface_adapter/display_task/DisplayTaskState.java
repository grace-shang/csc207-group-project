package interface_adapter.display_task;

import java.util.HashSet;
import java.util.Set;

public class DisplayTaskState {

    private Set<String> tasks = new HashSet<>();

    private Set<Object> taskInfo = new HashSet<>();

    public DisplayTaskState(DisplayTaskState copy) {
        this.tasks = copy.tasks;
        this.taskInfo = copy.taskInfo;
    }

    public DisplayTaskState() {
    }

    public Set<String> getTasks() {return tasks;}

    public void setTasks(Set<String> tasks) {this.tasks = tasks;}

    public Set<Object> getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(Set<Object> taskInfo) {
        this.taskInfo = taskInfo;
    }

}

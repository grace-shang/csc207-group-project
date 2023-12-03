package interface_adapter.display_task;

import java.util.ArrayList;

public class DisplayTaskState {

    private ArrayList<String> tasks = new ArrayList<>();

    private ArrayList<ArrayList<Object>> taskInfo = new ArrayList<>();

    public DisplayTaskState(DisplayTaskState copy) {
        this.tasks = copy.tasks;
        this.taskInfo = copy.taskInfo;
    }

    public DisplayTaskState() {
    }

    public ArrayList<String> getTasks() {return tasks;}

    public void setTasks(ArrayList<String> tasks) {this.tasks = tasks;}

    public ArrayList<ArrayList<Object>> getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(ArrayList<ArrayList<Object>> taskInfo) {
        this.taskInfo = taskInfo;
    }

}

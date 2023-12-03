package use_case.display_task;

import java.util.ArrayList;
import java.util.Collection;

public class DisplayTaskOutputData {

    private final ArrayList<String> tasks;
    private final ArrayList<ArrayList<Object>> taskInfo;

    private boolean useCaseFailed;

    public DisplayTaskOutputData(ArrayList<String> tasks, ArrayList<ArrayList<Object>> taskInfo, boolean useCaseFailed) {
        this.tasks = tasks;
        this.taskInfo = taskInfo;
        this.useCaseFailed = useCaseFailed;
    }

    public ArrayList<String> getAllTasks() {return tasks;}

    public ArrayList<ArrayList<Object>> getTaskInfo() {
        return taskInfo;
    }


}

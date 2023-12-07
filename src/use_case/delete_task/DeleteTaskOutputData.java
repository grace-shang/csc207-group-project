package use_case.delete_task;

import java.util.ArrayList;
import java.util.Set;

public class DeleteTaskOutputData {
    private boolean deleteTaskFailed;
    private Set<String> tasks;

    public DeleteTaskOutputData(boolean deleteTaskFailed, Set<String> tasks) {
        this.deleteTaskFailed = deleteTaskFailed;
        this.tasks = tasks;
    }

    public Set<String> getAllTasks() {return tasks;}
}

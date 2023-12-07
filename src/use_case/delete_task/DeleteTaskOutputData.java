package use_case.delete_task;

import java.util.ArrayList;
import java.util.Set;

public class DeleteTaskOutputData {
    private boolean deleteTaskFailed;
    private Set<String> tasks;

    /**
     * Constructor for the DeleteTaskOutputData
     * @param deleteTaskFailed boolean to check if the use case failed
     * @param tasks set of all active task names
     */
    public DeleteTaskOutputData(boolean deleteTaskFailed, Set<String> tasks) {
        this.deleteTaskFailed = deleteTaskFailed;
        this.tasks = tasks;
    }

    /**
     * Getter for all task names
     * @return set of all task names
     */
    public Set<String> getAllTasks() {return tasks;}
}

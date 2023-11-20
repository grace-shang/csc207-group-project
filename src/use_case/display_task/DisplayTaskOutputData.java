package use_case.display_task;

import java.util.Set;

public class DisplayTaskOutputData {

    private final Set<String> tasks;
    private String taskNames;

    private boolean useCaseFailed;

    public DisplayTaskOutputData(Set<String> tasks, String taskNames, boolean useCaseFailed) {
        this.tasks = tasks;
        this.taskNames = taskNames;
        this.useCaseFailed = useCaseFailed;
    }

    public Set<String> getAllTasks() {return tasks;}

    public String getTaskNames() {
        return taskNames;
    }

    public void setTaskNames(String taskNames) {
        this.taskNames = taskNames;
    }


}

package use_case.display_task;

import java.util.Set;

public class DisplayTaskOutputData {

    private final Set<String> tasks;
    private final Set<Object> taskInfo;

    private boolean useCaseFailed;

    public DisplayTaskOutputData(Set<String> tasks, Set<Object> taskInfo, boolean useCaseFailed) {
        this.tasks = tasks;
        this.taskInfo = taskInfo;
        this.useCaseFailed = useCaseFailed;
    }

    public Set<String> getAllTasks() {return tasks;}

    public Set<Object> getTaskInfo() {
        return taskInfo;
    }


}

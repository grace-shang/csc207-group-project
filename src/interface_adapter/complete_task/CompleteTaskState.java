package interface_adapter.complete_task;

import entity.TaskI;
import interface_adapter.create_task.CreateTaskState;

public class CompleteTaskState {
    private final String task;

    public CompleteTaskState(CreateTaskState copy) {
        task = copy.getTask();
    }

    public String getTaskName() {
        return task;
    }
}

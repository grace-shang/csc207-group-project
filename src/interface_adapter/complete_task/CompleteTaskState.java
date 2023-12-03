package interface_adapter.complete_task;

import entity.AllTaskFactory;
import entity.TaskI;
import interface_adapter.create_task.CreateTaskState;

public class CompleteTaskState {
    private final String task;

    /**
     * @param copy a copy of the state
     */
    public CompleteTaskState(CreateTaskState copy) {
        task = copy.getTask();
    }

    /**
     * Getter for the task name
     * @return task
     */
    public String getTaskName() {
        return task;
    }

    /**
     * Setter for the task name
     * @param task the task that will get set to complete
     */
    public void setTaskCompletion(TaskI task){
        task.setComplete(true);
    }


}

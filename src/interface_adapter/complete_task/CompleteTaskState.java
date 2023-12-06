package interface_adapter.complete_task;

import interface_adapter.create_task.CreateTaskState;

public class CompleteTaskState {
    private String task;

    /**
     * @param copy a copy of the state
     */
    public CompleteTaskState(CreateTaskState copy) {
        this.task = copy.getTask();
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
    public void setTaskCompletion(String task){
        this.task = task;
    }


}

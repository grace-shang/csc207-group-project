package use_case.complete_task;

public class CompleteTaskInputData {
    private final String taskName;

    /**
     * @param task the task to complete
     */
    public CompleteTaskInputData(String task) {
        this.taskName = task;
    }

    /**
     * Getter for the task name
     * @return task name
     */
    public String getTaskName(){
        System.out.println("Complete task input data task name: " + taskName);
        return taskName;
    }
}

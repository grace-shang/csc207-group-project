package use_case.complete_task;

public class CompleteTaskInputData {
    private final String taskName;
    public CompleteTaskInputData(String task) {
        this.taskName = task;
    }

    public String getTaskName(){
        return taskName;
    }
}

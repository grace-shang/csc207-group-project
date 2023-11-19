package use_case.complete_task;

public class CompleteTaskOutputData {
    private boolean completeTaskFailed;
    private final String task;

    public CompleteTaskOutputData(boolean completeTaskFailed, String task){
        this.completeTaskFailed = completeTaskFailed;
        this.task = task;
    }
}

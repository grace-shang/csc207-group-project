package use_case.complete_task;

public class CompleteTaskOutputData {
    private boolean completeTaskFailed;
    private String task;

    /**
     * Determines if the use case failed
     * @param completeTaskFailed boolean for whether the use case failed
     * @param task the name of the completed task
     */
    public CompleteTaskOutputData(String task, boolean completeTaskFailed){
        this.completeTaskFailed = completeTaskFailed;
        this.task = task;
    }
}

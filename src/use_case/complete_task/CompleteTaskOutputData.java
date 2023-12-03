package use_case.complete_task;

public class CompleteTaskOutputData {
    private boolean completeTaskFailed;

    /**
     * Determines if the use case failed
     * @param completeTaskFailed boolean for whether the use case failed
     */
    public CompleteTaskOutputData(boolean completeTaskFailed){
        this.completeTaskFailed = completeTaskFailed;
    }
}

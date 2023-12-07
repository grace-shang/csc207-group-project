package use_case.delete_task;

import use_case.complete_task.CompleteTaskOutputData;

public interface DeleteTaskOutputBoundary {
    void prepareSuccessView(DeleteTaskOutputData deleted);

}

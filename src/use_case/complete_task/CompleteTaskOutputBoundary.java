package use_case.complete_task;

import use_case.complete_task.CompleteTaskOutputData;

public interface CompleteTaskOutputBoundary {
    void prepareSuccessView(CompleteTaskOutputData completed);
}

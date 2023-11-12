package use_case.complete_task;

import use_case.create_task.CreateTaskOutputBoundary;

public interface CompleteTaskOutputBoundary {
    void prepareSuccessView(CreateTaskOutputBoundary task);
}

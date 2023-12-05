package use_case.create_task;

public interface CreateTaskOutputBoundary {
    void prepareSuccessView(CreateTaskOutputData createTaskOutputData);
    void prepareFailView(String error);

}

package use_case.create_task;


public interface CreateTaskOutputBoundary {
    void prepareSuccessView(CreateTaskOutputData task);
    void prepareFailView(String error);

}

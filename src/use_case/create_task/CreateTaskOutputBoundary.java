package use_case.create_task;


public interface CreateTaskOutputBoundary {
    /**
     * @param task is the outputdata that is being sent to the presenter
     */
    void prepareSuccessView(CreateTaskOutputData task);

    /**
     * @param error is the error message that is being sent to the fail view implementation in presenter
     */
    void prepareFailView(String error);

}

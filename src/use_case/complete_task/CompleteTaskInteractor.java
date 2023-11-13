package use_case.complete_task;

public class CompleteTaskInteractor implements CompleteTaskInputBoundary{
    final CompleteTaskDataAccessInterface completeDataAccessObject;
    final CompleteTaskOutputBoundary completeTaskPresenter;

    public CompleteTaskInteractor(CompleteTaskDataAccessInterface completeDataAccessObject,
                                  CompleteTaskOutputBoundary completeTaskOutputBoundary){
        this.completeDataAccessObject = completeDataAccessObject;
        this.completeTaskPresenter = completeTaskOutputBoundary;
    }

    @Override
    public void execute(CompleteTaskInputData completeTaskInputData) {
        // Still need to write the execute method
    }
}

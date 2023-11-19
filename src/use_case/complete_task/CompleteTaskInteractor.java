package use_case.complete_task;

import data_access.FileTaskDataAccessObject;
import entity.TaskI;

import java.io.IOException;

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
        CompleteTaskOutputData completeTaskOutputData = new CompleteTaskOutputData(false);
        String completedTask = completeTaskInputData.getTaskName();
        // Finish execute method
        completeTaskPresenter.prepareSuccessView(completeTaskOutputData);
    }
}

package use_case.complete_task;

import data_access.FileTaskDataAccessObject;
import entity.TaskI;

import java.io.IOException;

public class CompleteTaskInteractor implements CompleteTaskInputBoundary{
    final CompleteTaskDataAccessInterface completeDataAccessObject;
    final CompleteTaskOutputBoundary completeTaskPresenter;

    /**
     * @param completeDataAccessObject the data access object for the complete use case
     * @param completeTaskOutputBoundary the output boundary for the complete use case
     */
    public CompleteTaskInteractor(CompleteTaskDataAccessInterface completeDataAccessObject,
                                  CompleteTaskOutputBoundary completeTaskOutputBoundary){
        this.completeDataAccessObject = completeDataAccessObject;
        this.completeTaskPresenter = completeTaskOutputBoundary;
    }

    /**
     * Executes the completion of a task by notifying the use case succeeded
     * @param completeTaskInputData the input data being passed through the interactor
     */
    @Override
    public void execute(CompleteTaskInputData completeTaskInputData) throws IOException {
        String completedTask = completeTaskInputData.getTaskName();

        TaskI task = completeDataAccessObject.getTask(completedTask);
        try {
            completeDataAccessObject.complete(completedTask);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Completion: " + task.getComplete());


        CompleteTaskOutputData completeTaskOutputData = new CompleteTaskOutputData(completedTask,false);
        completeTaskPresenter.prepareSuccessView(completeTaskOutputData);
    }
}

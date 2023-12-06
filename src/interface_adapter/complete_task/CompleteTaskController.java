package interface_adapter.complete_task;

import use_case.complete_task.CompleteTaskInputBoundary;
import use_case.complete_task.CompleteTaskInputData;

import java.io.IOException;

public class CompleteTaskController {

    final CompleteTaskInputBoundary completeTaskUseCaseInteractor;

    /**
     * @param completeTaskUseCaseInteractor the input boundary that the controller will pass information through
     */
    public CompleteTaskController(CompleteTaskInputBoundary completeTaskUseCaseInteractor){
        this.completeTaskUseCaseInteractor = completeTaskUseCaseInteractor;
    }

    /**
     * Creates new input data for the complete task use case
     * @param task the String that helps determine the complete task
     * @throws IOException
     */
    public void execute(String task) throws IOException {
        CompleteTaskInputData completeTaskInputData = new CompleteTaskInputData(task);
        completeTaskUseCaseInteractor.execute(completeTaskInputData);
    }

//    public boolean getTaskCompletion(String task){
//        CompleteTaskInputData completeTaskInputData = new CompleteTaskInputData(task);
//        return completeTaskUseCaseInteractor.getTaskCompletion(completeTaskInputData);
//    }

}

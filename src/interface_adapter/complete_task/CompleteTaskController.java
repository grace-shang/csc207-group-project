package interface_adapter.complete_task;

import use_case.complete_task.CompleteTaskInputBoundary;
import use_case.complete_task.CompleteTaskInputData;

import java.io.IOException;

public class CompleteTaskController {

    final CompleteTaskInputBoundary completeTaskUseCaseInteractor;

    public CompleteTaskController(CompleteTaskInputBoundary completeTaskUseCaseInteractor){
        this.completeTaskUseCaseInteractor = completeTaskUseCaseInteractor;

    }

    public void execute(String task) throws IOException {
        CompleteTaskInputData completeTaskInputData = new CompleteTaskInputData(task);
        completeTaskUseCaseInteractor.execute(completeTaskInputData);
    }


}

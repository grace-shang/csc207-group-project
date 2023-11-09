package interface_adapter.complete_task;

import use_case.complete_task.CompleteTaskInputBoundary;
import use_case.complete_task.CompleteTaskInputData;

public class CompleteTaskController {

    final CompleteTaskInputBoundary userCompleteTaskUseCaseInteractor;

    public CompleteTaskController(CompleteTaskInputBoundary userCompleteTaskUseCaseInteractor){
        this.userCompleteTaskUseCaseInteractor = userCompleteTaskUseCaseInteractor;

    }

    public void execute(String task){
        CompleteTaskInputData completeTaskInputData = new CompleteTaskInputData(task);
        // userCreateTaskUseCaseInteractor.execute(createTaskInputData);
    }


}

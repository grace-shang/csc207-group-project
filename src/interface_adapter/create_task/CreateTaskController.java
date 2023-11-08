package interface_adapter.create_task;

import use_case.create_task.CreateTaskInputBoundary;
import use_case.create_task.CreateTaskInputData;

public class CreateTaskController {

    final CreateTaskInputBoundary userCreateTaskUseCaseInteractor;

    public CreateTaskController(CreateTaskInputBoundary userCreateTaskUseCaseInteractor){
        this.userCreateTaskUseCaseInteractor = userCreateTaskUseCaseInteractor;

    }

    public void execute(String task){
        CreateTaskInputData createTaskInputData = new CreateTaskInputData(task);

        userCreateTaskUseCaseInteractor.execute(createTaskInputData);
    }



}

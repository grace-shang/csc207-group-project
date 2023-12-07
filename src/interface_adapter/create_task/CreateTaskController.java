package interface_adapter.create_task;

import use_case.create_task.CreateTaskInputBoundary;
import use_case.create_task.CreateTaskInputData;

public class CreateTaskController {

    final CreateTaskInputBoundary userCreateTaskUseCaseInteractor;

    /**
     * @param userCreateTaskUseCaseInteractor sets up the controller in order to initalize a new input boundary.
     */
/*

 */
    public CreateTaskController(CreateTaskInputBoundary userCreateTaskUseCaseInteractor){
        this.userCreateTaskUseCaseInteractor = userCreateTaskUseCaseInteractor;

    }

    /**
     * @param task creates a new create task input data and adds in the task into the input data given by task view and
     *             executes the create task input boundary.
     */
    public void execute(String task){
        CreateTaskInputData createTaskInputData = new CreateTaskInputData(task);

        userCreateTaskUseCaseInteractor.execute(createTaskInputData);
    }



}

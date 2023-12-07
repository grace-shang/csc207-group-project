package use_case.create_task;

public interface CreateTaskInputBoundary {

    /**
     * @param createTaskInputData executes with the create task input data
     */
    void execute(CreateTaskInputData createTaskInputData);
}

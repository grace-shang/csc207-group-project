package use_case.create_task;

public class CreateTaskInputData {

    final private String createTask;

    /**
     * @param createTask intializes the create task input data
     */
    public CreateTaskInputData(String createTask){
        this.createTask = createTask;
    }

    /**
     * @return return the task given into the input data
     */
    String getCreateTask(){return createTask;}
}

package use_case.create_task;

public class CreateTaskInputData {

    final private String createTask;

    public CreateTaskInputData(String createTask){
        this.createTask = createTask;
    }

    String getCreateTask(){return createTask;}
}

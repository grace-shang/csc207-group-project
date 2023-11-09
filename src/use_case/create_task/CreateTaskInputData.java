package use_case.create_task;

public class CreateTaskInputData {

    final private String createTask;

    final private String projectName;

    public CreateTaskInputData(String createTask, String projectName){
        this.createTask = createTask;
        this.projectName = projectName;
    }

    String getCreateTask(){return createTask;}
}

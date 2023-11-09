package use_case.create_task;

public class CreateTaskOutputData {

    private final String task;

    public CreateTaskOutputData(String task){
        this.task = task;
    }

    public String getTask(){return task;}
}

package interface_adapter.create_task;

public class CreateTaskState {

    private String task = "";

    private String taskError = null;

    public CreateTaskState(CreateTaskState copy){
        task = copy.task;
        taskError = copy.taskError;
    }

    public CreateTaskState(){}

    public String getTask(){return task;}

    public String getTaskError() {
        return taskError;
    }

    public String toString() {
        return "CreateTaskState{" +
                "task=" + task + '\'' +
                '}';
    }
}

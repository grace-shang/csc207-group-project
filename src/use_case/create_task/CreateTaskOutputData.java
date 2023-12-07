package use_case.create_task;

public class CreateTaskOutputData {

    private final String task;

    /**
     * @param task is initialized with the task that was given by the interactor=
     */
    public CreateTaskOutputData(String task){
        this.task = task;
    }

    /**
     * @return returns the task that was initialized above
     */
    public String getTask(){return task;}
}

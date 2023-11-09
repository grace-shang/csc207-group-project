package interface_adapter.create_task;

import view.ViewManager;
import interface_adapter.ViewModel;

import javax.swing.text.View;

public class CreateTaskState{

    private String task = "";

    private String taskError = null;

    private String project = "";

    private String projectError = null;

    private CreateTaskState state = new CreateTaskState();

    public CreateTaskState(CreateTaskState copy){
        task = copy.task;
        taskError = copy.taskError;
        project = copy.project;
        projectError = copy.projectError;
    }

    public CreateTaskState(){}

    public String getTask(){return task;}

    public String getTaskError() {
        return taskError;
    }

    public Object getProject() {return project;}

    public String getProjectError(){return getProjectError();}

    public String toString() {
        return "CreateTaskState{" +
                "task=" + task + '\'' +
                '}';
    }


}

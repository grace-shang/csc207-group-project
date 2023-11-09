package interface_adapter.create_task;

import interface_adapter.create_task.CreateTaskState;
import interface_adapter.create_task.CreateTaskViewModel
import view.ViewManager;
import use_case.create_task.CreateTaskOutputBoundary;
import use_case.create_task.CreateTaskOutputData;

public class CreateTaskPresenter implements CreateTaskOutputBoundary{

    private final CreateTaskViewModel createTaskViewModel;
    private ViewManager viewManager;


    public CreateTaskPresenter(CreateTaskViewModel createTaskViewModel,
                               ViewManager viewManager){
        this.createTaskViewModel = createTaskViewModel;
        this.viewManager = viewManager;
    }


    @Override
    public void prepareSuccessView(CreateTaskOutputBoundary response) {
        CreateTaskState createTaskState = createTaskViewModel.getState();
        createTaskState.getTask();


    }
}

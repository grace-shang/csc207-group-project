package interface_adapter.create_task;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_task.CreateTaskState;
import interface_adapter.create_task.CreateTaskViewModel;
import view.ViewManager;
import use_case.create_task.CreateTaskOutputBoundary;
import use_case.create_task.CreateTaskOutputData;

public class CreateTaskPresenter implements CreateTaskOutputBoundary{

    private final CreateTaskViewModel createTaskViewModel;
    private ViewManagerModel viewManagerModel;


    public CreateTaskPresenter(CreateTaskViewModel createTaskViewModel,
                               ViewManagerModel viewManagerModel){
        this.createTaskViewModel = createTaskViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(CreateTaskOutputData response) {
        CreateTaskState createTaskState = createTaskViewModel.getState();
        createTaskState.getTask();


    }
}

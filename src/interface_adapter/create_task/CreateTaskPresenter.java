package interface_adapter.create_task;

import entity.TaskFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_task.CreateTaskState;
import interface_adapter.create_task.CreateTaskViewModel;
import interface_adapter.display_task.DisplayTaskState;
import view.ViewManager;
import use_case.create_task.CreateTaskOutputBoundary;
import use_case.create_task.CreateTaskOutputData;

public class CreateTaskPresenter implements CreateTaskOutputBoundary {

    private final CreateTaskViewModel createTaskViewModel;
    private ViewManagerModel viewManagerModel;


    /**
     * @param createTaskViewModel initializes the create task view model
     * @param viewManagerModel initializes the view manager model in preseneter
     */
    public CreateTaskPresenter(CreateTaskViewModel createTaskViewModel,
                               ViewManagerModel viewManagerModel) {
        this.createTaskViewModel = createTaskViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    /**
     * @param response takes the output data and activates firepropertychange which will update the view model and change
     *                 the main page of the program for a successful addition of a task.
     */
    @Override
    public void prepareSuccessView(CreateTaskOutputData response) {
        CreateTaskState createTaskState = createTaskViewModel.getState();
        createTaskState.getTask();
        this.createTaskViewModel.setState(createTaskState);
        createTaskViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(createTaskViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * @param error sends back the error in the failed task view which can then also be used to update the view model to
     *              tell the user about the error.
     */
    @Override
    public void prepareFailView(String error){
        CreateTaskState createTaskState = createTaskViewModel.getState();
        createTaskState.setTaskError(error);
        createTaskViewModel.firePropertyChanged();
    }
}
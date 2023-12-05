package interface_adapter.display_task;

import interface_adapter.ViewManagerModel;
import use_case.display_task.DisplayTaskOutputBoundary;
import use_case.display_task.DisplayTaskOutputData;

public class DisplayTaskPresenter implements DisplayTaskOutputBoundary {

    private final DisplayTaskViewModel displayTaskViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructs the presenter for the display use case
     * @param displayTaskViewModel the view model for the display use case
     * @param viewManagerModel the view manager model for managing the view models
     */
    public DisplayTaskPresenter(DisplayTaskViewModel displayTaskViewModel, ViewManagerModel viewManagerModel) {
        this.displayTaskViewModel = displayTaskViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Changes the state of the display task with the output data and updates the
     * view manager model with the changes
     * @param response the output data for the display use case
     */
    @Override
    public void prepareSuccessView(DisplayTaskOutputData response) {

        DisplayTaskState displayTaskState = displayTaskViewModel.getState();
        displayTaskState.setTasks(response.getAllTasks());
        displayTaskState.setTaskInfo(response.getTaskInfo());
        this.displayTaskViewModel.setState(displayTaskState);
        displayTaskViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(displayTaskViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}

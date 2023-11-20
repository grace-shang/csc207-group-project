package interface_adapter.display_task;

import interface_adapter.ViewManagerModel;
import use_case.display_task.DisplayTaskOutputBoundary;
import use_case.display_task.DisplayTaskOutputData;

public class DisplayTaskPresenter implements DisplayTaskOutputBoundary {

    private final DisplayTaskViewModel displayTaskViewModel;
    private ViewManagerModel viewManagerModel;

    public DisplayTaskPresenter(DisplayTaskViewModel displayTaskViewModel, ViewManagerModel viewManagerModel) {
        this.displayTaskViewModel = displayTaskViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DisplayTaskOutputData response) {

        DisplayTaskState displayTaskState = displayTaskViewModel.getState();
        displayTaskState.setTasks(response.getAllTasks());
        DisplayTaskState.setTaskNames(response.getTaskNames());
        this.displayTaskViewModel.setState(displayTaskState);
        displayTaskViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(DisplayTaskViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}

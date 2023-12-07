package interface_adapter.delete_task;

import interface_adapter.ViewManagerModel;
import use_case.delete_task.DeleteTaskOutputBoundary;
import use_case.delete_task.DeleteTaskOutputData;

public class DeleteTaskPresenter implements DeleteTaskOutputBoundary {

    private final DeleteTaskViewModel deleteViewModel;
    private ViewManagerModel viewManagerModel;

    public DeleteTaskPresenter(DeleteTaskViewModel deleteViewModel, ViewManagerModel viewManagerModel) {
        this.deleteViewModel = deleteViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(DeleteTaskOutputData response) {

        DeleteTaskState deleteTaskState = deleteViewModel.getState();
        deleteTaskState.setTasks(response.getAllTasks());
        this.deleteViewModel.setState(deleteTaskState);
        deleteViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(deleteViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}

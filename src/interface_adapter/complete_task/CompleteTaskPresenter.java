package interface_adapter.complete_task;

import interface_adapter.ViewManagerModel;
import use_case.complete_task.CompleteTaskOutputBoundary;
import use_case.create_task.CreateTaskOutputBoundary;

public class CompleteTaskPresenter implements CompleteTaskOutputBoundary {
    private final CompleteTaskViewModel completeTaskViewModel;
    private final ViewManagerModel viewManagerModel;

    public CompleteTaskPresenter(ViewManagerModel viewManagerModel, CompleteTaskViewModel completeTaskViewModel) {
        this.completeTaskViewModel = completeTaskViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CreateTaskOutputBoundary task) {

    }
}

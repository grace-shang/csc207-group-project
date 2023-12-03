package interface_adapter.complete_task;

import interface_adapter.ViewManagerModel;
import use_case.complete_task.CompleteTaskOutputBoundary;
import use_case.complete_task.CompleteTaskOutputData;
import use_case.create_task.CreateTaskOutputBoundary;
import use_case.create_task.CreateTaskOutputData;

public class CompleteTaskPresenter implements CompleteTaskOutputBoundary {
    private final CompleteTaskViewModel completeTaskViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * @param viewManagerModel the view manager the presenter will interact with
     * @param completeTaskViewModel the specific view for this use case
     */
    public CompleteTaskPresenter(ViewManagerModel viewManagerModel, CompleteTaskViewModel completeTaskViewModel) {
        this.completeTaskViewModel = completeTaskViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * @param completed the output data passed to the success view
     */
    @Override
    public void prepareSuccessView(CompleteTaskOutputData completed) {

    }
}

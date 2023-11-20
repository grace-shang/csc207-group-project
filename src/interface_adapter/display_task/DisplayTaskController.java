package interface_adapter.display_task;

import use_case.display_task.DisplayTaskDataAccessInterface;
import use_case.display_task.DisplayTaskOutputBoundary;
import use_case.display_task.DisplayTaskOutputData;

public class DisplayTaskController {

    final DisplayTaskDataAccessInterface displayTaskDataAccessObject;
    final DisplayTaskOutputBoundary displayTaskPresenter;

    public DisplayTaskController(DisplayTaskDataAccessInterface displayTaskDataAccessObject, DisplayTaskOutputBoundary displayTaskPresenter) {
        this.displayTaskDataAccessObject = displayTaskDataAccessObject;
        this.displayTaskPresenter = displayTaskPresenter;
    }

    public void execute() {
        DisplayTaskOutputData displayTaskOutputData = new DisplayTaskOutputData(displayTaskDataAccessObject.getAllTasks().keySet(), false, String.join(",", userDataAccessObject.getAllUsers().keySet()));
        displayTaskDataAccessObject.display(displayTaskDataAccessObject.getAllTasks());
        displayTaskPresenter.prepareSuccessView(displayTaskOutputData);

    }

}

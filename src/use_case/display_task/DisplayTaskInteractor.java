package use_case.display_task;

public class DisplayTaskInteractor {

    final DisplayTaskDataAccessInterface displayDataAccessObject;
    final DisplayTaskOutputBoundary taskPresenter;

    /**
     *
     * @param displayDataAccessObject
     * @param taskPresenter
     */
    public DisplayTaskInteractor(DisplayTaskDataAccessInterface displayDataAccessObject, DisplayTaskOutputBoundary taskPresenter) {
        this.displayDataAccessObject = displayDataAccessObject;
        this.taskPresenter = taskPresenter;
    }

    public void execute() {
        DisplayTaskOutputData displayTaskOutputData = new DisplayTaskOutputData(displayTaskDataAccessObject.getAllTasks().keySet(), false, String.join(",", userDataAccessObject.getAllUsers().keySet()));
        displayTaskDataAccessObject.display(displayTaskDataAccessObject.getAllTasks());
        displayTaskPresenter.prepareSuccessView(displayTaskOutputData);

    }

}

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
        DisplayTaskOutputData displayTaskOutputData = new DisplayTaskOutputData(displayDataAccessObject.getAllTasks().keySet(), String.join(",", displayDataAccessObject.getAllTasks().keySet()), false);
        displayDataAccessObject.display(displayDataAccessObject.getAllTasks());
        displayDataAccessObject.save();
        taskPresenter.prepareSuccessView(displayTaskOutputData);

    }

}

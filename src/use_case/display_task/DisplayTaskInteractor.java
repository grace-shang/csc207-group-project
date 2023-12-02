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

//        for displayDataAccessObject.getAllTasks().entrySet()


//        DisplayTaskOutputData displayTaskOutputData = new DisplayTaskOutputData(displayDataAccessObject.getAllTasks().keySet(), displayDataAccessObject.getAllTasks().entrySet(), false);
//        taskPresenter.prepareSuccessView(displayTaskOutputData);
    }

}

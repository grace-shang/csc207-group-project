package use_case.display_task;

import java.util.Collections;

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

        DisplayTaskOutputData displayTaskOutputData = new DisplayTaskOutputData(displayDataAccessObject.getAllTasks().keySet(), Collections.singleton(displayDataAccessObject.getAllTasks().values()), false);
        taskPresenter.prepareSuccessView(displayTaskOutputData);
    }

}

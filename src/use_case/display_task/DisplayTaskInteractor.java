package use_case.display_task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class DisplayTaskInteractor {

    final DisplayTaskDataAccessInterface displayDataAccessObject;
    final DisplayTaskOutputBoundary taskPresenter;

    /**
     * Constructs the display task interactor with a presenter and DAO
     * @param displayDataAccessObject the DAO for the display use case
     * @param taskPresenter the output boundary for the display use case
     */
    public DisplayTaskInteractor(DisplayTaskDataAccessInterface displayDataAccessObject, DisplayTaskOutputBoundary taskPresenter) {
        this.displayDataAccessObject = displayDataAccessObject;
        this.taskPresenter = taskPresenter;
    }

    /**
     * Executes the display tasks by creating the output data and sending it to the presenter
     */
    public void execute() {
        DisplayTaskOutputData displayTaskOutputData = new DisplayTaskOutputData(new ArrayList<String>(displayDataAccessObject.getAllTasks().keySet()), new ArrayList<ArrayList<Object>>(displayDataAccessObject.getAllTasks().values()), false);
        taskPresenter.prepareSuccessView(displayTaskOutputData);
    }

}

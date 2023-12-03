package use_case.display_task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

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
        DisplayTaskOutputData displayTaskOutputData = new DisplayTaskOutputData(new ArrayList<String>(displayDataAccessObject.getAllTasks().keySet()), new ArrayList<ArrayList<Object>>(displayDataAccessObject.getAllTasks().values()), false);
        taskPresenter.prepareSuccessView(displayTaskOutputData);
    }

}

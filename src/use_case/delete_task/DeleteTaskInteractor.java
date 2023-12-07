package use_case.delete_task;

import java.util.ArrayList;

public class DeleteTaskInteractor {
    final DeleteTaskDataAccessInterface deleteDataAccessObject;
    final DeleteTaskOutputBoundary deletePresenter;

    /**
     * Constructor for the DeleteTaskInteractor
     * @param deleteDataAccessObject the delete data access object
     * @param deletePresenter the delete task presenter
     */
    public DeleteTaskInteractor(DeleteTaskDataAccessInterface deleteDataAccessObject, DeleteTaskOutputBoundary deletePresenter) {
        this.deleteDataAccessObject = deleteDataAccessObject;
        this.deletePresenter = deletePresenter;
    }

    /**
     * Executes the presenter with the delete task output data
     */
    public void execute() {
        DeleteTaskOutputData deleteTaskOutputData = new DeleteTaskOutputData(false, deleteDataAccessObject.getAllTasksDelete().keySet());
        deleteDataAccessObject.delete(deleteDataAccessObject.getAllTasksDelete());
        deleteDataAccessObject.save();
        deletePresenter.prepareSuccessView(deleteTaskOutputData);

    }
}

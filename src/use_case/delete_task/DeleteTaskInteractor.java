package use_case.delete_task;

import java.util.ArrayList;

public class DeleteTaskInteractor {
    final DeleteTaskDataAccessInterface deleteDataAccessObject;
    final DeleteTaskOutputBoundary deletePresenter;

    public DeleteTaskInteractor(DeleteTaskDataAccessInterface deleteDataAccessObject, DeleteTaskOutputBoundary deletePresenter) {
        this.deleteDataAccessObject = deleteDataAccessObject;
        this.deletePresenter = deletePresenter;
    }

    public void execute() {
        DeleteTaskOutputData deleteTaskOutputData = new DeleteTaskOutputData(false, deleteDataAccessObject.getAllTasksDelete().keySet());
        deleteDataAccessObject.delete(deleteDataAccessObject.getAllTasksDelete());
        deleteDataAccessObject.save();
        deletePresenter.prepareSuccessView(deleteTaskOutputData);

    }
}

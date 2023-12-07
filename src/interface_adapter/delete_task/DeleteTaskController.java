package interface_adapter.delete_task;

import use_case.delete_task.DeleteTaskInteractor;

public class DeleteTaskController {

    final DeleteTaskInteractor deleteUseCaseInteractor;

    /**
     * Constructor for DeleteTaskController
     * @param deleteUseCaseInteractor the interactor for the delete use case
     */
    public DeleteTaskController(DeleteTaskInteractor deleteUseCaseInteractor) {
        this.deleteUseCaseInteractor = deleteUseCaseInteractor;
    }

    /**
     * Executes the delete use case interactor
     */
    public void execute() {
        deleteUseCaseInteractor.execute();
    }

}

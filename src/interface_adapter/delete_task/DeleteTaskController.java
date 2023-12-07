package interface_adapter.delete_task;

import use_case.delete_task.DeleteTaskInteractor;

public class DeleteTaskController {

    final DeleteTaskInteractor deleteUseCaseInteractor;

    public DeleteTaskController(DeleteTaskInteractor deleteUseCaseInteractor) {
        this.deleteUseCaseInteractor = deleteUseCaseInteractor;
    }

    public void execute() {
        deleteUseCaseInteractor.execute();
    }

}

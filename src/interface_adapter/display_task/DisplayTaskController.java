package interface_adapter.display_task;
import use_case.display_task.DisplayTaskInteractor;


public class DisplayTaskController {

    final DisplayTaskInteractor displayUseCaseInteractor;

    public DisplayTaskController(DisplayTaskInteractor displayUseCaseInteractor) {
        this.displayUseCaseInteractor = displayUseCaseInteractor;
    }

    public void execute() {
        displayUseCaseInteractor.execute();
    }

}

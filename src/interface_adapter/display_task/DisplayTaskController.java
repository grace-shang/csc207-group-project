package interface_adapter.display_task;
import use_case.display_task.DisplayTaskInteractor;


public class DisplayTaskController {

    final DisplayTaskInteractor displayUseCaseInteractor;

    /**
     * Constructs the controller for the display use case
     * @param displayUseCaseInteractor the interactor for the display use case
     */
    public DisplayTaskController(DisplayTaskInteractor displayUseCaseInteractor) {
        this.displayUseCaseInteractor = displayUseCaseInteractor;
    }

    /**
     * Executes the interactor for the display use case
     */
    public void execute() {
        displayUseCaseInteractor.execute();
    }

}

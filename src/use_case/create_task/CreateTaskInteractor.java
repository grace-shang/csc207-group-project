package use_case.create_task;

public class CreateTaskInteractor {
    final CreateTaskDataAccessInterface userDataAccessObject;

    final CreateTaskOutputBoundary userPresenter;

    public CreateTaskInteractor(CreateTaskDataAccessInterface userDataAccessObject,
                                CreateTaskOutputBoundary userPresenter){
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
    }



}

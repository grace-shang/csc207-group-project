package use_case.create_task;

import entity.TaskFactory;
import api.Todo;

public class CreateTaskInteractor {
    final CreateTaskDataAccessInterface userDataAccessObject;

    final CreateTaskOutputBoundary userPresenter;

    final TaskFactory taskFactory;

    public CreateTaskInteractor(CreateTaskDataAccessInterface userDataAccessObject,
                                CreateTaskOutputBoundary userPresenter,
                                TaskFactory taskFactory){
        this.userDataAccessObject = userDataAccessObject;
        this.userPresenter = userPresenter;
        this.taskFactory = taskFactory;
    }

    public void execute(CreateTaskInputData createTaskInputData){
    }



}

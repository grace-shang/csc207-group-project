package use_case.create_task;

import entity.TaskI;
import entity.TaskFactory;

public class CreateTaskInteractor implements CreateTaskInputBoundary{
    final CreateTaskDataAccessInterface taskDataAccessObject;

    final CreateTaskOutputBoundary userPresenter;

    final TaskFactory taskFactory;


    public CreateTaskInteractor(CreateTaskDataAccessInterface taskDataAccessObject,
                                CreateTaskOutputBoundary userPresenter,
                                TaskFactory taskFactory){
        this.taskDataAccessObject = taskDataAccessObject;
        this.userPresenter = userPresenter;
        this.taskFactory = taskFactory;
    }

    public void execute(CreateTaskInputData createTaskInputData){
        TaskI taskI = taskFactory.create(createTaskInputData.getCreateTask(), false);
        taskDataAccessObject.save(taskI);

        taskDataAccessObject.addTask(taskI);

        CreateTaskOutputData createTaskOutputData = new CreateTaskOutputData(taskI.getName());
        userPresenter.prepareSuccessView(createTaskOutputData);
    }



}
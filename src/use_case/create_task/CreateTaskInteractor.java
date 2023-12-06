package use_case.create_task;

import entity.TaskI;
import entity.TaskFactory;

public class CreateTaskInteractor implements CreateTaskInputBoundary{
    final CreateTaskDataAccessInterface taskDataAccessObject;

    final CreateTaskOutputBoundary taskPresenter;

    final TaskFactory taskFactory;


    public CreateTaskInteractor(CreateTaskDataAccessInterface taskDataAccessObject,
                                CreateTaskOutputBoundary taskPresenter,
                                TaskFactory taskFactory){
        this.taskDataAccessObject = taskDataAccessObject;
        this.taskPresenter = taskPresenter;
        this.taskFactory = taskFactory;
    }

    public void execute(CreateTaskInputData createTaskInputData){
        if (createTaskInputData.getCreateTask() == ""){
            taskPresenter.prepareFailView("An Empty Task Can't Be Added");
        } else{
            long taskId = taskDataAccessObject.addTask(createTaskInputData.getCreateTask());
            TaskI taskI = taskFactory.create(createTaskInputData.getCreateTask(), false, taskId);
            taskI.setTaskId(taskId);
            taskDataAccessObject.save(taskI);

            CreateTaskOutputData createTaskOutputData = new CreateTaskOutputData(taskI.getName());
            taskPresenter.prepareSuccessView(createTaskOutputData);
        }
    }

}
package use_case.create_task;

import entity.TaskI;
import entity.TaskFactory;

public class CreateTaskInteractor implements CreateTaskInputBoundary{
    final CreateTaskDataAccessInterface taskDataAccessObject;

    final CreateTaskOutputBoundary taskPresenter;

    final TaskFactory taskFactory;


    /**
     * constructs create task interactor
     * @param taskDataAccessObject
     * @param taskPresenter is initialized with the create task output boundary
     * @param taskFactory is initialized with the task factory
     */
    public CreateTaskInteractor(CreateTaskDataAccessInterface taskDataAccessObject,
                                CreateTaskOutputBoundary taskPresenter,
                                TaskFactory taskFactory){
        this.taskDataAccessObject = taskDataAccessObject;
        this.taskPresenter = taskPresenter;
        this.taskFactory = taskFactory;
    }

    /**
     * @param createTaskInputData executes with the create task input data first checking whether the task can be added,
     *                            setting up the fail view if not, setting up the success view otherwise.
     */
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
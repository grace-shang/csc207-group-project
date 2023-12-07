package app;

import entity.AllTaskFactory;
import interface_adapter.complete_task.CompleteTaskController;
import interface_adapter.complete_task.CompleteTaskPresenter;
import interface_adapter.complete_task.CompleteTaskViewModel;
import interface_adapter.create_task.CreateTaskController;
import interface_adapter.create_task.CreateTaskPresenter;
import interface_adapter.create_task.CreateTaskViewModel;
import interface_adapter.delete_task.DeleteTaskController;
import interface_adapter.delete_task.DeleteTaskPresenter;
import interface_adapter.delete_task.DeleteTaskViewModel;
import interface_adapter.display_task.DisplayTaskController;
import interface_adapter.display_task.DisplayTaskPresenter;
import interface_adapter.display_task.DisplayTaskViewModel;
import use_case.complete_task.CompleteTaskInputBoundary;
import use_case.complete_task.CompleteTaskInteractor;
import use_case.complete_task.CompleteTaskOutputBoundary;
import use_case.complete_task.CompleteTaskDataAccessInterface;
import use_case.create_task.CreateTaskInputBoundary;
import use_case.create_task.CreateTaskInteractor;
import use_case.create_task.CreateTaskOutputBoundary;
import use_case.create_task.CreateTaskDataAccessInterface;
//import use_case.delete_task.DeleteTaskInteractor;
//import use_case.delete_task.DeleteTaskOutputBoundary;
//import use_case.delete_task.DeleteTaskDataAccessInterface;
import entity.TaskFactory;
import use_case.delete_task.DeleteTaskDataAccessInterface;
import use_case.delete_task.DeleteTaskInteractor;
import use_case.delete_task.DeleteTaskOutputBoundary;
import use_case.display_task.DisplayTaskDataAccessInterface;
import use_case.display_task.DisplayTaskInteractor;
import use_case.display_task.DisplayTaskOutputBoundary;
import view.TaskView;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.io.IOException;

public class TaskUseCaseFactory {

    private TaskUseCaseFactory() {}

    /**
     * This method constructs the Task View
     * @param viewManagerModel the view manager model
     * @param deleteTaskViewModel the delete task view model
     * @param deleteTaskDataAccessInterface the delete task data access interface
     * @param createTaskViewModel the create task view model
     * @param createTaskDataAccessInterface the create task data access interface
     * @param completeTaskViewModel the complete task view model
     * @param completeTaskDataAccessInterface the complete task data access interface
     * @param displayTaskViewModel the display task view model
     * @param displayTaskDataAccessInterface the display task data access interface
     * @return the constructed Task View based on the parameters
     */
    public static TaskView create(
            ViewManagerModel viewManagerModel, DeleteTaskViewModel deleteTaskViewModel, DeleteTaskDataAccessInterface deleteTaskDataAccessInterface,CreateTaskViewModel createTaskViewModel, CreateTaskDataAccessInterface createTaskDataAccessInterface,
            CompleteTaskViewModel completeTaskViewModel, CompleteTaskDataAccessInterface completeTaskDataAccessInterface, DisplayTaskViewModel displayTaskViewModel, DisplayTaskDataAccessInterface displayTaskDataAccessInterface) {

        try {
            CreateTaskController createTaskController = createTaskUseCase(viewManagerModel, createTaskViewModel, createTaskDataAccessInterface);
            CompleteTaskController completeTaskController =  createCompleteUseCase(viewManagerModel, completeTaskViewModel, completeTaskDataAccessInterface);
            DisplayTaskController displayTaskController = createDisplayUseCase(viewManagerModel, displayTaskViewModel, displayTaskDataAccessInterface);
            DeleteTaskController deleteTaskController =  createDeleteUseCase(viewManagerModel, deleteTaskViewModel, deleteTaskDataAccessInterface);
            return new TaskView(createTaskController, createTaskViewModel, completeTaskController, completeTaskViewModel, displayTaskViewModel, displayTaskController, deleteTaskViewModel, deleteTaskController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open task data file.");
        }

        return null;
    }

    private static CreateTaskController createTaskUseCase(ViewManagerModel viewManagerModel, CreateTaskViewModel createTaskViewModel, CreateTaskDataAccessInterface userDataAccessObject) throws IOException {

        CreateTaskOutputBoundary createTaskOutputBoundary = new CreateTaskPresenter(createTaskViewModel, viewManagerModel);

        TaskFactory taskFactory = new AllTaskFactory();

        CreateTaskInputBoundary userCreateTaskInteractor = new CreateTaskInteractor(
                userDataAccessObject, createTaskOutputBoundary, taskFactory);

        return new CreateTaskController(userCreateTaskInteractor);
    }

    private static DeleteTaskController createDeleteUseCase(ViewManagerModel viewManagerModel, DeleteTaskViewModel deleteViewModel, DeleteTaskDataAccessInterface taskDataAccessObject) throws IOException {
        DeleteTaskOutputBoundary deleteOutputBoundary = new DeleteTaskPresenter(deleteViewModel, viewManagerModel);

        DeleteTaskInteractor deleteClearInteractor = new DeleteTaskInteractor(
                taskDataAccessObject, deleteOutputBoundary);

        return new DeleteTaskController(deleteClearInteractor);
    }

    private static CompleteTaskController createCompleteUseCase(ViewManagerModel viewManagerModel, CompleteTaskViewModel completeTaskViewModel,
                                                                CompleteTaskDataAccessInterface completeTaskDataObject) throws IOException {
        // Notice how we pass this method's parameters to the Presenter.
        CompleteTaskOutputBoundary completeOutputBoundary = new CompleteTaskPresenter(viewManagerModel, completeTaskViewModel);

        CompleteTaskInputBoundary completeInteractor = new CompleteTaskInteractor(completeTaskDataObject, completeOutputBoundary);

        return new CompleteTaskController(completeInteractor);
    }

    private static DisplayTaskController createDisplayUseCase(ViewManagerModel viewManagerModel, DisplayTaskViewModel displayTaskViewModel, DisplayTaskDataAccessInterface displayTaskDataAccessInterface) {
        DisplayTaskOutputBoundary displayOutputBoundary = new DisplayTaskPresenter(displayTaskViewModel, viewManagerModel);

        DisplayTaskInteractor displayInteractor = new DisplayTaskInteractor(
                displayTaskDataAccessInterface, displayOutputBoundary);

        return new DisplayTaskController(displayInteractor);
    }

}

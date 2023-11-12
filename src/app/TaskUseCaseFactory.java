package app;

import interface_adapter.complete_task.CompleteTaskController;
import interface_adapter.complete_task.CompleteTaskPresenter;
import interface_adapter.complete_task.CompleteTaskViewModel;
import interface_adapter.create_task.CreateTaskController;
import interface_adapter.create_task.CreateTaskPresenter;
import interface_adapter.create_task.CreateTaskViewModel;
import interface_adapter.delete_task.DeleteTaskController;
import interface_adapter.delete_task.DeleteTaskPresenter;
import interface_adapter.delete_task.DeleteTaskViewModel;
import use_case.complete_task.CompleteTaskInteractor;
import use_case.complete_task.CompleteTaskOutputBoundary;
import use_case.complete_task.CompleteTaskDataAccessInterface;
import use_case.create_task.CreateTaskInputBoundary;
import use_case.create_task.CreateTaskInteractor;
import use_case.create_task.CreateTaskOutputBoundary;
import use_case.create_task.CreateTaskDataAccessInterface;
import use_case.delete_task.DeleteTaskInteractor;
import use_case.delete_task.DeleteTaskOutputBoundary;
import use_case.delete_task.DeleteTaskDataAccessInterface;
import entity.CommonTaskFactory;
import entity.TaskFactory;
import view.TaskView;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.io.IOException;

public class TaskUseCaseFactory {

    /** Prevent instantiation. */
    private TaskUseCaseFactory() {}

    public static TaskView create(
            ViewManagerModel viewManagerModel, DeleteTaskViewModel deleteTaskViewModel, CreateTaskViewModel createTaskViewModel, CompleteTaskViewModel completeTaskViewModel, DeleteTaskDataAccessInterface taskDataAccessObjectDelete, CreateTaskDataAccessInterface taskDataAccessObjectCreate, CompleteTaskDataAccessInterface taskDataAccessObjectComplete) {

        try {
            CreateTaskController createTaskController = createTaskUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject);
            CompleteTaskController completeTaskController =  createCompleteUseCase(viewManagerModel, clearViewModel, userDataAccessObjectClear);
            DeleteTaskController deleteTaskController =  createDeleteUseCase(viewManagerModel, clearViewModel, userDataAccessObjectClear);
            return new TaskView(createTaskController, createTaskViewModel, completeTaskViewModel, completeTaskController, deleteTaskViewModel, deleteTaskController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open task data file.");
        }

        return null;
    }

    private static CreateTaskController createTaskUseCase(ViewManagerModel viewManagerModel, SignupViewModel signupViewModel, LoginViewModel loginViewModel, SignupUserDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel, signupViewModel, loginViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new SignupController(userSignupInteractor);
    }

    private static DeleteTaskController createDeleteUseCase(ViewManagerModel viewManagerModel, ClearViewModel clearViewModel, ClearUserDataAccessInterface userDataAccessObject) throws IOException {
        // Notice how we pass this method's parameters to the Presenter.
        ClearOutputBoundary clearOutputBoundary = new ClearPresenter(clearViewModel, viewManagerModel);

        ClearInteractor userClearInteractor = new ClearInteractor(
                userDataAccessObject, clearOutputBoundary);

        return new ClearController(userClearInteractor);
    }

    private static CompleteTaskController createCompleteUseCase(ViewManagerModel viewManagerModel, ClearViewModel clearViewModel, ClearUserDataAccessInterface userDataAccessObject) throws IOException {
        // Notice how we pass this method's parameters to the Presenter.
        ClearOutputBoundary clearOutputBoundary = new ClearPresenter(clearViewModel, viewManagerModel);

        ClearInteractor userClearInteractor = new ClearInteractor(
                userDataAccessObject, clearOutputBoundary);

        return new ClearController(userClearInteractor);
    }

}
package app;

import api.ToDoList;
import api.Todo;
import data_access.FileTaskDataAccessObject;
import entity.AllTaskFactory;
import interface_adapter.complete_task.CompleteTaskViewModel;
import interface_adapter.create_task.CreateTaskViewModel;
import interface_adapter.delete_task.DeleteTaskState;
import interface_adapter.delete_task.DeleteTaskViewModel;
import interface_adapter.display_task.DisplayTaskViewModel;
import use_case.create_task.CreateTaskDataAccessInterface;
import use_case.complete_task.CompleteTaskDataAccessInterface;
import use_case.display_task.DisplayTaskDataAccessInterface;
import use_case.display_task.DisplayTaskOutputData;
import view.TaskView;
import view.ViewManager;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        JFrame application = new JFrame("Tasks");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        CreateTaskViewModel createTaskViewModel = new CreateTaskViewModel();
        CompleteTaskViewModel completeTaskViewModel = new CompleteTaskViewModel();
        DeleteTaskViewModel deleteTaskViewModel = new DeleteTaskViewModel();
        DisplayTaskViewModel displayTaskViewModel = new DisplayTaskViewModel();

        FileTaskDataAccessObject taskDataAccessObject;
        Todo todo = new ToDoList();

        try {
            taskDataAccessObject = new FileTaskDataAccessObject("tasks.csv", new AllTaskFactory(), todo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TaskView taskView = TaskUseCaseFactory.create(viewManagerModel, deleteTaskViewModel, taskDataAccessObject,createTaskViewModel, taskDataAccessObject, completeTaskViewModel, taskDataAccessObject, displayTaskViewModel, taskDataAccessObject);
        views.add(taskView, taskView.viewName);

        viewManagerModel.setActiveView(taskView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
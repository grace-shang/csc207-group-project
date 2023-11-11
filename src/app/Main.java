package app;

import data_access.FileTaskDataAccessObject;
import interface_adapter.complete_task.CompleteTaskController;
import src.interface_adapter.complete_task.CompleteTaskViewModel;
import interface_adapter.create_task.CreateTaskViewModel;
import use_case.create_task.CreateTaskDataAccessInterface;
import view.TaskView;
import view.ViewManager;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Tasks");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        CreateTaskViewModel createTaskViewModel = new CreateTaskViewModel();
        CompleteTaskViewModel completeTaskViewModel = new CompleteTaskViewModel();

//        FileTaskDataAccessObject taskDataAccessObject;
//        try {
//            taskDataAccessObject = new FileTaskDataAccessObject("./users.csv", new CommonUserFactory());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        TaskView taskView = TaskUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, userDataAccessObject, clearViewModel);
        views.add(taskView, taskView.viewName);

        viewManagerModel.setActiveView(taskView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
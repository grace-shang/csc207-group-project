package app;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import src.entity.CourseTask;
import src.entity.PersonalTask;
import src.entity.Course;
import src.interface_adapter.complete_task.CompleteTaskViewModel;
import interface_adapter.create_task.CreateTaskViewModel;
import src.interface_adapter.delete_task.DeleteTaskViewModel;

public class Main {
    // The main application window.
    JFrame application = new JFrame("Login Example");
    application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    CardLayout cardLayout = new CardLayout();

    // The various View objects. Only one view is visible at a time.
    JPanel views = new JPanel(cardLayout);
        application.add(views);

    // This keeps track of and manages which view is currently showing.
    ViewManagerModel viewManagerModel = new ViewManagerModel();
    new viewManager(views, cardLayout, viewManagerModel);

    // The view models
    CreateTaskViewModel createTaskViewModel = new CreateTaskViewModel();
    CompleteTaskViewModel completeTaskViewModel = new CompleteTaskViewModel();
    DeleteTaskViewModel deleteTaskViewModel = new DeleteTaskViewModel();

    FileUserDataAccessObject userDataAccessObject;
        try

    {
        userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
    } catch(
    IOException e)

    {
        throw new RuntimeException(e);
    }

    SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, userDataAccessObject, clearViewModel);
        views.add(signupView,signupView.viewName);
}



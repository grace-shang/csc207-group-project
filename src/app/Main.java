package app;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import src.interface_adapter.TaskViewModel;
import use_case.login.LoginUserDataAccessInterface;

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
        new ViewManager(views, cardLayout, viewManagerModel);

    // The data for the views, such as username and password, are in the ViewModels.
    // This information will be changed by a presenter object that is reporting the
    // results from the use case. The ViewModels are observable, and will
    // be observed by the Views.
    LoginViewModel loginViewModel = new LoginViewModel();
    LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
    SignupViewModel signupViewModel = new SignupViewModel();
    ClearViewModel clearViewModel = new ClearViewModel();

    FileUserDataAccessObject userDataAccessObject;
        try {
        userDataAccessObject = new FileUserDataAccessObject("./users.csv", new CommonUserFactory());
    } catch (
    IOException e) {
        throw new RuntimeException(e);
    }

    SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, userDataAccessObject, clearViewModel);
        views.add(signupView, signupView.viewName);

    LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

    LoggedInView loggedInView = new LoggedInView(loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);


}

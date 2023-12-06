package interface_adapter.create_task;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class CreateTaskViewModel extends ViewModel {

    public static final String CREATE_BUTTON_LABEL = "Create Task";

    public static final String TITLE_LABEL = "Tasks";

    public static final String CREATE_TASK_LABEL = "Write New Task";

    public static final String CREATE_TASK_PROJECT_LABEL = "Write Project Name";


    private CreateTaskState state = new CreateTaskState();

    /**
     * labels the view model create
     */
    public CreateTaskViewModel(){super("create");}
    public CreateTaskState getState(){return state;}

    /**
     * @param state sets the state of the view model
     */
    public void setState(CreateTaskState state){this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * firepropertychange sends the message to update the state in task view
     */
    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);}

    /**
     * @param listener adds the property change listener to that the view model will change
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) { support.addPropertyChangeListener(listener);
    }
}
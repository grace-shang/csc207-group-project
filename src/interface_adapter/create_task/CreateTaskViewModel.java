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

    public CreateTaskViewModel(){super("create");}
    public CreateTaskState getState(){return state;}

    public void setState(CreateTaskState state){this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);}

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) { support.addPropertyChangeListener(listener);
    }
}
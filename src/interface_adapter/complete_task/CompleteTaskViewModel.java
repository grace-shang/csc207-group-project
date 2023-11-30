package interface_adapter.complete_task;

import interface_adapter.ViewModel;
import interface_adapter.create_task.CreateTaskState;
import view.TaskView;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CompleteTaskViewModel extends ViewModel {

    private CreateTaskState createTaskState = new CreateTaskState();
    private CompleteTaskState state = new CompleteTaskState(createTaskState);
    public CompleteTaskViewModel() {
        super("complete view");
    }

    public void addPropertyChangeListener(TaskView taskView) {
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CompleteTaskState getState() {
        return state;
    }

    public void setState(CompleteTaskState completeTaskState){
        this.state = completeTaskState;
    }
}

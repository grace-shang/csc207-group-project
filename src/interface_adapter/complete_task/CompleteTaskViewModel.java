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

    /**
     * @param taskView
     */
    public void addPropertyChangeListener(TaskView taskView) {
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * changes the state
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a listener for when a property has been changed
     * @param listener the property changed listener
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Getter for the state
     * @return the state of the view model
     */
    public CompleteTaskState getState() {
        return state;
    }

    /**
     * Setter for the state
     * @param completeTaskState the new state to change into
     */
    public void setState(CompleteTaskState completeTaskState){
        this.state = completeTaskState;
    }
}

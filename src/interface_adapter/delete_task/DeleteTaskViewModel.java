package interface_adapter.delete_task;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeleteTaskViewModel extends ViewModel {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private DeleteTaskState state = new DeleteTaskState();

    /**
     * Constructor for the DeleteTaskViewModel
     */
    public DeleteTaskViewModel() {super("delete");}

    /**
     * Setter for the delete state
     * @param state the new delete task state
     */
    public void setState(DeleteTaskState state) {
        this.state = state;
    }

    /**
     * Fires the property change for the delete use case
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("delete", null, this.state);
    }

    /**
     * Adds a property change listener
     * @param listener the property change listener to add
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Getter for the delete state
     * @return the delete task state
     */
    public DeleteTaskState getState() {
        return state;
    }

}

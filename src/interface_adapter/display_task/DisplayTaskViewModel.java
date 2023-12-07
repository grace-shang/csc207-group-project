package interface_adapter.display_task;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DisplayTaskViewModel extends ViewModel {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private DisplayTaskState state = new DisplayTaskState();

    /**
     * Constructor for DisplayTaskViewModel
     */
    public DisplayTaskViewModel() {super("display");}

    /**
     * Setter for the display state
     * @param state the new display state
     */
    public void setState(DisplayTaskState state) {
        this.state = state;
    }

    /**
     * Fire property change for the display use case
     */
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("display", null, this.state);
    }

    /**
     * Add property change listener
     * @param listener the property change listener to be added
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Getter for the display state
     * @return the display task state
     */
    public DisplayTaskState getState() {
        return state;
    }

}

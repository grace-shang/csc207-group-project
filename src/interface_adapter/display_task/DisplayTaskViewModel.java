package interface_adapter.display_task;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DisplayTaskViewModel extends ViewModel {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private DisplayTaskState state = new DisplayTaskState();

    /**
     *
     */
    public DisplayTaskViewModel() {super("display");}

    public void setState(DisplayTaskState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("display", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public DisplayTaskState getState() {
        return state;
    }

}

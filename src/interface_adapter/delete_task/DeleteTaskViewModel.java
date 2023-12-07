package interface_adapter.delete_task;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class DeleteTaskViewModel extends ViewModel {

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private DeleteTaskState state = new DeleteTaskState();

    public DeleteTaskViewModel() {super("delete");}

    public void setState(DeleteTaskState state) {
        this.state = state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("delete", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public DeleteTaskState getState() {
        return state;
    }

}

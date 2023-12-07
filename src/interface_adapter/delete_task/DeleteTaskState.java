package interface_adapter.delete_task;

import java.util.Set;

public class DeleteTaskState {

    private Set<String> tasks;

    /**
     * Constructor for the DeleteTaskState
     * @param copy a copy of the delete use case state
     */
    public DeleteTaskState(DeleteTaskState copy) {
        tasks = copy.tasks;
    }

    /**
     * Constructor for the DeleteTaskState
     */
    public DeleteTaskState() {
    }

    /**
     * Getter for the tasks
     * @return the active task names
     */
    public Set<String> getTasks() {return tasks;}

    /**
     * Setter for the tasks
     * @param tasks the new task names
     */
    public void setTasks(Set<String> tasks) {this.tasks = tasks;}

}

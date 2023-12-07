package interface_adapter.delete_task;

import java.util.Set;

public class DeleteTaskState {

    private Set<String> tasks = null;

    public DeleteTaskState(DeleteTaskState copy) {
        tasks = copy.tasks;
    }

    public DeleteTaskState() {
    }

    public Set<String> getTasks() {return tasks;}

    public void setTasks(Set<String> tasks) {this.tasks = tasks;}

}

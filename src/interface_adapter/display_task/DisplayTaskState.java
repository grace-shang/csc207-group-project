package interface_adapter.display_task;

import java.util.Set;

public class DisplayTaskState {

    private Set<String> tasks = null;

    private Set<Boolean> completions = null;

    public DisplayTaskState(DisplayTaskState copy) {
        this.tasks = copy.tasks;
        this.completions = copy.completions;
    }

    public DisplayTaskState() {
    }

    public Set<String> getTasks() {return tasks;}

    public void setTasks(Set<String> tasks) {this.tasks = tasks;}

    public Set<Boolean> getCompletions() {
        return completions;
    }

    public void setCompletions(Set<Boolean> completions) {
        this.completions = completions;
    }

}

package use_case.display_task;

import java.util.Set;

public class DisplayTaskOutputData {

    private final Set<String> tasks;
    private final Set<Boolean> completions;

    private boolean useCaseFailed;

    public DisplayTaskOutputData(Set<String> tasks, Set<Boolean> completions, boolean useCaseFailed) {
        this.tasks = tasks;
        this.completions = completions;
        this.useCaseFailed = useCaseFailed;
    }

    public Set<String> getAllTasks() {return tasks;}

    public Set<Boolean> getCompletions() {
        return completions;
    }


}

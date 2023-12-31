package use_case.display_task;

import java.util.ArrayList;
import java.util.Collection;

public class DisplayTaskOutputData {

    private final ArrayList<String> tasks;
    private final ArrayList<Boolean> taskInfo;

    private boolean useCaseFailed;

    /**
     * Constructs the display task output data
     * @param tasks A list of the task names in the CSV file
     * @param taskInfo A list containing booleans that represent the completion of each task
     * @param useCaseFailed A boolean determining whether the use case failed
     */
    public DisplayTaskOutputData(ArrayList<String> tasks, ArrayList<Boolean> taskInfo, boolean useCaseFailed) {
        this.tasks = tasks;
        this.taskInfo = taskInfo;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Getter for tasks
     * @return the list of task names
     */
    public ArrayList<String> getAllTasks() {return tasks;}

    /**
     * Getter for task information
     * @return the list of booleans with completion status
     */
    public ArrayList<Boolean> getTaskInfo() {
        return taskInfo;
    }


}

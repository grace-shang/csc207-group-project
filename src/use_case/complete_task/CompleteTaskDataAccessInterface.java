package use_case.complete_task;

import entity.TaskI;

import java.io.IOException;

public interface CompleteTaskDataAccessInterface {

    // Complete task method
    void complete(TaskI task) throws IOException;

    boolean existsByName(String taskName);

    TaskI getTask(String taskName);
}

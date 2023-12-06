package use_case.complete_task;

import entity.TaskI;

import java.io.IOException;

public interface CompleteTaskDataAccessInterface {
    void complete(String task) throws IOException;

    boolean existsByName(String taskName);

    TaskI getTask(String taskName);

    void update(String taskName);
}

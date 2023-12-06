package use_case.create_task;

import entity.TaskI;

public interface CreateTaskDataAccessInterface {
    void save(TaskI taskI);

    long addTask(String taskName);

    boolean existByName(String identifier);
}

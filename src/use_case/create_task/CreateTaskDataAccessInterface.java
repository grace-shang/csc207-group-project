package use_case.create_task;

import entity.TaskI;

public interface CreateTaskDataAccessInterface {
    void save(TaskI taskI);

    void addTask(TaskI taskI);
}

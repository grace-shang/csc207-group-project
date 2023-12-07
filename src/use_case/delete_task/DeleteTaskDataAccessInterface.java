package use_case.delete_task;

import entity.TaskI;

import java.util.Map;

public interface DeleteTaskDataAccessInterface {
    void delete(Map<String, TaskI> tasks);

    Map<String, TaskI> getAllTasksDelete();

    void save();
}

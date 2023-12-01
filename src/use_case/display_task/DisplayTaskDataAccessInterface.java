package use_case.display_task;

import entity.TaskI;

import java.util.Map;

public interface DisplayTaskDataAccessInterface {
    void display(Map<String, TaskI> prevTasks);

    Map<String, TaskI> getAllTasks();

    void save();


}

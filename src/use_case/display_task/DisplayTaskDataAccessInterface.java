package use_case.display_task;

import entity.TaskI;

import java.util.Map;

public interface DisplayTaskDataAccessInterface {
    void display(Map<String, TaskI> accounts);

    Map<String, TaskI> getAllTasks();

}

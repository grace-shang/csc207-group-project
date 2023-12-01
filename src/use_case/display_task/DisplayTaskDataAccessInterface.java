package use_case.display_task;

import entity.TaskI;

import java.util.Map;

public interface DisplayTaskDataAccessInterface {

    Map<String, TaskI> getAllTasks();



}

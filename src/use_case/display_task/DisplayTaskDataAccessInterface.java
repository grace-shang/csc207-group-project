package use_case.display_task;

import entity.TaskI;

import java.util.Map;
import java.util.Set;

public interface DisplayTaskDataAccessInterface {

    Map<String, Set<Object>> getAllTasks();

}

package use_case.create_task;

import entity.TaskI;

public interface CreateTaskDataAccessInterface {
    /**
     * @param taskI interface that calls the Data Access Interface to save the task
     */
    void save(TaskI taskI);

    /**
     * @param taskName interface that calls the Data Access Interface to add the task
     * @return
     */
    long addTask(String taskName);

    /**
     * @param identifier interface that calls the Data Access Interface to get the method to check whether the task exists in memory
     * @return
     */
    boolean existByName(String identifier);
}

package api;


public interface Todo {

    long addTask(String projectName, String taskName);

    void completeTask(String projectName, String taskName, long taskID);

    void deleteTask(String projectName, String taskName, long taskID);

}


package api;


import org.json.JSONException;

public interface Todo {

    long addTask(String projectName, String taskName);

//    Projects getProject(String name);
//
//    Projects logProject(String name, boolean favourite) throws JSONException;

    void completeTask(String projectName, String taskName, long taskID);

}


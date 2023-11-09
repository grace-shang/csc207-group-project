package api;


import org.json.JSONException;

public interface Todo {

    void addTask(String projectName, String taskName);

    Projects getProject(String name);

    Projects logProject(String name, boolean favourite) throws JSONException;


}


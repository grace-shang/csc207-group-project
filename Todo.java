package api;

import entity.Projects;
import org.json.JSONException;
public interface Todo {

    Projects getProject(String name);

    Projects logProject(String name, boolean favourite) throws JSONException;


}


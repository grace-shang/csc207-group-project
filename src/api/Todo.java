package entity.api;
public interface Todo {

    Projects getProject(String name);

    Projects logProject(String name, boolean favourite) throws JSONException;


}


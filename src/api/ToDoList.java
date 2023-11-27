package api;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.IOException;

public class ToDoList implements Todo{
    @Override

    public Projects getProject(String name) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder()
                .url(String.format("@doist/todoist-api-typescript", name)).build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            JSONObject project = responseBody.getJSONObject("project");
            return Projects.builder()
                    .name(Projects.getName())
                    .favourite(Projects.getFavourite())
                    .build();

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Projects logProject(String name, boolean favourite) throws JSONException {
        return null;
    }

    @Override
    public void addTask(String projectName, String taskName) {
        JSONObject task = new JSONObject();
        try{
            task.put("content", "A new task for test");
        }

        catch (JSONException e){
            throw new RuntimeException(e);
        }

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), task.toString());

        Request request = new Request.Builder()
                .url("https://api.todoist.com/rest/v2/tasks")
                .post(requestBody)
                .build();

        try{
            Response response = client.newCall(request).execute();
            System.out.println(response);
        } catch (IOException e){
            throw new RuntimeException();
        }
    }
}

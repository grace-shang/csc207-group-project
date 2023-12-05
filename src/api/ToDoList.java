package api;

import okhttp3.*;
import okio.BufferedSink;
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

//    @Override
//    public void deleteTask(String projectName, String taskName) {
//        OkHttpClient client = new OkHttpClient().newBuilder()
//                .build();
//        MediaType mediaType = MediaType.parse("application/json");
//        JSONObject requestBody = new JSONObject();
//        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
//        Request request = new Request.Builder()
//                .url("https://api.todoist.com/rest/v2/tasks/2995104339")
//                .post(body)
//                .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            System.out.println(response);
//            JSONObject responseBody = new JSONObject(response.body().string());
//
//            if (responseBody.getInt("status_code") == 204) {
//                return;
//            } else {
//                throw new RuntimeException(responseBody.getString("Error"));
//            }
//        }
//        catch (IOException | JSONException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public void completeTask(String projectName, String taskName, Integer taskID) {
        JSONObject task = new JSONObject();
        try{
            task.put("id", taskID);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), task.toString());

        Request request = new Request.Builder()
                .url("https://api.todoist.com/rest/v1/tasks/" + task.get("id") + "/close")
                .post(requestBody)
                .header("Authorization", "Bearer b1aa35c9378d5217bc2745afc04aa0fcae246844")
                .build();

        try{
            Response response = client.newCall(request).execute();
            System.out.println(response);
        } catch (IOException e){
            throw new RuntimeException();
        }
    }

    @Override
    public void addTask(String projectName, String taskName) {
        JSONObject task = new JSONObject();
        try{
            task.put("content", "task name");
        }

        catch (JSONException e){
            throw new RuntimeException(e);
        }

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String jsonBody = "{\"content\": " + task.get("content") + "}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonBody);
        Request request = new Request.Builder()
                .url("https://api.todoist.com/rest/v2/tasks")
                .post(requestBody)
                .header("Content-Type", "application/json")
                .header("X-Request-Id", java.util.UUID.randomUUID().toString())
                .header("Authorization", "Bearer b1aa35c9378d5217bc2745afc04aa0fcae246844")
                .build();

        try{
            Response response = client.newCall(request).execute();
            System.out.println(response);

            JSONObject jsonResponse = new JSONObject(response);

            // The issue is it doesn't recognize the key "id" even though it should return one
            // I think the response doesn't respond like it does on Todoist but I'll look when
            // I'm back from classes
            int taskID = jsonResponse.getInt("id");

            System.out.println("Task ID: " + taskID);
        } catch (IOException e){
            throw new RuntimeException();
        }
    }
}

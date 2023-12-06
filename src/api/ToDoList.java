package api;

import okhttp3.*;
import okio.BufferedSink;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.IOException;

public class ToDoList implements Todo{
//    @Override
//
//    public Projects getProject(String name) {
//        OkHttpClient client = new OkHttpClient().newBuilder().build();
//        Request request = new Request.Builder()
//                .url(String.format("@doist/todoist-api-typescript", name)).build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            System.out.println(response);
//            JSONObject responseBody = new JSONObject(response.body().string());
//
//            JSONObject project = responseBody.getJSONObject("project");
//            return Projects.builder()
//                    .name(Projects.getName())
//                    .favourite(Projects.getFavourite())
//                    .build();
//
//        } catch (IOException | JSONException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public Projects logProject(String name, boolean favourite) throws JSONException {
//        return null;
//    }

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

    /**
     * @param projectName the name of the project
     * @param taskName the name of the task to be completed
     * @param taskID the ID of the task as assigned by Todoist API
     */
    @Override
    public void completeTask(String projectName, String taskName, long taskID) {
        JSONObject task = new JSONObject();
        try{
            task.put("id", taskID);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        OkHttpClient client = new OkHttpClient().newBuilder().build();

        Request request = new Request.Builder()
                .url("https://api.todoist.com/rest/v2/tasks/" + taskID + "/close")
                .post(RequestBody.create(null, new byte[0]))
                .header("X-Request-Id", java.util.UUID.randomUUID().toString())
                .header("Authorization", "Bearer b1aa35c9378d5217bc2745afc04aa0fcae246844")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response.code());

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * @param projectName the name of the project
     * @param taskName the name of the task to be added
     */
    @Override
    public long addTask(String projectName, String taskName) {
        JSONObject task = new JSONObject();
        String apiToken = "a232fc0417363afdc0318912dada87868f6889d4";
        try{
            task.put("content", taskName);
        }

        catch (JSONException e){
            throw new RuntimeException(e);
        }

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String jsonBody = "{\"content\": \"" + task.get("content") + "\"}";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), jsonBody);
        Request request = new Request.Builder()
                .url("https://api.todoist.com/rest/v2/tasks")
                .header("Authorization", "Bearer " + apiToken)
                .post(requestBody)
                .header("Content-Type", "application/json")
                .header("X-Request-Id", java.util.UUID.randomUUID().toString())
                .header("Authorization", "Bearer b1aa35c9378d5217bc2745afc04aa0fcae246844")
                .build();

        try{
            Response response = client.newCall(request).execute();

            // Convert the response body to a JSON object
            JSONObject jsonResponse = new JSONObject(response.body().string()) ;

            // Extracts the taskID as a long
            long taskID = Long.parseLong(jsonResponse.getString("id"));

            // Print the task ID
            System.out.println("Task ID: " + taskID);
            return taskID;

        } catch (IOException e){
            e.printStackTrace();
            return -1;
        }
    }
}

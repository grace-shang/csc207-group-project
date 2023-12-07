package api;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class ToDoList implements Todo{

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

            return taskID;

        } catch (IOException e){
            e.printStackTrace();
            return -1;
        }
    }
}

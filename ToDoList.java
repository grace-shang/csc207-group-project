package api;
import entity.Projects;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class ToDoList implements Todo{

    @Override
    public Projects getProject(String name) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(String.format("@doist/todoist-api-typescript", name))
                .build();
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


}

package api.views;

import com.google.gson.Gson;

public class View {
    
    private static Gson gson = new Gson();

    public static String show(Object data) {
        String json = gson.toJson(data);
        return json;
    }

}

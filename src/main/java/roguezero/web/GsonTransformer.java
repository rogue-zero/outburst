package roguezero.web;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class GsonTransformer implements ResponseTransformer {

    private final transient Gson gson;

    public GsonTransformer(Gson gson) {
        this.gson = gson;
    }

    public String render(Object response) {
        return gson.toJson(response);
    }

}
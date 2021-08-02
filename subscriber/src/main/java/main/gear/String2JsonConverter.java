package main.gear;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class String2JsonConverter {
    public JSONObject convertString2JsonObject(String jsonString) {
        try {
            return new JSONObject(jsonString);
        } catch (JSONException e) {
            throw new JSONException(e);
        }
    }
}

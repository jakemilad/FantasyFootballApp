package persistence;

import org.json.JSONArray;
import org.json.JSONObject;

public interface Writeable {
    JSONObject toJson();
}

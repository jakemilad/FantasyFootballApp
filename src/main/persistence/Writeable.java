package persistence;

import org.json.JSONObject;

// EFFECTS: returns this as a Json Object.
public interface Writeable {
    JSONObject toJson();
}
// SOURCES: WorkRoomApp Json demo project was used to implement the Writeable interface.
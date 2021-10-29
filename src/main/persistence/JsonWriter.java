package persistence;

import model.League;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of FantasyApp files on it.
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs a writer to write to the destination file.
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer and throws a FileNotFoundException, if the destination file
    // cannot be opened for writing.
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file.
    public void writeLeague(League lg) {
        JSONObject json = lg.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer.
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file.
    private void saveToFile(String json) {
        writer.print(json);
    }
}

// SOURCES: WorkRoomApp Json demo project was used to implement the JsonWriter.

package persistence;

import model.League;
import model.Player;
import model.Team;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    public JsonWriter(String destination) {
        this.destination = destination;
    }

    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

//    public void writePlayer(Player pl) {
//        JSONObject json = pl.toJson();
//        saveToFile(json.toString(TAB));
//    }
//
//    public void writeTeam(Team tm) {
//        JSONObject json = tm.toJson();
//        saveToFile(json.toString(TAB));
//    }

    public void writeLeague(League lg) {
        JSONObject json = lg.toJson();
        saveToFile(json.toString(TAB));
    }


    public void close() {
        writer.close();
    }

    private void saveToFile(String json) {
        writer.print(json);
    }
}

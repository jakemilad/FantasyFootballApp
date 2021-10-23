package persistence;

import model.League;
import model.Team;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            League lg = new League();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IO Exception was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyFantasyApp() {
        try {
            League lg = new League();
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyFantasyApp.json");
            writer.open();
            writer.writeLeague(lg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderEmptyFantasyApp.json");
            lg = reader.read();
            assertEquals("The League",lg.getName());
            assertEquals(0,lg.getTeamsInLeague());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

//    @Test
//    public void testReaderEmptyFantasyApp() {
//        JsonReader reader = new JsonReader("./data/testReaderEmptyFantasyApp.json");
//        try {
//            League lg = reader.read();
//            assertEquals("The League", lg.getName());
//            Team tm = new Team("Test Team");
//            assertEquals("The League",lg.getName());
//            lg.addTeam(tm);
//            assertEquals(1,lg.lengthOfLeagueTeams());
//
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }

}

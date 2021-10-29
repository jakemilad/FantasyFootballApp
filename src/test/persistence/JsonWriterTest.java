package persistence;

import model.League;
import model.Player;
import model.Team;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyFantasyApp.json");
            writer.open();
            writer.writeLeague(lg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFantasyApp.json");
            lg = reader.read();
            assertEquals(0,lg.lengthOfLeagueTeams());
            assertEquals(0,lg.lengthOfLeaguePlayers());
            assertTrue(lg.getPlayersInLeague().isEmpty());
            assertTrue(lg.getTeamsInLeague().isEmpty());
            assertEquals("The League",lg.getName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralFantasyApp() {
        try {
            League lg = new League();
            Team testTeam = new Team("Test Team");
            Team testTeamTwo = new Team ("Test Team 2");
            Player pl = new Player("Test Player","MID",50.0);
            lg.addTeam(testTeam);
            lg.addTeam(testTeamTwo);
            lg.addPlayerToLeague(pl);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFantasyApp.json");
            writer.open();
            writer.writeLeague(lg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFantasyApp.json");
            lg = reader.read();
            assertEquals(1,lg.lengthOfLeaguePlayers());
            assertEquals(2,lg.lengthOfLeagueTeams());
            assertFalse(lg.getTeamsInLeague().isEmpty());
            assertEquals("Test Team",testTeam.getTeamName());
            assertEquals("The League",lg.getName());
        } catch (IOException e) {
            fail("Exception was not expected");
        }
    }



}

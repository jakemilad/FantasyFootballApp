package persistence;

import model.League;
import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            League lg = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // should pass
        }
    }

    @Test
    public void testReaderEmptyFantasyApp() {
        JsonReader reader =  new JsonReader("./data/testReaderEmptyFantasyApp.json");
        try {
            League lg = reader.read();
            assertEquals("The League",lg.getName());
            assertEquals(0,lg.lengthOfLeaguePlayers());
            assertTrue(lg.getTeamsInLeague().isEmpty());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    public void testReaderGeneralFantasyApp() {
        try {
            JsonReader reader = new JsonReader("./data/testReaderGeneralFantasyApp.json");
            League lg = reader.read();
            assertEquals(0,lg.lengthOfLeagueTeams());
            Team tm = new Team("Test Team");
            Player plOne = new Player("test player","MID",12.9);
            Player plTwo = new Player("testPLayer 2","ATT",15.7);
            lg.addTeam(tm);
            lg.addPlayerToLeague(plOne);
            assertEquals(1,lg.lengthOfLeagueTeams());
            assertEquals(1, lg.lengthOfLeaguePlayers());
            lg.addPlayerToLeague(plTwo);
            assertEquals(2, lg.lengthOfLeaguePlayers());
            tm.addPlayer(plOne);
            assertEquals(plOne,tm.getPlayerFromTeam("test player"));
        } catch (IOException e) {
            fail("don't fail please");
        }
    }

}

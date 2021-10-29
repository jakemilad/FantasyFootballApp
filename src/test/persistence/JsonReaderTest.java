package persistence;

import model.League;
import model.Player;
import model.Team;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

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
            League lg = new League();
            JsonWriter writer = new JsonWriter("./data/testReaderGeneralFantasyApp.json");
            Team testTeam = new Team("testTeam");
            Player testPlayer = new Player("testPlayer","MID",12.5);
            Player testPlayerTwo = new Player("testPlayerTwo","ATT",12.5);
            lg.addTeam(testTeam);
            lg.addPlayerToLeague(testPlayer);
            lg.addPlayerToLeague(testPlayerTwo);
            testTeam.addPlayer(testPlayer);
            testTeam.addPlayer(testPlayerTwo);
            writer.open();
            writer.writeLeague(lg);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralFantasyApp.json");
            lg = reader.read();
            assertEquals(1,lg.lengthOfLeagueTeams());
            assertEquals(2,lg.lengthOfLeaguePlayers());
            List<Team> testAddPlayer = lg.getTeamsInLeague();
            testTeam = testAddPlayer.get(0);
            assertEquals(2,testTeam.length());
        } catch (IOException e) {
            fail("Don't fail please");
        }
    }

}
// SOURCES: WorkRoomApp Json demo project was used to test the JsonReader.
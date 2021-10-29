package persistence;

import model.League;
import model.Player;
import model.Team;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads a FantasyApp from JSON stored data in files
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the FantasyApp from the file and returns it; throws an IOException
    // if there is an error while reading the data from the file source.
    public League read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLeague(jsonObject);
    }

    // EFFECTS: reads the source file as String and returns it.
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses a League, denoted as the root of the FantasyApp from JSON Object
    // and returns it.
    public League parseLeague(JSONObject jsonObject) {
        League lg = new League();
        addLeaguePlayers(lg, jsonObject);
        addLeagueTeams(lg, jsonObject);
        return lg;
    }

    // MODIFIES: lg
    // EFFECTS: parses all league teams from JSON Object and adds them to the league.
    public void addLeagueTeams(League lg, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("leagueTeams");
        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            addLeagueTeam(lg, nextTeam);
        }
    }

    // MODIFIES: lg
    // EFFECTS: parses all league players from JSON Object and adds them to the league
    public void addLeaguePlayers(League lg, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("leaguePlayers");
        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addLeaguePlayer(lg, nextPlayer);
        }
    }

    // MODIFIES: lg
    // EFFECTS: adds all league players as JSON Object data to be parsed
    public void addLeaguePlayer(League lg, JSONObject jsonObject) {
        String name = jsonObject.getString("PlayerName");
        String position = jsonObject.getString("Position");
        Double price = jsonObject.getDouble("Price");
        Integer goals = jsonObject.getInt("Goals");
        Integer assists = jsonObject.getInt("Assists");
        Integer points = jsonObject.getInt("Points");
        Player leaguePlayer = new Player(name, position, price);
        leaguePlayer.points = points - points;
        leaguePlayer.scoredGoal(goals);
        leaguePlayer.scoredAssist(assists);
        lg.addPlayerToLeague(leaguePlayer);
    }


    // MODIFIES: lg
    // EFFECTS: adds all league teams as JSON Object data to be parsed
    public void addLeagueTeam(League lg, JSONObject jsonObject) {
        String name = jsonObject.getString("TeamName");
        Integer teamPoints = jsonObject.getInt("TeamPoints");
        Team leagueTeam = new Team(name);
        addPlayersForLeagueTeam(lg, leagueTeam, jsonObject);
        leagueTeam.teamPoints = teamPoints;
        lg.addTeam(leagueTeam);
    }

    // MODIFIES: lg, tm
    // EFFECTS: parses all players that have been added into a team as Json Objects.
    public void addPlayersForLeagueTeam(League lg, Team tm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("PlayersInTeam");

        for (Object json : jsonArray) {
            String nextPlayer = (String) json;
            addPlayer(lg,tm,nextPlayer);
        }
    }


    // MODIFIES: lg, tm
    // EFFECTS: adds players into the team for a given Json Object team in a Json Object league.
    public void addPlayer(League lg, Team tm, String playerName) {
        for (Player p : lg.getPlayersInLeague()) {
            if (p.getName().equals(playerName)) {
                tm.addPlayer(p);
            }
        }
    }
}

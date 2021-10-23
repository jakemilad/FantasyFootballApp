package persistence;

import model.League;
import model.Player;
import model.Team;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.FantasyApp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {

    private String source;

    public JsonReader(String source) {
        this.source = source;
    }

    public League read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseLeague(jsonObject);
    }

    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    public League parseLeague(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        League lg = new League();
        addLeaguePlayers(lg, jsonObject);
        addLeagueTeams(lg, jsonObject);
        return lg;
    }

    public void addLeaguePlayers(League lg, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Players");

        for (Object json : jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            addLeaguePlayer(lg, nextPlayer);
        }
    }

    public void addLeaguePlayer(League lg, JSONObject jsonObject) {
        String name = jsonObject.getString("leaguePlayer Name");
        String position = jsonObject.getString("leaguePLayer Position");
        Double price = jsonObject.getDouble("leaguePlayer  price");
        Integer goals = jsonObject.getInt("leaguePlayer Goals");
        Integer assists = jsonObject.getInt("leaguePlayer Assists");
        Integer points = jsonObject.getInt("leaguePlayer Points");
        Player leaguePlayer = new Player(name, position, price);
        leaguePlayer.getPoints().equals(points);
        leaguePlayer.scoredGoal(goals);
        leaguePlayer.scoredAssist(assists);
        lg.getPlayersInLeague().add(leaguePlayer);

    }

    public void addLeagueTeams(League lg, JSONObject jsonObject) {
        JSONArray jsonArray = new JSONArray("Teams");

        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            addLeagueTeam(lg, nextTeam);
        }
    }

    public void addLeagueTeam(League lg, JSONObject jsonObject) {
        String name = jsonObject.getString("leagueTeam Name");
        Integer teamPoints = jsonObject.getInt("leagueTeam Points");
        Team leagueTeam = new Team(name);
        addPlayersForLeagueTeam(lg, leagueTeam, jsonObject);
        leagueTeam.getPoints().equals(teamPoints);
        lg.getTeamsInLeague().add(leagueTeam);
    }

    public void addPlayersForLeagueTeam(League lg, Team tm, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Players for team");

        for (Object json : jsonArray) {
            String nextPlayer = (String) json;
            //addPlayer(lg,tm,nextPlayer);
        }
    }

//    public void addPlayer(League lg, Team tm, String playerName) {
////        for (Player p : lg.getPlayersInLeague()) {
////            if (p.getName().equals(playerName)) {
////                for (Team t : lg.getTeamsInLeague()) {
////                    if (t.)
////                }
////            }
////        }
////    }


}

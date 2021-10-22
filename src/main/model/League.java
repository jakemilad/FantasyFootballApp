package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;

public class League implements Writeable {

    public ArrayList<Team> leagueTeams;
    public ArrayList<Player> leaguePlayers;
    private String name;

    // EFFECTS: constructs a League as a list of teams
    public League() {
        this.name = "My League";
        leagueTeams = new ArrayList<>();
        leaguePlayers = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList getTeamsInLeague() {
        return this.leagueTeams;
    }

    public ArrayList getPlayersInLeague() {
        return this.leaguePlayers;
    }


    // EFFECTS: adds a Team to the league
    public void addTeam(Team t) {
        leagueTeams.add(t);
    }


    // EFFECTS: removes a team from the league
    public void removeTeam(Team t) {
        leagueTeams.remove(t);
    }

    public void addPlayerToLeague(Player p) {
        leaguePlayers.add(p);
    }

    // EFFECTS: returns the number of teams in the league,
    // expressed as the size of the list of teams.
    public int length() {
        return leagueTeams.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Teams", leagueTeamToJson());
        json.put("Players", leaguePlayersToJson());
        return json;
    }

    private JSONArray leagueTeamToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Team t : leagueTeams) {
            jsonArray.put(t.getTeamName());
        }
        return jsonArray;
    }

    private JSONArray leaguePlayersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : leaguePlayers) {
            jsonArray.put(p.getName());
        }
        return jsonArray;
    }


}

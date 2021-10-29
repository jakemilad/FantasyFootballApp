package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;

public class League implements Writeable {

    private ArrayList<Team> leagueTeams;
    private ArrayList<Player> leaguePlayers;
    private String name;

    // EFFECTS: constructs a League as a list of teams, initializes the league name, league teams
    // and league players.
    public League() {
        this.name = "The League";
        leagueTeams = new ArrayList<>();
        leaguePlayers = new ArrayList<>();
    }

    // EFFECTS: returns the name of the league, constructed as "The League".
    public String getName() {
        return this.name;
    }

    // EFFECTS: gets all teams in the league
    public ArrayList<Team> getTeamsInLeague() {
        return leagueTeams;
    }

    // EFFECTS: gets all the players in the league
    public ArrayList<Player> getPlayersInLeague() {
        return leaguePlayers;
    }

    // EFFECTS: if the team is not already in the league, adds a Team to the league
    public void addTeam(Team t) {
        if (!leagueTeams.contains(t)) {
            leagueTeams.add(t);
        }
    }

    // EFFECTS: removes a team from the league
    public void removeTeam(Team t) {
        leagueTeams.remove(t);
    }

    // EFFECTS: if the player is not already in the league, adds a Player to the league.
    public void addPlayerToLeague(Player p) {
        if (!leaguePlayers.contains(p)) {
            leaguePlayers.add(p);
        }
    }

    // EFFECTS: returns the number of teams in the league,
    // expressed as the size of the list of teams.
    public int lengthOfLeagueTeams() {
        return leagueTeams.size();
    }


    // EFFECTS: returns  the number of  teams in the league.
    public int lengthOfLeaguePlayers() {
        return leaguePlayers.size();
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("leagueTeams", leagueTeamToJson());
        json.put("leaguePlayers", leaguePlayersToJson());
        return json;
    }

    // EFFECTS: returns all league teams as a JSON Array
    private JSONArray leagueTeamToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Team t : leagueTeams) {
            jsonArray.put(t.toJson());
        }
        return jsonArray;
    }


    // EFFECTS: returns all league players as a JSON Array
    private JSONArray leaguePlayersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : leaguePlayers) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }


}

package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writeable;

import java.util.ArrayList;

public class Team implements Writeable {

    protected ArrayList<Player> teamPlayers;
    protected int teamPoints;
    private String name;

    // EFFECTS: constructs a team as a list of players and assigned a team name,
    // initializes as having 0 team points.
    public Team(String name) {
        teamPlayers = new ArrayList<>();
        teamPoints = 0;
        this.name = name;
    }

    // REQUIRES: valid player from initialized set of players
    // EFFECTS: if the player p is not already in the team, add the player
    // into the team and alter their inTeam status to true.
    public void addPlayer(Player p) {
        if (!(inTeamForGivenPlayer(p))) {
            teamPlayers.add(p);
            p.inTeam = true;
        }
    }

    // EFFECTS: removes player from team
    public void removePlayer(Player p) {
        teamPlayers.remove(p);
    }


    // EFFECTS: returns the total points for the team
    public Integer getPoints() {
        return teamPoints;
    }

    // EFFECTS: sets the total points for the team as parameter i
    public void setPointsForTeam(int i) {
        teamPoints = i;
    }

    // EFFECTS: returns name of the team
    public String getTeamName() {
        return name;
    }

    // EFFECTS: returns number of players in the team as the size
    // of a team.
    public Integer length() {
        return teamPlayers.size();
    }

    // REQUIRES: Player p to be a valid and existing player in the initialized set of players
    // EFFECTS: given a String s, interpreted as the name of a Player, return the Player object
    // if the player is in the Team, otherwise print given statement
    public Player getPlayerFromTeam(String s) {
        for (Player p : teamPlayers) {
            if (s == p.getName()) {
                return p;
            }
        }
        return null;
    }

    // EFFECTS: if the player p is in team, return true, false otherwise
    public boolean inTeamForGivenPlayer(Player p) {
        if (this.teamPlayers.contains(p)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("TeamName", name);
        json.put("PlayersInTeam", teamPlayersToJson());
        json.put("TeamPoints", teamPoints);
        return json;
    }

    // returns the list of all teams in the league as a Json Array.
    private JSONArray teamPlayersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Player p : teamPlayers) {
            jsonArray.put(p.getName());
        }
        return jsonArray;
    }

}

package model;

import java.util.ArrayList;

public class Team {

    protected ArrayList<Player> myTeam;
    protected int totalPoints;
    private String name;

    // EFFECTS: constructs a team as a list of players and assigned a team name,
    // initializes as having 0 team points.
    public Team(String name) {
        myTeam = new ArrayList<>();
        totalPoints = 0;
        this.name = name;
    }

    // REQUIRES: valid player from initialized set of players
    // EFFECTS: if the player p is not already in the team, add the player
    // into the team and alter their inTeam status to true.
    public void addPlayer(Player p) {
        if (!(inTeamForGivenPlayer(p))) {
            myTeam.add(p);
            p.inTeam = true;
        }
    }

    // EFFECTS: removes player from team
    public void removePlayer(Player p) {
        myTeam.remove(p);
    }

    // EFFECTS: returns the total points for the team
    public Integer getPoints() {
        return totalPoints;
    }

    // EFFECTS: returns name of the team
    public String getTeamName() {
        return name;
    }

    // EFFECTS: returns number of players in the team as the size
    // of a team.
    public Integer length() {
        return myTeam.size();
    }

    // REQUIRES: Player p to be a valid and existing player in the initialized set of players
    // EFFECTS: given a String s, interpreted as the name of a Player, return the Player object
    // if the player is in the Team, otherwise print given statement
    public Player getPlayerFromTeam(String s) {
        for (Player p : myTeam) {
            if (s == p.getName()) {
                return p;
            }
        }
        return null;
    }

    // EFFECTS: if the player p is in team, return true, false otherwise
    public boolean inTeamForGivenPlayer(Player p) {
        if (this.myTeam.contains(p)) {
            return true;
        } else {
            return false;
        }
    }

}

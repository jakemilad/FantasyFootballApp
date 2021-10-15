package model;

import java.util.ArrayList;

public class League {

    private ArrayList<Team> myLeague;

    // EFFECTS: constructs a League as a list of teams
    public League() {
        myLeague = new ArrayList<>();
    }

    // EFFECTS: adds a Team to the league
    public void addTeam(Team t) {
        myLeague.add(t);
    }

    // EFFECTS: removes a team from the league
    public void removeTeam(Team t) {
        myLeague.remove(t);
    }

    // EFFECTS: returns the number of teams in the league,
    // expressed as the size of the list of teams.
    public int length() {
        return myLeague.size();
    }

}

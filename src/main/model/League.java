package model;

import java.util.ArrayList;

public class League {

    private ArrayList<Team> myLeague;

    public League() {
        myLeague = new ArrayList<>();
    }

    public void addTeam(Team t) {
        myLeague.add(t);
    }

    public void removeTeam(Team t) {
        myLeague.remove(t);
    }

    public int length() {
        return myLeague.size();
    }

}

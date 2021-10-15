package model;

import java.util.ArrayList;

public class Team {

    protected ArrayList<Player> myTeam;
    protected int totalPoints;
    private String name;

    public Team(String name) {
        myTeam = new ArrayList<>();
        totalPoints = 0;
        this.name = name;
    }

    public void addPlayer(Player p) {
        if (!(inTeamForGivenPlayer(p))) {
            myTeam.add(p);
            p.inTeam = true;
        } else {
            System.out.println("Already in team");
        }
    }

    public void removePlayer(Player p) {
        myTeam.remove(p);
    }

    public Integer getPoints() {
        return totalPoints;
    }

    public String getTeamName() {
        return name;
    }


    public Integer length() {
        return myTeam.size();
    }

    public Player getPlayerFromTeam(String s) {
        for (Player p : myTeam) {
            if (s == p.getName()) {
                return p;
            } else  {
                System.out.println(s + " is not in this team.");
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

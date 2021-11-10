package model;

import org.json.JSONObject;
import persistence.Writeable;

// Modeling the specification of a Player in a Fantasy Football Application.
public class Player implements Writeable {

    private String name;          // Players name
    private String position;      // Players position: (DEF, MID, ATT)
    private Double price;         // Players price < 15.0
    private int goals;
    private int assists;
    protected int points;
    private int goalPoints = 2;
    private int assistPoints = 1;
    protected boolean inTeam;


    // EFFECTS: constructs a new player with a name, a position that is
    // either DEF, MID or ATT and a price. Initializes the player to have 0 goals,
    // 0 assists and therefore 0 points, as well as a boolean to determine if the player has
    // been assigned to a team or not
    public Player(String playerName, String playerPosition, Double playerPrice) {
        this.name = playerName;
        this.position = playerPosition;
        this.price = playerPrice;
        this.goals = 0;
        this.assists = 0;
        points = 0;
        inTeam = false;
    }

    // EFFECTS: returns the name of the Player
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns the position of the Player
    public String getPosition() {
        return this.position;
    }

    // EFFECTS: returns the price of the Player
    public Double getPrice() {
        return this.price;
    }

    // REQUIRES: goal > 0
    // MODIFIES: this, points
    // EFFECTS: when player scores a goal, increment goals by number of goals
    // and add points to player according to goalPoints multiplier
    public void scoredGoal(int goal) {
        this.goals = goal + this.goals;
        this.points = (goal * goalPoints) + this.points;
    }

    // REQUIRES: assist > 0
    // MODIFIES: this, points
    // EFFECTS: when player assists, increment assists by number of assists
    // and add points to player according to assistPoints multiplier
    public void scoredAssist(int assist) {
        this.assists = assist + assists;
        this.points = (assist * assistPoints) + points;
    }

    // EFFECTS: returns the number of points a player has accumulated
    public Integer getPoints() {
        return points;
    }


    // EFFECTS: sets the points field to given parameter i
    public void setPoints(int i) {
        points = i;
    }

    // EFFECTS: returns the number of goals a player has scored
    public Integer getGoals() {
        return goals;
    }

    // EFFECTS: returns the number of assists a player has got
    public Integer getAssists() {
        return assists;
    }

    // REQUIRES: goal > 0, t to be a valid and existing team
    // MODIFIES: this, Team, teamPoints, points
    // EFFECTS: when a player scores and has been assigned to a team, both the player
    // and the team they are assigned to accumulate points based on the goalPoints multiplier
    public void scoredGoalTeam(int goal, Team t) {
        this.goals = goal + this.goals;
        this.points = (goal * goalPoints) + this.points;
        t.teamPoints = (goal * goalPoints) + t.teamPoints;
    }

    // REQUIRES: assist > 0, t to be a valid and existing team
    // MODIFIES: this, Team, teamPoints, points
    // EFFECTS: when a player gets an assist and has been assigned to a team, both the player
    // and the team they are assigned to accumulate points based on the assistPoints multiplier
    public void scoredAssistTeam(int assist, Team t) {
        this.goals = assist + this.goals;
        this.points = (assist * assistPoints) + this.points;
        t.teamPoints = (assist * assistPoints) + t.teamPoints;
    }

    // EFFECTS: returns true if the player is in a team
    public boolean inTeamForPlayer() {
        return inTeam;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("PlayerName",name);
        json.put("Position", position);
        json.put("Price", price);
        json.put("Points", points);
        json.put("Assists", assists);
        json.put("Goals", goals);
        return json;
    }


}

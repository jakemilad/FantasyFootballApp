package model;

public class Player {

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
    // either DEF, MID or ATT and a price.
    public Player(String playerName, String playerPosition, Double playerPrice) {
        this.name = playerName;
        this.position = playerPosition;
        this.price = playerPrice;
        this.goals = 0;
        this.assists = 0;
        points = 0;
        inTeam = false;
    }

    public String getName() {
        return this.name;
    }

    public String getPosition() {
        return this.position;
    }

    public Double getPrice() {
        return this.price;
    }

    // REQUIRES: goal > 0
    // MODIFIES: number of points that player accumulates
    // EFFECTS: when player scores a goal, increment goals by number of goals
    // and add points to player according to goalPoints multiplier
    public void scoredGoal(int goal) {
        this.goals = goal + this.goals;
        this.points = (goal * goalPoints) + this.points;
    }

    // REQUIRES: assist > 0
    // MODIFIES: number of points that player accumulates
    // EFFECTS: when player assists, increment assists by number of assists
    // and add points to player according to assistPoints multiplier
    public void scoredAssist(int assist) {
        this.assists = assist + assists;
        this.points = (assist * assistPoints) + points;
    }

    public Integer getPoints() {
        return points;
    }

    public Integer getGoals() {
        return goals;
    }

    public Integer getAssists() {
        return assists;
    }

    public void scoredGoalTeam(int goal, Team t) {
        this.goals = goal + this.goals;
        this.points = (goal * goalPoints) + this.points;
        t.totalPoints = (goal * goalPoints) + t.totalPoints;
    }

    public void scoredAssistTeam(int assist, Team t) {
        this.goals = assist + this.goals;
        this.points = (assist * assistPoints) + this.points;
        t.totalPoints = (assist * assistPoints) + t.totalPoints;
    }

    public boolean inTeamForPlayer() {
        return this.inTeam;
    }


}

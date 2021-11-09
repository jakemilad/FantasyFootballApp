package ui;

import model.League;
import model.Team;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FantasyApp {
    private League theLeague;
    private Scanner input;
    private static ArrayList<Player> allPlayers;
    private static ArrayList<Team> allTeams;
    private Player messi = new Player("Messi", "ATT", 15.0);
    private Player ronaldo = new Player("Ronaldo", "ATT", 15.0);
    private Player salah = new Player("Salah", "ATT", 14.5);
    private Player kante = new Player("Kante", "MID", 10.0);
    private Player fernandes = new Player("Fernandes", "MID", 13.5);
    private Player debruyne = new Player("De Bruyne", "MID", 14.0);
    private Player pogba = new Player("Pogba", "MID", 11.5);
    private Player hernandez = new Player("Hernandez", "DEF", 12.0);
    private Player walker = new Player("Walker", "DEF", 13.0);
    private Player jaitly = new Player("Jaitly", "MID", 14.5);
    private Player davies = new Player("Davies", "DEF", 11.0);
    private Player ramos = new Player("Ramos", "MID", 8.5);
    private Player emile = new Player("Emile", "MID", 9.0);
    private Player neuer = new Player("Neuer", "DEF", 13.0);
    private Player oblak = new Player("Oblak", "DEF", 13.0);
    private Player neymar = new Player("Neymar", "ATT", 15.0);
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/fantasy.json";


    public FantasyApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        theLeague = new League();
        runFantasy();
    }

    // EFFECTS: Initializes the game. Adds all players into a list of players named allPlayers,
    // creates a list of Teams denoted as allTeams.
    public void init() {
        allPlayers = new ArrayList<>();
        allTeams = new ArrayList<>();
        theLeague = new League();
        allPlayers = theLeague.getPlayersInLeague();
        allTeams = theLeague.getTeamsInLeague();
        allPlayers.add(messi);
        allPlayers.add(ronaldo);
        allPlayers.add(salah);
        allPlayers.add(kante);
        allPlayers.add(fernandes);
        allPlayers.add(debruyne);
        allPlayers.add(pogba);
        allPlayers.add(hernandez);
        allPlayers.add(walker);
        allPlayers.add(jaitly);
        allPlayers.add(davies);
        allPlayers.add(neuer);
        allPlayers.add(neymar);
        allPlayers.add(emile);
        allPlayers.add(ramos);
        allPlayers.add(oblak);
        input = new Scanner(System.in);
    }

    // EFFECTS: runs the game, if q is inputted, end the game and print the
    // statement specified, otherwise, run otherCommands.
    private void runFantasy() {
        boolean onGoing = true;
        String command = null;

        init();

        while (onGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                onGoing = false;
            } else {
                otherCommands(command);
            }
        }
        System.out.println("\n See you soon");

    }

    // EFFECTS: presents the list of commands in the display menu
    private void otherCommands(String command) {
        if (command.equals("l")) {
            viewLeague();
        } else if (command.equals("v")) {
            viewPlayers();
        } else if (command.equals("c")) {
            createTeam();
        } else if (command.equals("s")) {
            saveState();
        } else if (command.equals("p")) {
            loadState();
        } else {
            System.out.println("Not valid selection");
        }
    }

    // EFFECTS: presents the list of all the teams created, denoted as a League.
    // Prints the team name as well as the total points for the team. Prompts the functionality
    // to configure inputted teams.
    public void viewLeague() {
        System.out.println("League Table");
        if (!(allTeams).isEmpty()) {
            for (Team team : allTeams) {
                System.out.println("\n" + team.getTeamName() + "   " + "   " + team.getPoints());
            }
        }

        System.out.println("f ---> Configure Team");
        System.out.println("b ---> Back to Main Menu");
        String leagueCommand = "";
        leagueCommand = input.next();
        leagueCommand = leagueCommand.toLowerCase();
        if (leagueCommand.equals("b")) {
            displayMenu();
        } else if (leagueCommand.equals("f"))  {
            teamConfiguration();
        }

    }


    // EFFECTS: prints the list of all initialized players in allPLayers as their Name
    // and points. Functionality to configure players if inputted is selected.
    public void viewPlayers() {
        String playerCommand = "";
        System.out.println("All Players Available");

        if (!(allPlayers).isEmpty()) {
            for (Player playa : allPlayers) {
                System.out.println(playa.getName() + " " + " " +  playa.getPoints() + " " + " " + playa.getPosition()
                        + " " + " " + playa.getPrice());
            }
        }

        System.out.println("\nr ---> Add Player Statistics");
        System.out.println("b ---> Back to Main Menu");
        playerCommand = input.next();
        playerCommand = playerCommand.toLowerCase();
        if (playerCommand.equals("r")) {
            playerConfiguration();
        }
    }

    // EFFECTS: display menu for configuring players
    public void playerConfiguration() {
        String playerCommand = "";
        System.out.println("\ng ---> Register Goals");
        System.out.println("a ---> Register Assists");
        System.out.println("b ---> Back to Main Menu");
        playerCommand = input.next();
        playerCommand = playerCommand.toLowerCase();
        if (playerCommand.equals("g")) {
            registerGoals();
        } else if (playerCommand.equals("a")) {
            registerAssists();
        }

    }

    // REQUIRES: player name to be a valid player that exists in the initialized set of players.
    // EFFECTS: if the player is in a team, accumulate points for the player and subsequently the team
    // they are assigned to, otherwise, accumulate points for players
    public void registerGoals() {
        int goalsCommand = 0;
        String nameCommand = "";
        System.out.println("Who Scored?");
        nameCommand = input.next();

        System.out.println("How Many Goals?");
        goalsCommand = input.nextInt();

        for (Player p : allPlayers) {
            if (p.getName().equals(nameCommand)) {
                if (p.inTeamForPlayer()) {
                    for (Team t : allTeams) {
                        if (t.inTeamForGivenPlayer(p)) {
                            p.scoredGoalTeam(goalsCommand, t);
                        }
                    }
                }
                if ((!(p.inTeamForPlayer())) && p.getName().equals(nameCommand)) {
                    p.scoredGoal(goalsCommand);
                }
            }
        }

    }

    // REQUIRES: player name to be a valid player that exists in the initialized set of players.
    // EFFECTS: if the player is in a team, accumulate points for the player and subsequently the team
    // they are assigned to, otherwise, accumulate points for players.
    public void registerAssists() {
        int assistCommand = 0;
        String nameCommand = "";
        System.out.println("Who Assisted?");
        nameCommand = input.next();

        System.out.println("How Many Assists?");
        assistCommand = input.nextInt();

        for (Player p : allPlayers) {
            if (p.getName().equals(nameCommand)) {
                if (p.inTeamForPlayer()) {
                    for (Team t : allTeams) {
                        if (t.inTeamForGivenPlayer(p)) {
                            p.scoredAssistTeam(assistCommand, t);
                        }
                    }
                }
                if ((!(p.inTeamForPlayer())) && p.getName().equals(nameCommand)) {
                    p.scoredAssist(assistCommand);
                }

            }
        }
    }


    // EFFECTS: displays menu to create a team which is added to the league, displays menu to configure
    // the team.
    public void createTeam() {
        String createCommand = "";
        System.out.println("\n Enter Team Name");
        String name = input.next();
        Team newTeam = new Team(name);
        allTeams.add(newTeam);
        System.out.println("\nTeam Successfully Created and Added to the League");

        System.out.println("\nf ---> Team Configuration");
        System.out.println("b ---> Back to Main Menu");
        createCommand = input.next();
        createCommand = createCommand.toLowerCase();
        if (createCommand.equals("f")) {
            teamConfiguration();
        }

    }

    // EFFECTS: displays menu for all the configurations possible for a team.
    public void teamConfiguration() {
        String teamCommand = "";
        System.out.println("a ---> Add a Player to your team");
        System.out.println("v ---> Show all players");
        System.out.println("c ---> Show Players in your Team");
        System.out.println("b ---> Back to Main Menu");
        teamCommand = input.next();
        teamCommand = teamCommand.toLowerCase();
        if (teamCommand.equals("a")) {
            addPlayerToTeam();
        } else if (teamCommand.equals("v")) {
            viewPlayers();
        } else if (teamCommand.equals("c")) {
            viewPlayersInTeam();
        } else {
            System.out.println("No Teams Created");
            System.out.println("b ---> back to main menu");
        }

    }

    // EFFECTS: prints the list of all players; if the player is not already in the inputted team,
    // add the player into the  team, otherwise print the given statement.
    public void addPlayerToTeam() {
        if (!(allPlayers).isEmpty()) {
            for (Player playa : allPlayers) {
                System.out.println(playa.getName() + " " + " " +  playa.getPoints() + " " + " " + playa.getPosition()
                        + " " + " " + playa.getPrice());
            }
        }
        System.out.println("\nWhich Player do you want to add?");
        String name = input.next();

        System.out.println("Which team do you want to add the player to?");
        String teamName = input.next();

        for (Player p : allPlayers) {
            for (Team t : allTeams) {
                if ((p.getName().equals(name)) && (t.getTeamName().equals(teamName))) {
                    if (!(t.inTeamForGivenPlayer(p))) {
                        t.addPlayer(p);
                    } else {
                        System.out.println("Already in the team");
                    }
                }
            }
        }
    }

    // EFFECTS: prints all the players that have been added to an inputted team
    public void viewPlayersInTeam() {
        String viewCommand = "";
        System.out.println("\nWhich team do you want to view?");
        viewCommand = input.next();

        for (Team t : allTeams) {
            if (t.getTeamName().equals(viewCommand)) {
                for (Player p : allPlayers) {
                    if (t.inTeamForGivenPlayer(p)) {
                        System.out.println(p.getName() + " " + p.getPoints());
                    }
                }
            }
        }
    }

    // EFFECTS: saves the current state of the FantasyApp, otherwise throws FileNotFound exception
    // if saving fails.
    public void saveState() {
        try {
            jsonWriter.open();
            jsonWriter.writeLeague(theLeague);
            jsonWriter.close();
            System.out.println("Saved League to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable  to write file: " + JSON_STORE);
        }
    }

    // EFFECTS: loads the previously saved state of the FantasyApp, otherwise catches IOException exception
    // if failed to load.
    public void loadState() {
        try {
            theLeague = jsonReader.read();
            allTeams = theLeague.getTeamsInLeague();
            allPlayers = theLeague.getPlayersInLeague();
            System.out.println("Loaded League from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to load from " + JSON_STORE);
        }
    }

    // EFFECTS: displays the menu of the game
    public void displayMenu() {

        System.out.println("\nFantasy Super League Main Menu");
        System.out.println("\nChoose from:");
        System.out.println("\tl ---> View League");
        System.out.println("\tv ---> View Players");
        System.out.println("\tc ---> Create Team");
        System.out.println("\ts ---> Save League Standings");
        System.out.println("\tp ---> Load League Standings");
        System.out.println("\tq ---> Quit");

    }

}

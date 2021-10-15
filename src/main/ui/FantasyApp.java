package ui;

import model.League;
import model.Team;
import model.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class FantasyApp {
    private Team theTeam;
    private League theLeague;
    private Scanner input;
    private ArrayList<Player> allPlayers;
    private ArrayList<Team> allTeams;
    private Player messi = new Player("Messi", "ATT", 15.0);
    private Player ronaldo = new Player("Ronaldo", "ATT", 15.0);
    private Player salah = new Player("Salah", "ATT", 14.5);
    private Player kante = new Player("Kante", "MID", 10.0);
    private Player fernandes = new Player("Fernandes", "MID", 13.5);
    private Player debruyne = new Player("De Bruyne", "MID", 14.0);
    private Player pogba = new Player("Pogba", "MID", 11.5);
    private Player hernandez = new Player("Hernandez", "DEF", 12.0);
    private Player walker = new Player("Walker", "DEF", 13.0);
    private Player jaitly = new Player("AJ", "MID", 14.5);


    public FantasyApp() {
        runFantasy();
    }

    public void init() {
        allPlayers = new ArrayList<>();
        allTeams = new ArrayList<>();
        theTeam = new Team("Your Team");
        theLeague = new League();
        theLeague.addTeam(theTeam);
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
        input = new Scanner(System.in);
    }

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


    private void otherCommands(String command) {
        if (command.equals("l")) {
            viewLeague();
        } else if (command.equals("v")) {
            viewPlayers();
        } else if (command.equals("c")) {
            createTeam();
        } else {
            System.out.println("Not valid selection");
        }
    }

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

    public void viewPlayers() {
        String playerCommand = "";
        System.out.println("All Players Available");

        if (!(allPlayers).isEmpty()) {
            for (Player playa : allPlayers) {
                System.out.println("\n" + playa.getName() + " " + playa.getPoints());
            }
        }

        System.out.println("\n r ---> Add Player Statistics");
        System.out.println("\n b ---> Back to Main Menu");
        playerCommand = input.next();
        playerCommand = playerCommand.toLowerCase();
        if (playerCommand.equals("r")) {
            playerConfiguration();
        }
    }

    public void playerConfiguration() {
        String playerCommand = "";
        System.out.println("\n g ---> Register Goals");
        System.out.println("\n a ---> Register Assists");
        System.out.println("\n b ---> Back to Main Menu");
        playerCommand = input.next();
        playerCommand = playerCommand.toLowerCase();
        if (playerCommand.equals("g")) {
            registerGoals();
        } else if (playerCommand.equals("a")) {
            registerAssists();
        }

    }


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
                p.scoredGoal(goalsCommand);
            }
        }

    }

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
                p.scoredAssist(assistCommand);
            }
        }
    }


    public void createTeam() {
        String createCommand = "";
        System.out.println("\n Enter Team Name");
        String name = input.next();
        Team newTeam = new Team(name);
        allTeams.add(newTeam);
        theLeague.addTeam(newTeam);
        System.out.println("Team Successfully Created and Added to the League");

        System.out.println("f ---> Team Configuration");
        System.out.println("b ---> Back to Main Menu");
        createCommand = input.next();
        createCommand = createCommand.toLowerCase();
        if (createCommand.equals("f")) {
            teamConfiguration();
        }

    }

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

    public void addPlayerToTeam() {
        if (!(allPlayers).isEmpty()) {
            for (Player playa : allPlayers) {
                System.out.println("\n" + playa.getName() + " " + playa.getPoints());
            }
        }
        System.out.println("Which Player do you want to add");
        String name = input.next();

        System.out.println("Which team do you want to add the player to");
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


    public void viewPlayersInTeam() {
        String viewCommand = "";
        System.out.println("Which team do you want to view?");
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

    public void displayMenu() {

        System.out.println("Fantasy Super League Main Menu");
        System.out.println("\nChoose from:");
        System.out.println("\tl ---> View League");
        System.out.println("\tv ---> View Players");
        System.out.println("\tc ---> Create Team");
        System.out.println("\tq ---> Quit");

    }



}
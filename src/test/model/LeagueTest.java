package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LeagueTest {

    Team myTeam;
    Team mySecondTeam;
    League myLeague;
    Player myPlayer;

    @BeforeEach
    public void runBefore(){
        myLeague = new League();
        myTeam = new Team("testTeam");
        mySecondTeam = new Team("testSecondTeam");
        myPlayer = new Player("testPlayer","MID",12.5);
    }

    @Test
    public void testGetName() {
        assertEquals("The League",myLeague.getName());
    }

    @Test
    public void testGetLeaguePlayers() {
        Player p1 = new Player("test","pos",12.5);
        Player p2 = new Player("test2","pos",12.5);
        ArrayList<Player> testPlayer = new ArrayList<>();
        testPlayer.add(p1);
        testPlayer.add(p2);
        myLeague.addPlayerToLeague(p1);
        myLeague.addPlayerToLeague(p2);
        assertEquals(2,myLeague.lengthOfLeaguePlayers());
        assertEquals(testPlayer,myLeague.getPlayersInLeague());
    }

    @Test
    public void testGetLeagueTeams() {
        Team t1 = new Team("test1");
        Team t2 = new Team("test2");
        ArrayList<Team> testTeam = new ArrayList<>();
        testTeam.add(t1);
        testTeam.add(t2);
        myLeague.addTeam(t1);
        myLeague.addTeam(t2);
        assertEquals(2,myLeague.lengthOfLeagueTeams());
        assertEquals(testTeam,myLeague.getTeamsInLeague());
    }

    @Test
    public void testAddPlayerToLeague() {
        Player p = new Player("Jake","MID",12.5);
        myLeague.addPlayerToLeague(p);
        assertEquals(1,myLeague.lengthOfLeaguePlayers());
    }

    @Test
    public void testAddPlayerToLeagueMultiple() {
        Player p1 = new Player("Jake","MID",12.5);
        Player p2 = new Player("Jill","MID",12.5);
        myLeague.addPlayerToLeague(p1);
        myLeague.addPlayerToLeague(p2);
        assertEquals(2, myLeague.lengthOfLeaguePlayers());
    }

    @Test
    public void testAddTeam() {
        myLeague.addTeam(myTeam);
        assertEquals(1,myLeague.lengthOfLeagueTeams());
    }

    @Test
    public void testAddTeamMany() {
        myLeague.addTeam(myTeam);
        myLeague.addTeam(mySecondTeam);
        assertEquals(2,myLeague.lengthOfLeagueTeams());
    }

    @Test
    public void testAddTeamDuplicate() {
        myLeague.addTeam(myTeam);
        assertEquals(1,myLeague.lengthOfLeagueTeams());
        myLeague.addTeam(myTeam);
        assertEquals(1,myLeague.lengthOfLeagueTeams());
    }

    @Test
    public void testAddPlayerDuplicate() {
        myLeague.addPlayerToLeague(myPlayer);
        assertEquals(1,myLeague.lengthOfLeaguePlayers());
        myLeague.addPlayerToLeague(myPlayer);
        assertEquals(1,myLeague.lengthOfLeaguePlayers());
    }

    @Test
    public void testRemoveTeam() {
        myLeague.addTeam(myTeam);
        myLeague.addTeam(mySecondTeam);
        assertEquals(2,myLeague.lengthOfLeagueTeams());
        myLeague.removeTeam(myTeam);
        assertEquals(1,myLeague.lengthOfLeagueTeams());
    }

}

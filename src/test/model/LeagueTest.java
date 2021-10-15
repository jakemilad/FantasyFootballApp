package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LeagueTest {

    Team myTeam;
    Team mySecondTeam;
    League myLeague;

    @BeforeEach
    public void runBefore(){
        myLeague = new League();
        myTeam = new Team("testTeam");
        mySecondTeam = new Team("testSecondTeam");
    }

    @Test
    public void testAddTeam() {
        myLeague.addTeam(myTeam);
        assertEquals(1,myLeague.length());
    }

    @Test
    public void testAddTeamMany() {
        myLeague.addTeam(myTeam);
        myLeague.addTeam(mySecondTeam);
        assertEquals(2,myLeague.length());
    }

    @Test
    public void testRemoveTeam() {
        myLeague.addTeam(myTeam);
        myLeague.addTeam(mySecondTeam);
        assertEquals(2,myLeague.length());
        myLeague.removeTeam(myTeam);
        assertEquals(1,myLeague.length());
    }

}

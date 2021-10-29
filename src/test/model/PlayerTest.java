package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Player testNeymar;
    Player testMessi;
    Player testVirgil;
    Team testTeam;
    Team testTeamTwo;


    @BeforeEach
    public void runBefore() {
        testNeymar = new Player("Neymar", "ATT", 12.5);
        testMessi = new Player("Messi", "MID", 15.0);
        testVirgil = new Player("Virgil", "DEF", 11.5);
        testTeam = new Team("testTeam");
        testTeamTwo = new Team("testTeamTwo");
    }

    @Test
    public void testConstructor() {
        assertEquals("Neymar", testNeymar.getName());
        assertEquals("ATT", testNeymar.getPosition());
        assertEquals(12.5,testNeymar.getPrice());
    }

    @Test
    public void scoredGoal() {
        testNeymar.scoredGoal(1);
        assertEquals(1,testNeymar.getGoals());
        assertEquals(2,testNeymar.getPoints());
    }

    @Test
    public void scoredAssist() {
        testNeymar.scoredAssist(1);
        assertEquals(1,testNeymar.getPoints());
        assertEquals(1,testNeymar.getAssists());
    }

    @Test
    public void scoredGoalMultiple() {
        testNeymar.scoredGoal(1);
        assertEquals(1,testNeymar.getGoals());
        assertEquals(1 * 2,testNeymar.getPoints());

        testNeymar.scoredGoal(2);
        assertEquals(3,testNeymar.getGoals());
        assertEquals((1+2) * 2,testNeymar.getPoints());
    }

    @Test
    public void scoredAssistMultiple() {
        testNeymar.scoredAssist(1);
        assertEquals(1,testNeymar.getAssists());
        assertEquals(1 * 1,testNeymar.getPoints());

        testNeymar.scoredAssist(2);
        assertEquals(3,testNeymar.getAssists());
        assertEquals((1+2) * 1,testNeymar.getPoints());
    }

    @Test
    public void testScoredGoalTeam() {
        testTeam.addPlayer(testNeymar);
        testTeam.addPlayer(testMessi);
        testTeamTwo.addPlayer(testMessi);
        testNeymar.scoredGoalTeam(1,testTeam);
        testMessi.scoredGoalTeam(2,testTeam);
        testMessi.scoredGoalTeam(2,testTeamTwo);

        assertEquals(2, testTeam.length());
        assertEquals(1, testTeamTwo.length());
        assertEquals(2, testNeymar.getPoints());
        assertEquals(8, testMessi.getPoints());

        assertEquals(6,testTeam.getPoints());
        assertEquals(4,testTeamTwo.getPoints());
    }

    @Test
    public void testScoredAssistTeam() {
        testTeam.addPlayer(testNeymar);
        testTeam.addPlayer(testMessi);
        testTeamTwo.addPlayer(testMessi);
        testNeymar.scoredAssistTeam(1,testTeam);
        testMessi.scoredAssistTeam(2,testTeam);
        testMessi.scoredAssistTeam(2,testTeamTwo);

        assertEquals(2, testTeam.length());
        assertEquals(1, testTeamTwo.length());

        assertEquals(1, testNeymar.getPoints());
        assertEquals(4, testMessi.getPoints());

        assertEquals(3,testTeam.getPoints());
        assertEquals(2,testTeamTwo.getPoints());
    }

    @Test
    public void testisInTeam() {
        testTeam.addPlayer(testMessi);
        testTeam.addPlayer(testVirgil);
        assertTrue(testMessi.inTeam);
        assertTrue(testVirgil.inTeam);

        testTeamTwo.addPlayer(testMessi);
        assertTrue(testMessi.inTeam);
        assertFalse(testNeymar.inTeam);
    }

    @Test
    public void testInTeamForPlayer() {
        testTeam.addPlayer(testNeymar);
        assertTrue(testNeymar.inTeamForPlayer());
    }

    @Test
    public void testInTeamForPlayerFalse() {
        testTeam.addPlayer(testVirgil);
        testTeam.addPlayer(testNeymar);
        assertFalse(testMessi.inTeamForPlayer());
        assertTrue(testVirgil.inTeamForPlayer());
    }

    @Test
    public void testSetPoints() {
        int i = 3;
        testMessi.setPoints(i);
        assertEquals(3, testMessi.getPoints());
    }




}
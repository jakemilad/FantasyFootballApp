package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TeamTest {

    Player testMessi;
    Player testRonaldo;
    Player testSalah;
    Player testVirgil;
    Player testNeymar;
    Team testTeam;
    Team testTeamTwo;

    @BeforeEach
    public void runBefore(){
        testMessi = new Player("Messi","MID",15.0);
        testRonaldo = new Player("Ronaldo", "ATT",15.0);
        testSalah = new Player("Salah", "ATT",14.5);
        testVirgil = new Player("Virgil", "DEF",14.0);
        testNeymar = new Player("Neymar", "ATT",14.5);
        testTeam = new Team("testTeam");
        testTeamTwo = new Team("testTeamTwo");
    }

    @Test
    public void testAddPlayer(){
        testTeam.addPlayer(testMessi);
        assertEquals(1, testTeam.length());
    }

    @Test
    public void testAddPlayerMany() {
        testTeam.addPlayer(testMessi);
        testTeam.addPlayer(testRonaldo);
        testTeam.addPlayer(testSalah);
        assertEquals(3, testTeam.length());
    }

    @Test
    public void testAddPlayerAlreadyInTeam() {
        assertEquals(0,testTeam.length());

        testTeam.addPlayer(testMessi);
        testTeam.addPlayer(testVirgil);
        assertEquals(2,testTeam.length());
        testTeam.addPlayer(testMessi);
        assertEquals(2,testTeam.length());

    }

    @Test
    public void testRemovePlayer(){
        testTeam.addPlayer(testMessi);
        assertEquals(1, testTeam.length());
        testTeam.removePlayer(testMessi);
        assertEquals(0, testTeam.length());
    }

    @Test
    public void testRemovePlayerMultiple(){
        testTeam.addPlayer(testMessi);
        testTeam.addPlayer(testRonaldo);
        testTeam.addPlayer(testSalah);
        assertEquals(3, testTeam.length());
        testTeam.removePlayer(testRonaldo);
        testTeam.removePlayer(testMessi);
        assertEquals(1, testTeam.length());
    }

    @Test
    public void testGetTeamName() {
        String testString = testTeam.getTeamName();
        assertEquals("testTeam", testString);
    }

    @Test
    public void testInTeam() {
        testTeam.addPlayer(testMessi);
        assertEquals(1, testTeam.length());
        testTeam.addPlayer(testMessi);
        assertTrue(testTeam.inTeamForGivenPlayer(testMessi));
        assertEquals(1, testTeam.length());
    }

    @Test
    public void testGetPlayerFromTeam() {
        testTeam.addPlayer(testMessi);
        testTeam.addPlayer(testRonaldo);
        testTeam.addPlayer(testSalah);
        assertEquals(testMessi, testTeam.getPlayerFromTeam("Messi"));
        assertEquals(testRonaldo, testTeam.getPlayerFromTeam("Ronaldo"));
        assertEquals(null, testTeam.getPlayerFromTeam("Virgil"));
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
    public void testInTeamForGivenPlayer() {
        testTeam.addPlayer(testMessi);
        assertTrue(testTeam.inTeamForGivenPlayer(testMessi));
        assertFalse(testTeam.inTeamForGivenPlayer(testSalah));

        testTeam.addPlayer(testRonaldo);
        assertTrue(testTeam.inTeamForGivenPlayer(testRonaldo));
        testTeamTwo.addPlayer(testRonaldo);
        assertTrue(testTeamTwo.inTeamForGivenPlayer(testRonaldo));
        assertFalse(testTeamTwo.inTeamForGivenPlayer(testSalah));
    }

    @Test
    public void testSetPointsForTeam() {
        int i = 3;
        testTeam.setPointsForTeam(i);
        assertEquals(3,testTeam.getPoints());
    }



}

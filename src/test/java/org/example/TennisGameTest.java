package org.example;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TennisGameTest {

    @Test
    void testInvalidNumberOfPlaysSuprassTheGame() {
        String validScript = "AAAAAAAAA";
        IGame tennisGame = new TennisGame(validScript);

        InvalidGameException exception = assertThrows(InvalidGameException.class, tennisGame::runTheGame);
        assertEquals("Invalid game script : number of plays surpass game", exception.getMessage());
    }

    @Test
    void testInvalidNumberOfPLays() {
        String validScript = "BBBAAA";
        IGame tennisGame = new TennisGame(validScript);
        InvalidGameException exception = assertThrows(InvalidGameException.class, tennisGame::runTheGame);
        assertEquals("Invalid game script : number of plays don't end game", exception.getMessage());
    }

    @Test
    void testInvalidScript() {
        String invalidScript = "AABXC";
        IGame tennisGame = new TennisGame(invalidScript);
        InvalidGameException exception = assertThrows(InvalidGameException.class, tennisGame::runTheGame);
        assertEquals("Invalid game script : non supported characters", exception.getMessage());
    }

    @Test
    void testPlayerAWinTheGame() {
        String validScript = "ABABAA";
        IGame tennisGame = new TennisGame(validScript);
        assertDoesNotThrow(() -> {
            String result = tennisGame.runTheGame();
            assertTrue(result.contains("Player A : 15 / Player B : 0 \n" +
                    "Player A : 15 / Player B : 15 \n" +
                    "Player A : 30 / Player B : 15 \n" +
                    "Player A : 30 / Player B : 30 \n" +
                    "Player A : 40 / Player B : 30 \n" +
                    "Player A win the game"));
        });
    }

    @Test
    void testPlayerBWinTheGameAfterDeuce() {
        String validScript = "ABBAABBABAABBB";
        IGame tennisGame = new TennisGame(validScript);
        assertDoesNotThrow(() -> {
            String result = tennisGame.runTheGame();
            assertTrue(result.contains("Player A : 15 / Player B : 0 \n" +
                    "Player A : 15 / Player B : 15 \n" +
                    "Player A : 15 / Player B : 30 \n" +
                    "Player A : 30 / Player B : 30 \n" +
                    "Player A : 40 / Player B : 30 \n" +
                    "Player A : 40 / Player B : 40 \n" +
                    "Player B win the game"));
        });
    }

}


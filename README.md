# Tennis Game Kata

## Introduction

This Kata simulates a tennis game with scoring points and advantages.

## Code Structure

The code is organized to provide a global solution for managing tennis games. It includes support for various scenarios:

validation of the game is based on function validator() for input structure and in the run time for logical validation

### 1. Input Plays Can't End Game

The code handles situations where the input plays do not lead to the end of the game. This ensures that the game progresses as expected.

### 2. Input Plays Surpass Game Win

The implementation considers cases where the input plays surpass the conditions required for winning the game. This helps prevent unexpected outcomes and maintains the integrity of the game.

### 3. Invalid Input

The code accounts for invalid input scenarios, such as unsupported characters in the game script. It throws an `InvalidGameException` when non-supported characters are encountered, ensuring that the game script remains valid.

## Usage

To use the Tennis Game Kata main in [App.java](src/main/java/org/example/App.java), follow these steps:

1. Create an instance of the `Game` class, providing the game script as input.
2. Run the game using the `runTheGame()` method, which returns the result of the game.


#### Example:

```java
// Create an instance of the Game class with a valid script
String validScript = "AAABBB";
Game game = new Game(validScript);

// Run the game
try {
     IGame tennisGame = new TennisGame("ABABAA");
    System.out.println(tennisGame.runTheGame());
}catch (InvalidGameException invalidGameException){
    System.out.println(invalidGameException.getMessage());
}
```

## Test

test are based on Junit 5 

you can run class [TennisGameTest](src/test/java/org/example/TennisGameTest.java)


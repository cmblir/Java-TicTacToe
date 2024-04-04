package test;

import org.junit.Test;
import main.TicTacToe.Board;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashSet;

public class BoardTest {
    static final int size = 3;
    public enum State {Blank, X, O}

    private State[][] board;
    private State playerTurn;
    private State winner;
    private HashSet<Integer> movesAvailable;

    private int moveCount;
    private boolean gameOver;

    @BeforeEach
    void before() {
        board = new State[size][size];
        movesAvailable = new HashSet<>();
        reset();
    }

    @AfterEach
    public void reset() {
        moveCount = 0;
        gameOver = false;
        playerTurn = State.X;
        winner = State.Blank;
    }

    @Test
    public void move() {
    }

    @Test
    public void testMove() {
    }

    @Test
    public void isGameOver() {
    }

    @Test
    public void toArray() {
    }

    @Test
    public void getTurn() {
    }

    @Test
    public void getWinner() {
    }

    @Test
    public void getAvailableMoves() {
    }

    @Test
    public void checkCol() {
    }

    @Test
    public void getDeepCopy() {
    }

    @Test
    public void testToString() {
    }
}
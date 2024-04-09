package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import main.Algorithm.AlphaBetaPruning;
import main.TicTacToe.Board;

public class AlphaBetaPruningTest {
    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRun_InvalidMaxPly() {
        AlphaBetaPruning.run(Board.State.X, board, 0); // 최대 깊이는 0보다 커야함
    }

    @Test
    public void testScore_PlayerXWins() {
        board.move(0, 0);
        board.move(1, 0);
        board.move(0, 1);
        board.move(1, 1);
        board.move(0, 2);

        assertEquals(10, AlphaBetaPruning.score(Board.State.X, board));
    }

    @Test
    public void testScore_PlayerOWins() {
        board.move(1, 0);
        board.move(0, 0);
        board.move(1, 1);
        board.move(0, 1);
        board.move(2, 0);
        board.move(0, 2);

        assertEquals(-10, AlphaBetaPruning.score(Board.State.X, board));
    }

/*    @Test
    public void testScore_Draw() {
        board.move(0, 0);
        board.move(1, 0);
        board.move(2, 0);
        board.move(0, 1);
        board.move(1, 1);
        board.move(0, 2);
        board.move(2, 1);
        board.move(1, 2);
        board.move(2, 2);

        assertEquals(0, AlphaBetaPruning.score(Board.State.X, board));
    }*/

    @Test
    public void testRun_PlayerXWins() {
        board.move(0, 0);
        board.move(1, 0);
        board.move(0, 1);
        board.move(1, 1);
        board.move(0, 2);

        // 플레이어 X가 최적의 수를 찾아야 함
        AlphaBetaPruning.run(Board.State.X, board, 9);

        assertTrue(board.isGameOver());
        assertEquals(Board.State.X, board.getWinner());
    }

    @Test
    public void testRun_PlayerOWins() {
        board.move(1, 0);
        board.move(0, 0);
        board.move(1, 1);
        board.move(0, 1);
        board.move(2, 0);
        board.move(0, 2);

        // 플레이어 X가 최적의 수를 찾아야 함
        AlphaBetaPruning.run(Board.State.X, board, 9);

        assertTrue(board.isGameOver());
        assertEquals(Board.State.O, board.getWinner());
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void testRun_Draw() {
        board.move(0, 0);
        board.move(1, 0);
        board.move(2, 0);
        board.move(0, 1);
        board.move(1, 1);
        board.move(2, 1);
        board.move(2, 1);
        board.move(2, 0);
        board.move(2, 2);
        // 플레이어 X가 최적의 수를 찾아야 함
        AlphaBetaPruning.run(Board.State.X, board, 9);
    }*/
}

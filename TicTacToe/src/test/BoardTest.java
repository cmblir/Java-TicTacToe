package test;

import static org.junit.Assert.*;

import main.TicTacToe.Board;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {
    public Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @Test(expected = IllegalStateException.class)
    public void testReset() {
        board.move(0, 0); // X's turn
        board.reset();

        assertEquals(Board.State.X, board.getTurn());
        assertFalse(board.isGameOver());
        assertNull(board.getWinner());
        assertEquals(Board.size * Board.size, board.getAvailableMoves().size());
    }

    @Test
    public void testMove_ValidMove() {
        assertTrue(board.move(0, 0)); // X's turn
        assertEquals(Board.State.O, board.getTurn());
    }

    @Test
    public void testMove_InvalidMove() {
        board.move(0, 0); // X's turn
        assertFalse(board.move(0, 0)); // Trying to move to the same position
        assertEquals(Board.State.O, board.getTurn()); // Turn shouldn't change
    }

    @Test(expected = IllegalStateException.class)
    public void testMove_GameOver() {
        board.move(0, 0);
        board.move(1, 0);
        board.move(0, 1);
        board.move(1, 1);
        board.move(0, 2);
        board.move(1, 2);
        board.move(2, 0);
        board.move(2, 1);
        board.move(2, 2); // Last move, should end the game

        // Trying to move after game over
        board.move(1, 1);
    }

    @Test(expected = IllegalStateException.class)
    public void testGetDeepCopy() {
        Board copy = board.getDeepCopy();

        assertNotSame(board, copy);
        assertArrayEquals(board.toArray(), copy.toArray());
        assertEquals(board.getTurn(), copy.getTurn());
        assertEquals(board.isGameOver(), copy.isGameOver());
        assertEquals(board.getWinner(), copy.getWinner());
        assertEquals(board.getAvailableMoves(), copy.getAvailableMoves());
    }

    @Test
    public void testCheckRow_WinningRow() {
        board.move(0, 0); // X
        board.move(0, 1); // O
        board.move(1, 0); // X
        board.move(1, 1); // O
        board.move(2, 0); // X, should win

        assertTrue(board.isGameOver());
        assertEquals(Board.State.X, board.getWinner());
    }

    @Test(expected = IllegalStateException.class) // 승자가 없으므로 어차피 승리 호출은 에러가 남
    public void testCheckRow_NonWinningRow() {
        board.move(0, 0); // X
        board.move(0, 1); // O
        board.move(1, 0); // X
        board.move(1, 1); // O
        board.move(2, 1); // X, should not win

        assertFalse(board.isGameOver());
        assertNull(board.getWinner());
    }

    @Test
    public void testCheckCol_WinningCol() {
        board.move(0, 0); // X
        board.move(1, 0); // O
        board.move(0, 1); // X
        board.move(1, 1); // O
        board.move(0, 2); // X, should win

        assertTrue(board.isGameOver());
        assertEquals(Board.State.X, board.getWinner());
    }

    @Test(expected = IllegalStateException.class)
    public void testCheckCol_NonWinningCol() {
        board.move(0, 0); // X
        board.move(1, 0); // O
        board.move(0, 1); // X
        board.move(1, 1); // O
        board.move(2, 1); // X, should not win

        assertFalse(board.isGameOver());
        assertNull(board.getWinner());
    }

    @Test
    public void testCheckDiagonalFromTopLeft_WinningDiagonal() {
        board.move(0, 0); // X
        board.move(0, 1); // O
        board.move(1, 1); // X
        board.move(0, 2); // O
        board.move(2, 2); // X, should win

        assertTrue(board.isGameOver());
        assertEquals(Board.State.X, board.getWinner());
    }

    @Test(expected = IllegalStateException.class)
    public void testCheckDiagonalFromTopLeft_NonWinningDiagonal() {
        board.move(0, 0); // X
        board.move(0, 1); // O
        board.move(1, 1); // X
        board.move(0, 2); // O
        board.move(2, 0); // X, should not win

        assertFalse(board.isGameOver());
        assertNull(board.getWinner());
    }

    @Test
    public void testCheckDiagonalFromTopRight_WinningDiagonal() {
        board.move(0, 2); // X
        board.move(0, 1); // O
        board.move(1, 1); // X
        board.move(0, 0); // O
        board.move(2, 0); // X, should win

        assertTrue(board.isGameOver());
        assertEquals(Board.State.X, board.getWinner());
    }

    @Test(expected = IllegalStateException.class)
    public void testCheckDiagonalFromTopRight_NonWinningDiagonal() {
        board.move(0, 2); // X
        board.move(0, 1); // O
        board.move(1, 1); // X
        board.move(0, 0); // O
        board.move(1, 0); // X, should not win

        assertFalse(board.isGameOver());
        assertNull(board.getWinner());
    }

    @Test
    public void testToString() {
        boolean moved1 = board.move(0, 0);
        System.out.println(board.toString());
    }
}

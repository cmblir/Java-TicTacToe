package TicTacToe;

import java.util.HashSet;

public class Board {

    static final int size = 3;
    public enum State {Blank, X, O}

    private State[][] board;
    private State playerTurn;
    private State winner;
    private HashSet<Integer> movesAvailable;

    private int moveCount;
    private boolean gameOver;

    Board() {
        board = new State[size][size];
        movesAvailable = new HashSet<>();
        reset();
    }

    private void initialize() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = State.Blank;
            }
        }

        movesAvailable.clear();

        for (int i = 0; i < size * size; i++) {
            movesAvailable.add(i);
        }
    }

    void reset() {
        moveCount = 0;
        gameOver = false;
        playerTurn = State.X;
        winner = State.Blank;
        initialize();
    }

    public boolean move(int index) {
        return move(index% size, index/ size);
    }

    public boolean move(int x, int y) {
        if (gameOver) {
            throw new IllegalStateException("틱택토는 끝났습니다. 움직이지 않으면 플레이할 수 있습니다.");
        }

        if (board[y][x] == State.Blank) {
            board[y][x] = playerTurn;
        } else {
            return false;
        }

        moveCount++;
        movesAvailable.remove(y * size + x);

        if (moveCount == size * size) {
            winner = State.Blank;
            gameOver = true;
        }

        checkRow(y);
        checkCol(x);
        checkDiagonalFromTopLeft(x, y);
        checkDIagonalFromTopRight(x, y);

        playerTurn = (playerTurn == State.X) ? State.O : State.X;
        return true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    State[][] toArray() {
        return board.clone();
    }

    public State getTurn() {
        return playerTurn;
    }

    public State getWinner() {
        if (gameOver) {
            return winner;
        } else {
            throw new IllegalStateException("틱택토가 아직 끝나지 않았습니다.");
        }
    }

    public HashSet<Integer> getAvailableMoves() {
        return movesAvailable;
    }

    private void checkRow(int row) {
        for (int i = 1; i < size; i++) {
            if (board[row][i] != board[row][i-1]) break;
            if (i == size - 1) {
                winner = playerTurn;
                gameOver = true;
            }
        }
    }

    public void checkCol(int col) {
        for (int i = 1; i < size; i++) {
            if (board[i][col] != board[i-1][col]) break;
            if (i == size - 1) {
                winner = playerTurn;
                gameOver = true;
            }
        }
    }

    private void checkDiagonalFromTopLeft(int x, int y) {
        if (x == y) {
            for (int i = 1; i < size; i++) {
                if (board[i][i] != board[i - 1][i - 1]) break;
                if (i == size - 1) {
                    winner = playerTurn;
                    gameOver = true;
                }
            }
        }
    }

    private void checkDIagonalFromTopRight(int x, int y) {
        if (size - 1 - x == y) {
            for (int i = 1; i < size; i++) {
                if (board[size - 1 - i][i] != board[size - i][i - 1]) break;
                if (i == size - 1) {
                    winner = playerTurn;
                    gameOver = true;
                }
            }
        }
    }

    public Board getDeepCopy() {
        Board board = new Board();
        int boardSize = board.board.length;

        for (int i = 0; i < boardSize; i++) {
            board.board[i] = this.board[i].clone();
        }

        board.playerTurn = this.playerTurn;
        board.winner = this.winner;
        board.moveCount = this.moveCount;
        board.movesAvailable = new HashSet<>();
        board.movesAvailable.addAll(this.movesAvailable);
        board.gameOver = this.gameOver;
        return board;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {

                if (board[y][x] == State.Blank) {
                    sb.append("-");
                } else {
                    sb.append(board[y][x].name());
                }
                sb.append(" ");
            }
            if (y != size - 1) {
                sb.append("\n");
            }
        }
        return new String(sb);
    }
}
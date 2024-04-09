package main.Algorithm;

import main.TicTacToe.Board;

public class Algorithms {

    private Algorithms() {}

    public static void random (Board board) {
        Random.run(board);
    }

    public static void alphaBetaPruning(Board board) {
        AlphaBetaPruning.run(board.getTurn(), board, Double.POSITIVE_INFINITY);
    }

    public static void alphaBetaAdvance(Board board) {
        AlphaBetaAdvanced.run(board.getTurn(), board, Double.POSITIVE_INFINITY);
    }
}

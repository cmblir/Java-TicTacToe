package main.Algorithm;

import main.TicTacToe.Board;

public class AlphaBetaPruning {
    private static double maxPly;

    private AlphaBetaPruning() {}

    static void run (Board.State player, Board board, double maxPly) {
        if (maxPly < 1) throw new IllegalArgumentException("최대 깊이는 무조건 0보다 커야합니다.");

        AlphaBetaPruning.maxPly = maxPly;
        alphaBetaPruning(player, board, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);

    }

    private static int alphaBetaPruning (Board.State player, Board board, double alpha, double beta, int currentPly) {
        if (currentPly ++ == maxPly || board.isGameOver()) return score(player, board);
        if (board.getTurn() == player) {
            return getMax(player, board, alpha, beta, currentPly);
        } else {
            return getMin(player, board, alpha, beta, currentPly);
        }
    }

    private static int getMax (Board.State player, Board board, double alpha, double beta, int currentPly) {
        int indexOfBestMove = -1;

        for (Integer theMove : board.getAvailableMoves()) {

            Board modifiedBoard = board.getDeepCopy();
            modifiedBoard.move(theMove);
            int score = alphaBetaPruning(player, modifiedBoard, alpha, beta, currentPly);

            if (score > alpha) {
                alpha = score;
                indexOfBestMove = theMove;
            }

            // 가지치기
            if (alpha >= beta) break;
        }

        if (indexOfBestMove != -1) {
            board.move(indexOfBestMove);
        }
        return (int) alpha;
    }


    private static int getMin (Board.State player, Board board, double alpha, double beta, int currentPly) {
        int indexOfBestMove = -1;

        for (Integer theMove : board.getAvailableMoves()) {

            Board modifiedBoard = board.getDeepCopy();
            modifiedBoard.move(theMove);

            int score = alphaBetaPruning(player, modifiedBoard, alpha, beta, currentPly);

            if (score < alpha) {
                alpha = score;
                indexOfBestMove = theMove;
            }

            // 가지치기
            if (alpha >= beta) break;
        }

        if (indexOfBestMove != -1) {
            board.move(indexOfBestMove);
        }
        return (int) beta;
    }

    private static int score (Board.State player, Board board) {
        if (player == Board.State.Blank) {
            throw new IllegalArgumentException("플레이어는 무조건 X 또는 O입니다.");
        }

        Board.State opponent = (player == Board.State.X) ? Board.State.O : Board.State.X;

        if (board.isGameOver() && board.getWinner() == player) {
            return 10;
        } else if (board.isGameOver() && board.getWinner() == opponent) {
            return -10;
        } else {
            return 0;
        }
    }
}

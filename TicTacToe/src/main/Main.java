package main;

import main.Algorithm.Algorithms;
import main.TicTacToe.Board;

import java.util.Scanner;

public class Main {

    private Board board;

    private Scanner sc = new Scanner(System.in);

    private void play() {
        System.out.println("게임을 시작합니다.");

        while (true) {
            printGameStatus();
            playMove();

            if (board.isGameOver()) {
                printWinner();

                if (!tryAgain()) {
                    break;
                }
            }
        }
    }

    private void printGameStatus() {
        System.out.println("\n" + board + "\n");
        System.out.println(board.getTurn().name());
    }

    private void playMove() {
        if (board.getTurn() == Board.State.X) {
            getPlayerMove();
        } else {
            Algorithms.alphaBetaAdvance(board);
        }
    }
}
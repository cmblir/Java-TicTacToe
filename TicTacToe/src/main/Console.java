package main;

import main.Algorithm.Algorithms;
import main.TicTacToe.Board;

import java.util.Scanner;

public class Console {

    private Board board;

    private Scanner sc = new Scanner(System.in);

    private Console() {
        board = new Board();
    }

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

    private void getPlayerMove() {
        System.out.print("이동할 위치 : ");

        int move = sc.nextInt();

        if (move < 0 || move >= Board.size * Board.size) {
            System.out.println("\n잘못된 움직임.");
            System.out.println("\n위치값은 무조건 0과 " + (Board.size * Board.size -1) + ", 사이여야합니다.");
        } else if (!board.move(move)) {
            System.out.println("\n잘못된 움직임.");
            System.out.println("\n빈 공간만 이동이 가능합니다.");
        }
    }

    private void printWinner() {
        Board.State winner = board.getWinner();

        System.out.println("\n" + board + "\n");

        if (winner == Board.State.Blank) {
            System.out.println("무승부입니다.");
        } else {
            System.out.println("승자는 " + winner.toString() + " wins!");
        }
    }

    private boolean tryAgain() {
        if (promptTryAgain()) {
            board.reset();
            System.out.println("새로운 게임을 시작합니다.");
            System.out.println("X의 턴입니다.");
            return true;
        }
        return false;
    }

    private boolean promptTryAgain() {
        while (true) {
            System.out.print("게임을 다시 시작하겠습니까 ? (Y/N)");
            String response = sc.next();
            if (response.equalsIgnoreCase("y")) {
                return true;
            } else if (response.equalsIgnoreCase("n")) {
                return false;
            }
            System.out.println("잘못된 입력입니다.");
        }
    }

    public static void main(String[] args) {
        Console ticTacToe = new Console();
        ticTacToe.play();
    }
}
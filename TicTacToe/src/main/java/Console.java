package main;

import main.Algorithm.Algorithms;
import main.TicTacToe.Board;
import main.TicTacToe.Board.State;

import java.util.Scanner;

public class Console {
    private Board board;
    private Scanner sc;

    private Console() {
        this.sc = new Scanner(System.in);
        this.board = new Board();
    }

    private void play() {
        System.out.println("게임을 시작합니다.");

        do {
            do {
                this.printGameStatus();
                this.playMove();
            } while(!this.board.isGameOver());

            this.printWinner();
        } while(this.tryAgain());

    }

    private void printGameStatus() {
        System.out.println("\n" + String.valueOf(this.board) + "\n");
        System.out.println(this.board.getTurn().name());
    }

    private void playMove() {
        if (this.board.getTurn() == State.X) {
            this.getPlayerMove();
        } else {
            Algorithms.alphaBetaAdvance(this.board);
        }

    }

    private void getPlayerMove() {
        System.out.print("이동할 위치 : ");
        int move = this.sc.nextInt();
        if (move >= 0 && move < 9) {
            if (!this.board.move(move)) {
                System.out.println("\n잘못된 움직임.");
                System.out.println("\n빈 공간만 이동이 가능합니다.");
            }
        } else {
            System.out.println("\n잘못된 움직임.");
            System.out.println("\n위치값은 무조건 0과 8, 사이여야합니다.");
        }

    }

    private void printWinner() {
        Board.State winner = this.board.getWinner();
        System.out.println("\n" + String.valueOf(this.board) + "\n");
        if (winner == State.Blank) {
            System.out.println("무승부입니다.");
        } else {
            System.out.println("승자는 " + winner.toString() + " wins!");
        }

    }

    private boolean tryAgain() {
        if (this.promptTryAgain()) {
            this.board.reset();
            System.out.println("새로운 게임을 시작합니다.");
            System.out.println("X의 턴입니다.");
            return true;
        } else {
            return false;
        }
    }

    private boolean promptTryAgain() {
        while(true) {
            System.out.print("게임을 다시 시작하겠습니까 ? (Y/N)");
            String response = this.sc.next();
            if (response.equalsIgnoreCase("y")) {
                return true;
            }

            if (response.equalsIgnoreCase("n")) {
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
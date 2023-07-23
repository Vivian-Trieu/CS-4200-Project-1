package _8puzzleproblem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            Main ai = new Main();
            int choice;
            Scanner kb = new Scanner(System.in);
            System.out.println("CS 4200 Project 1\nSelect Input Method:\n[1] Random\n[2] Manual Input\n[3] Exit");
            while (true) {
                try {
                    choice = Integer.parseInt(kb.nextLine());
                    if (choice >= 1 && choice <= 3) {
                        break;
                    } else {
                        System.out.println("Invalid option. Please try again.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                }
            }   

            switch (choice) {
                case 1:
                    ai.randomPuzzle();
                    break;
                case 2:
                    ai.manualInputPuzzle();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    break;
            }

            kb.close();
    }

    // private AStar aStar = new AStar();

    /**
     * Run AStar on a randomly generated puzzle
     */
    private void randomPuzzle() {
        Puzzle puzzle = new Puzzle();
        Integer[] randomPuzzle = puzzle.generateRandomPuzzle();
        System.out.println("Initial Puzzle:");
        printPuzzle(randomPuzzle);
    }

    /**
     * Run AStar on user input
     */
    private void manualInputPuzzle() {
        Puzzle puzzle = new Puzzle();
        Integer[] randomPuzzle = puzzle.generateManualPuzzle();
        System.out.println("Initial Puzzle:");
        printPuzzle(randomPuzzle);
        int emptyPos = puzzle.getEmptyPosition();
        System.out.println(emptyPos);

    }

    public static void printPuzzle(Integer[] puzzle) {
        for (int i = 0; i < 9; i++) {
            System.out.print(puzzle[i] + " ");
            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }
    }
}

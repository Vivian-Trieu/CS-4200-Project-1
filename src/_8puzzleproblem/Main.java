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

    private AStar aStar = new AStar();

    /**
     * Run AStar on a randomly generated puzzle
     */
    private void randomPuzzle() {
        Puzzle puzzle = new Puzzle();
        Integer[] randomPuzzle = puzzle.generateRandomPuzzle();
        System.out.println("Puzzle:");
        puzzle.printPuzzle(randomPuzzle);
        System.out.println();
        Node init = puzzle.getInitialNode();
        
        System.out.println("Solving with H1: ");
        long start1 = System.currentTimeMillis();
        Node h1Result = aStar.runAStar(init, true);
        long end1 = System.currentTimeMillis();
        long total1 = end1 - start1;

        System.out.println("Solving with H2: ");
        long start2 = System.currentTimeMillis();
        Node h2Result = aStar.runAStar(init, false);
        long end2 = System.currentTimeMillis();
        long total2 = end2 - start2;

        System.out.println();
        System.out.println("H1 Search Cost: " + h1Result.getSearchCost());
        System.out.println("H1 Total Time: " + total1 + " ms\n");
        System.out.println("H2 Search Cost: " + h2Result.getSearchCost());
        System.out.println("H2 Search Cost: " + total2 + " ms\n");

    }

    /**
     * Run AStar on user input
     */
    private void manualInputPuzzle() {
        Puzzle puzzle = new Puzzle();
        Integer[] manualPuzzle = puzzle.generateManualPuzzle();
        System.out.println("Puzzle:");
        puzzle.printPuzzle(manualPuzzle);
        System.out.println();
        Node init = puzzle.getInitialNode();
        
        System.out.println("Solving with H1: ");
        long start1 = System.currentTimeMillis();
        Node h1Result = aStar.runAStar(init, true);
        long end1 = System.currentTimeMillis();
        long total1 = end1 - start1;

        System.out.println("Solving with H2: ");
        long start2 = System.currentTimeMillis();
        Node h2Result = aStar.runAStar(init, false);
        long end2 = System.currentTimeMillis();
        long total2 = end2 - start2;

        System.out.println();
        System.out.println("H1 Search Cost: " + h1Result.getSearchCost());
        System.out.println("H1 Total Time: " + total1 + " ms\n");
        System.out.println("H2 Search Cost: " + h2Result.getSearchCost());
        System.out.println("H2 Search Cost: " + total2 + " ms\n");
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

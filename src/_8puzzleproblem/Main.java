package _8puzzleproblem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            Main ai = new Main();
            int validInput = ai.getInputMethod();
            switch (validInput) {
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
    }

    private AStar aStar = new AStar();

    /**
     * Create a randomly generated puzzle
     */
    private void randomPuzzle() {

    }

    /**
     * Create a puzzle based on user input
     */
    private void manualInputPuzzle() {

    }

    /**
     * @return user input that has been checked for validity
     * Gets the puzzle input method
     */
    private int getInputMethod() {
        int validInput = 1;
        String puzzleSelect = "CS 4200 Project 1\n";
        puzzleSelect += "Select Input Method:\n";
        puzzleSelect += "[1] Random\n[2] Manual Input\n[3] Exit";
        
        while (true) {
            System.out.println(puzzleSelect);
            Scanner kb = new Scanner(System.in);
            String input = kb.nextLine();
            try {
                validInput = Integer.parseInt(input);
                // Check if input is a valid option
                if (validInput >= 1 && validInput <= 3) {
                    break;
                } else {
                    System.out.println("Input is not a valid option");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input is not a number. Please try again.");
            }
        }
        return validInput;
    }

}

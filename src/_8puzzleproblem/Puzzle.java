package _8puzzleproblem;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Collections;
import java.util.HashSet;

public class Puzzle {
    private Integer[] initialPuzzle = new Integer[9];
    private Node initialNode;
    int emptyPosition;
    final private Integer[] goal = {0,1,2,3,4,5,6,7,8};

    /* 
    public Puzzle(Integer[] initialPuzzle) {
        this.initialPuzzle = initialPuzzle;
        this.initialNode = null;
    }  \
    */  

    public Node getInitialNode() {
        return initialNode;
    }

    public Integer[] getInitialPuzzle() {
        return initialPuzzle;
    }

    public Integer[] getGoal() {
        return goal;
    }

    public int getEmptyPosition() {
        return emptyPosition;
    }

    public void setInitialNode(Node init) {
        initialNode = init;
    }

    public void setInitialPuzzle(Integer[] puzzle) {
        initialPuzzle = puzzle;
    }

    /**
     * Generates a random puzzle with numbers 0-8 and ensures it is solvable. 
     * @return An array of Integers representing the generated random puzzle with numbers 0 to 8
     */
    public Integer[] generateRandomPuzzle() {
        List<Integer> numbers = Arrays.asList(0,1,2,3,4,5,6,7,8);
        Collections.shuffle(numbers);
        Integer[] randomPuzzle = new Integer[numbers.size()];
        numbers.toArray(randomPuzzle);

        // Check if the random puzzle is solvable first, regenerate if solvable is false
        while (!checkIfSolvable(randomPuzzle)) {
            Collections.shuffle(numbers);
            numbers.toArray(randomPuzzle);
        }
        
        emptyPosition = Arrays.asList(randomPuzzle).indexOf(0);
        setInitialPuzzle(randomPuzzle);
        setInitialNode(new Node(randomPuzzle, randomPuzzle, 0, "no_op", null, emptyPosition));
        return randomPuzzle;
    }

    public Integer[] generateManualPuzzle() {
        Scanner kb = new Scanner(System.in);
        Integer[] userPuzzle = new Integer[9];
        
        System.out.println("Enter numbers from 0 to 8 (without repeats) in an array format (ex: 0 1 2 3 4 5 6 7 8):");

        while (true) {
            
            String userInput = kb.nextLine();
            String[] numbers = userInput.split(" ");
            boolean validInput = true;

            if (numbers.length != 9) {
                System.out.println("Invalid input format. Please enter exactly 9 numbers separated by commas.");
                continue;
            }

            for (int i = 0; i < numbers.length; i++) {
                try {
                    userPuzzle[i] = Integer.parseInt(numbers[i].trim());
                    if (userPuzzle[i] < 0 || userPuzzle[i] > 8) {
                        validInput = false;
                        break;
                    }
                } catch (NumberFormatException e) {
                    validInput = false;
                    break;
                }
            }

            if (!validInput) {
                System.out.println("Invalid input. Please enter valid numbers from 0 to 8.");
                continue;
            }

            if (!isValidInput(userPuzzle)) {
                System.out.println("Numbers should be unique with no repeats. Please try again.");
                continue;
            }

            if (!checkIfSolvable(userPuzzle)) {
                System.out.println("Puzzle is not solvable! Try again.");
                continue;
            }

            emptyPosition = Arrays.asList(userPuzzle).indexOf(0);
            setInitialPuzzle(userPuzzle);
            setInitialNode(new Node(userPuzzle, userPuzzle, 0, "no_op", null, emptyPosition));
            kb.close();
            return userPuzzle;
        }
    }

    /**
     * Checks if the given input array contains numbers from 0-8 without any repeats
     * @param userInput An array of Integers representing the user input
     * @return true if the input array is valid (contains numbers 0-8 without any repeats)
     *         false otherwise
     */
    private boolean isValidInput(Integer[] userInput) {
        // Check if input is an array
        if (userInput == null) {
            return false;
        }

        // Check if numbers are between 0-8
        for (int num : userInput) {
            if (num < 0 || num > 8) {
                return false;
            }
        }
        // Check if number hasn't been used already
        Set<Integer> numSet = new HashSet<>();
        for (int num : userInput) {
            if (!numSet.add(num)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Method to check if a puzzle if solvable by checking the number of inversions
     * @return true if number of inversions is an even number, meaning it is solvable
     *         false if number of inversions is odd
     */
    public static boolean checkIfSolvable(Integer[] puzzle) {
        int inversions = 0;
        for (int i = 0; i < puzzle.length - 1; i++) {
            for (int j = i + 1; j < puzzle.length; ++j) {
                if (puzzle[i].equals(0) || puzzle[j].equals(0)) {
                    continue;
                }
                
                if (puzzle[i] > puzzle[j]) {
                    inversions++;
                }
            }
        }
        return inversions % 2 == 0;
    }

        /**
     * Print the puzzle in a 3x3 format.
     * @param puzzle The puzzle to be printed.
     */
    public void printPuzzle(Integer[] puzzle) {
        for (int i = 0; i < 9; i++) {
            System.out.print(puzzle[i] + " ");
            if ((i + 1) % 3 == 0) {
                System.out.println();
            }
        }
    }
}

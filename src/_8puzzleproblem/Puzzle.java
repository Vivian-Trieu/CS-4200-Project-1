package _8puzzleproblem;

import java.util.Arrays;
import java.util.List;
import java.util.Collections;

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

    /**
     * Method to check if a puzzle if solvable by checking the number of inversions
     * @return true if number of inversions is an even number
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

}

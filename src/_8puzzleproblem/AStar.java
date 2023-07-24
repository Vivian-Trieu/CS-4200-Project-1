package _8puzzleproblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {
    private PriorityQueue<Node> frontier;
    private final Integer[] goal = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private HashSet<Node> explored;
    

    public Node runAStar(Node initialNode, boolean H1) {
        int searchCost = 0;
        int stepCounter = 0;
        explored = new HashSet<>();
        frontier = new PriorityQueue<>((final Node o1, final Node o2) -> getHeuristic(o1, H1) - getHeuristic(o2, H1));
        
        frontier.add(initialNode);
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            explored.add(current);

            System.out.println("Step: " + stepCounter);
            new Puzzle().printPuzzle(current.getCurrentArray());
            System.out.println();
            
            if (Arrays.equals(current.getCurrentArray(), goal)) {
                System.out.println("Finished.\n");
                return current;
            }

            ArrayList<Node> successors = current.expandNode();
            /*
            for (Node successor : successors) {
                if (!explored.contains(successor)) {
                    searchCost++;
                    successor.setSearchCost(searchCost);
                    successor.setExploredSize(explored.size());
                    successor.setFrontierSize(frontier.size());
                    frontier.add(successor);
                }
            } 
            */
            for (int i = 0; i < successors.size(); ++i) {
                if (!explored.contains(successors.get(i))) {
                    searchCost++;
                    successors.get(i).setSearchCost(searchCost);
                    successors.get(i).setExploredSize(explored.size());
                    successors.get(i).setFrontierSize(frontier.size());
                    frontier.add(successors.get(i));
                }
            }

            stepCounter++;
        }
        return null;
    }

    private int getHeuristic(Node node, boolean H1) {
        return node.getG() + (H1 ? misplacedTiles(node) : sumOfDistance(node));
    }

    /**
     * Calculates the number of misplaced tiles in the current state of the puzzle compared to the goal state. 
     * A tile is misplaced if its value is not equal to its index position in the goal state.
     * @param node The node representing the current puzzle state
     * @return The total number of misplaced tiles in the current state
     */
    private int misplacedTiles(Node node) {
        int numOfMisplacedTiles = 0;

        // Iterate through the current state of the puzzle
        for (int i = 0; i < node.getCurrentArray().length; ++i) {

            // Check if the value of the tile is not equal to its index position
            if (node.getCurrentArray()[i] != i) {

                // If the tile is misplaced, increment the misplaced
                numOfMisplacedTiles++;
            }
        }
        return numOfMisplacedTiles;
    }

    /**
     * Calculates the sum of the Manhattan distances of each tile from its goal position in the current state of the puzzle 
     * The Manhattan distance is the sum of the horizontal & vertical distances between the current tile position and its goal position.
     * @param node The node representing the current puzzle state
     * @return The sum of the Manhattan distances of each tile from its goal position.
     */
    private int sumOfDistance(Node node) {
        int sumOfDistance = 0;

        // Iterate through the current state of the puzzle
        for (int i = 0; i < node.getCurrentArray().length; ++i) {
            int currentArray = node.getCurrentArray()[i];
            // Skip the tile if it is the empty tile (0)
            if (currentArray == 0) {
                continue;
            }

            // Calculate the row and column of the current tile and its goal position
            int row = currentArray / 3;
            int col = currentArray % 3;
            int goalRow = i / 3;
            int goalCol = i % 3;

            // Calculate the Manhattan distance and add it to the total sum
            sumOfDistance += Math.abs(col - goalCol) + Math.abs(row - goalRow);
        }
        return sumOfDistance;
    } 
}

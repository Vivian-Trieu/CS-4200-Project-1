package _8puzzleproblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {
    private PriorityQueue<Node> frontier;
    private final Integer[] goal = {0, 1, 2, 3, 4, 5, 6, 7, 8};
    private HashSet<Node> explored;
    int stepCounter = 0;

    public Node runAStar(Node initialNode, boolean H1) {
        int searchCost = 0;
        explored = new HashSet<>();
        frontier = new PriorityQueue<>((final Node o1, final Node o2) -> getHeuristic(o1, H1) - getHeuristic(o2, H1));
        
        frontier.add(initialNode);
        while (!frontier.isEmpty()) {
            Node current = frontier.poll();
            explored.add(current);

            System.out.println("Step" + stepCounter);
            System.out.println(current);
            System.out.println();
            
            if (Arrays.equals(current.getCurrentArray(), goal)) {
                System.out.println("Search cost: " + searchCost);
            }

            ArrayList<Node> successors = current.expandNode();
            for (Node successor : successors) {
                if (!explored.contains(successor)) {
                    searchCost++;
                    successor.setSearchCost(searchCost);
                    successor.setExploredSize(explored.size());
                    successor.setFrontierSize(frontier.size());
                    frontier.add(successor);
                }
            }

            stepCounter++;
        }
        return null;
    }

    private int getHeuristic(Node node, boolean H1) {
        return node.getG() + (H1 ? misplacedTiles(node) : sumOfDistance(node));
    }

    private int misplacedTiles(Node node) {
        int numOfMisplacedTiles = 0;
        for (int i = 0; i < node.getCurrentArray().length; ++i) {
            if (node.getCurrentArray()[i] != i) {
                numOfMisplacedTiles++;
            }
        }
        return numOfMisplacedTiles;
    }

    private int sumOfDistance(Node node) {
        int sumOfDistance = 0;
        for (int i = 0; i < node.getCurrentArray().length; ++i) {
            int currentArray = node.getCurrentArray()[i];
            if (currentArray == 0) {
                continue;
            }
            int row = currentArray / 3;
            int col = currentArray % 3;
            int goalRow = i / 3;
            int goalCol = i % 3;
            sumOfDistance += Math.abs(col - goalCol) + Math.abs(row - goalRow);
        }
        return sumOfDistance;
    } 
}

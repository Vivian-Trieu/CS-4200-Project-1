package _8puzzleproblem;

import java.util.ArrayList;
import java.util.Arrays;

public class Node {
    private final Integer[] initialNode; // start
    private final Integer[] currentNode;
    private final int g; // cost so far to reach n
    private final String action; // action taken
    private final int emptyNode; // empty node's position
    private Node predecessor;
    private int frontierNodeSize = 0;
    private int exploredNodeSize = 0;
    private int searchCost = 0;

    // Initialize variables
    public Node() {
        initialNode = new Integer[9];
        Arrays.fill(initialNode, -1);
        currentNode = initialNode; // set the current node to initial node
        g = 0;
        action = "no_op";
        emptyNode = 0;
    }

    // Constructor
    public Node(Integer[] init, Integer[] current, int g, String action, Node predecessor, int empty) {
        initialNode = init;
        currentNode = current;
        this.g = g;
        this.action = action;
        this.predecessor = predecessor;
        emptyNode = empty;
    }

    public Node (Node node) {
        this(node.getInitialNode(), node.getCurrentNode(), node.getG(), node.getAction(), node.getPredecessor(), node.getEmptyNode());
    }

    // Getters 
    private int getEmptyNode() {
        return emptyNode;
    }

    private Node getPredecessor() {
        return predecessor;
    }

    private String getAction() {
        return action;
    }

    private int getG() {
        return g;
    }

    private Integer[] getCurrentNode() {
        return currentNode;
    }

    private Integer[] getInitialNode() {
        return initialNode;
    }

    public int getFrontierNodeSize() {
        return frontierNodeSize;
    }

    public int getExploredNodeSize() {
        return exploredNodeSize;
    }

    public int getSearchCost() {
        return searchCost;
    }
    // Setters
    public void setFrontierNodeSize(int f) {
        frontierNodeSize = f;
    }

    public void setExploredNodeSize(int e) {
        exploredNodeSize = e;
    }

    public void setSearchCost(int searchCost) {
        this.searchCost = searchCost;
    }

        
    /**
     * Generates a new Node object representing the new node obtained after performing a specific action on the current node
     * Representation of the puzzle board as a 3x3 matrix and its positions:
     *        col0 col1 col2         
     * row0   0    1    2
     * row1   3    4    5
     * row2   6    7    8
     * Representation of the puzzle board as a 1D array corresponding to the matrix's positions:
     * {0, 1, 2, 3, 4, 5, 6, 7, 8}
     * @param action The action applied to the current node
     * @return Child node object which is the node after the specified action
     */
    public Node createNode(String action) {
        Node successorNode;
        Integer[] newNode = Arrays.copyOf(currentNode, currentNode.length);
        int rowSize = (int) Math.sqrt(currentNode.length);
        int newRow = emptyNode / rowSize;
        int newCol = emptyNode % rowSize;

        switch (action) {
            case "up":
                newRow--;
                break;
            case "down":
                newRow++;
                break;
            case "left":
                newCol--;
                break;
            case "right":
                newCol++;
                break;
            default:
                return null;
        }

        int newPosition = newRow * rowSize + newCol;
        newNode[emptyNode] = newNode[newPosition]; // Swap new child node with empty node
        newNode[newPosition] = 0;

        successorNode = new Node(initialNode, newNode, g+1, action, this, newPosition);
        return successorNode;
    }

    /**
     * Checks if the action (up, down, left, right) will keep the empty position within the bounds of the puzzle board.
     * Calculates the row and column of the empty node's position in the 1D array to determine if the node's movement is out of bounds.
     *       col0 col1 col2         
     * row0   0    1    2
     * row1   3    4    5
     * row2   6    7    8
     * @param action direction the empty node will move
     * @return true if the empty node is in bounds of the matrix after taking the action
     *         false otherwise
     */
    public boolean checkInBounds (String action) {
        int rowSize = (int) Math.sqrt(currentNode.length);
        int row = emptyNode / rowSize;
        int col = emptyNode % rowSize;

        switch (action) {
            case "up":
                return row > 0;
            case "down":
                return row < rowSize - 1;
            case "left":
                return col > 0;
            case "right":
                return col < rowSize - 1;
            default:
                return false;
        }
    }

    /**
     * Expands from the current node by doing all available actions and providing a list of all successors.
     * @return List of all successors
     */
    public ArrayList<Node> expandNode() {
        ArrayList<Node> successors = new ArrayList<>();
        String[] actions = {"up", "down", "left", "right"};
        
        for (String action : actions) {
            // First check if the actions is within the bounds of the puzzle
            if (checkInBounds(action)) {
                // Create node from that action and add it to the list of successors of the current node
                successors.add(createNode(action));
            }
        }

        return successors;
    }
}

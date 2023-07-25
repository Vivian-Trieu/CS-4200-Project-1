package _8puzzleproblem;

import java.util.ArrayList;
import java.util.Arrays;

public class Node {
    private final Integer[] initialArray; // start
    private final Integer[] currentArray;
    private final int g; // cost so far to reach n
    private final String action; // action taken
    private final int emptyPos; // empty node's position
    private Node predecessor;
    private int frontierSize = 0;
    private int exploredSize = 0;
    private int searchCost = 0;

    // Initialize variables
    public Node() {
        initialArray = new Integer[9];
        Arrays.fill(initialArray, -1);
        currentArray = initialArray; // set the current node to initial node
        g = 0;
        action = "no_op";
        emptyPos = 0;
    }

    // Constructor
    public Node(Integer[] init, Integer[] current, int g, String action, Node predecessor, int empty) {
        initialArray = init;
        currentArray = current;
        this.g = g;
        this.action = action;
        this.predecessor = predecessor;
        emptyPos = empty;
    }

    public Node (Node node) {
        this(node.getInitialArray(), node.getCurrentArray(), node.getG(), node.getAction(), node.getPredecessor(), node.getEmptyPos());
    }

    // Getters 
    public int getEmptyPos() {
        return emptyPos;
    }

    public Node getPredecessor() {
        return predecessor;
    }

    public String getAction() {
        return action;
    }

    public int getG() {
        return g;
    }

    public Integer[] getCurrentArray() {
        return currentArray;
    }

    public Integer[] getInitialArray() {
        return initialArray;
    }

    public int getFrontierSize() {
        return frontierSize;
    }

    public int getExploredSize() {
        return exploredSize;
    }

    public int getSearchCost() {
        return searchCost;
    }
    // Setters
    public void setFrontierSize(int f) {
        frontierSize = f;
    }

    public void setExploredSize(int e) {
        exploredSize = e;
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
     * @param action The action applied to the current node
     * @return Child node object which is the node after the specified action
     */
    public Node createNode(String action) {
        Node successorNode;
        Integer[] newArray = currentArray.clone();
        int newEmptyPos = emptyPos;
        // int rowSize = (int) Math.sqrt(currentArray.length);
        // int newRow = emptyNode / rowSize;
        // int newCol = emptyNode % rowSize;

        switch (action) {
            case "up":
                if (emptyPos - 3 >= 0) {
                    newArray = swap(newArray, emptyPos, emptyPos - 3);
                    newEmptyPos = emptyPos - 3;
                }
                break;
            case "down":
                if (emptyPos + 3 <= currentArray.length) {
                    newArray = swap(newArray, emptyPos, emptyPos + 3);
                    newEmptyPos = emptyPos + 3;
                }
                break;
            case "left":
                if (emptyPos == 0) break;
                if (emptyPos % 3 != 0) {
                    newArray = swap(newArray, emptyPos, emptyPos - 1);
                    newEmptyPos = emptyPos - 1;
                }
                
                break;
            case "right":
                if ((emptyPos + 1) % 3 != 0) {
                    newArray = swap(newArray, emptyPos, emptyPos + 1);
                    newEmptyPos = emptyPos + 1;
                }
                break;
            default:
                
        }

        successorNode = new Node(initialArray, newArray, getG() + 1, action, this, newEmptyPos);
        return successorNode;
        // int newPosition = newRow * rowSize + newCol;
        // newNode[emptyNode] = newNode[newPosition]; // Swap new child node with empty node
        // newNode[newPosition] = 0;

        // successorNode = new Node(initialArray, newNode, g+1, action, this, newEmptyPos);

    }

    private Integer[] swap(Integer[] array, int i, int j) {
        Integer temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return array;
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
    public boolean checkBounds (String action) {
       // Integer[] current = currentArray;
        //int empty = emptyPos;
        /* 
        switch (action) {
            case "up":
                if (empty - 3 < 0) 
                    return false;
                break;
            case "down":
                if (empty + 3 >= current.length) 
                    return false;
                break;
            case "left": 
                if (empty == 0)
                    return false;
                if (empty % 3 == 0)
                    return false;
                break;
            case "right":
                if (empty + 1 % 3 == 0) 
                    return false;
                break;
            default:
                return false;
        }
        
        return true;
        */
        int rowSize = (int) Math.sqrt(currentArray.length);
        int row = emptyPos / rowSize;
        int col = emptyPos % rowSize;

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
            if (checkBounds(action)) {
                // Create node from that action and add it to the list of successors of the current node
                successors.add(createNode(action));
            }
        }

        return successors;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        } 
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        return Arrays.deepEquals(this.currentArray, other.currentArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(currentArray);
    }
}

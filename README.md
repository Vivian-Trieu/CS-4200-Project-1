## Project Description

The A* search can be used to solve the 8-puzzle problem. As described in the book, there are two candidate heuristic functions: (1) h1 = the number of misplaced tiles; (2) h2 = the sum of the distances of the tiles from their goal positions.

You are to implement the A* using both heuristics and compare their efficiency in terms of depth of the solution and search costs.  The following figure provides some data points that you can refer to (you don’t need to report “effective branching factor” in your report). To test your program and analyze the efficiency, you should generate random problems (>100 cases) with different solution lengths or you can use the inputs that I provided to test your program. Please collect data on the different solution lengths that you have tested, with their corresponding search cost (# of nodes generate). A good testing program should test a range of possible cases (2 <= d <= 20). Note that the average solution cost for a randomly generated 8-puzzle instance is about 22 steps.

## Input requirements: 
Your program should allow the instructor to either generate a random 8-puzzle problem or enter a specific 8-puzzle configuration through the standard input, which contains the configuration for only one puzzle, in the following format:
`1 2 4
 0 5 6
 8 3 7`

The goal state is:
`0 1 2
 3 4 5
 6 7 8`
Where 0 represents the empty tile.

###How to run the code: Run from `Main.java`.
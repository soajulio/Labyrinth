# Labyrinth Solver Application

## Overview
This university project is a labyrinth-solving application where users can create and solve mazes using various pathfinding algorithms. The application visualizes the algorithm's path exploration and provides statistics on execution time and path length.

## Features
- **Maze Construction**: Users can place walls, a start point, and an end point on a grid to create a custom maze.
- **Pathfinding Algorithms**: The application supports several pathfinding algorithms including:
  - Breadth-First Search
  - Depth-First Search
  - Greedy Best First
  - Dijkstra's Algorithm
  - A*
  - IDA* (optional)
- **Visualization**: The grid updates in real-time, showing the path explored by the algorithm and the final solution.
- **Statistics**: After running an algorithm, the application displays key metrics such as execution time, number of generated states, and the success of the search.

## How to Use
1. **Create a Maze**: Use the "Wall", "Start", "End", and "Empty" buttons to build a maze on the grid.
2. **Select an Algorithm**: Choose a pathfinding algorithm from the dropdown list.
3. **Run the Algorithm**: Press "Start" to run the algorithm, and watch the grid update with the explored path and final solution.
4. **View Statistics**: After completion, view the time taken, number of states generated, and whether the solution was successful.
5. **Reset**: You can switch algorithms and rerun them or create a new maze.
6. **Exit**: Use the "Quit" button to close the application.

## Algorithms
- **Breadth-First Search**: Explores the maze level by level.
- **Depth-First Search**: Recursively explores one path as far as possible before backtracking.
- **Greedy Best First Search**: Chooses the next path based on the lowest estimated cost to the goal.
- **Dijkstra's Algorithm**: Guarantees the shortest path by exploring based on the lowest cost to reach a point.
- **A\***: Combines Dijkstra's guaranteed shortest path with a heuristic to improve efficiency.
- **IDA\*** (optional): An optimization over A* that reduces memory usage by iterative deepening.

## Heuristic
- **A\*** and **IDA\*** use the Manhattan distance as the heuristic.

## Development
The project follows a typical software development process:
1. **Requirements Analysis**
2. **Design** (based on the MVC architecture)
3. **Implementation**
4. **Testing**

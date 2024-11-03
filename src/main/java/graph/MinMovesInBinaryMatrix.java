package graph;

import java.util.*;

public class MinMovesInBinaryMatrix {
    static class Position {
        int x, y, moves;

        Position(int x, int y, int moves) {
            this.x = x;
            this.y = y;
            this.moves = moves;
        }
    }

    public static int minMoves(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        // If the start or end is blocked
        if (grid[0][0] == 1 || grid[m - 1][n - 1] == 1) {
            return -1;
        }

        // Directions for moving in 4 possible directions
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Queue<Position> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        queue.offer(new Position(0, 0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            // Check if we reached the bottom-right corner
            if (current.x == m - 1 && current.y == n - 1) {
                return current.moves;
            }

            // Explore all 4 directions
            for (int[] direction : directions) {
                int dx = direction[0];
                int dy = direction[1];

                for (int step = 1; step <= k; step++) {
                    int newX = current.x + dx * step;
                    int newY = current.y + dy * step;

                    // Check boundaries and if the cell is valid (0) and not visited
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n &&
                            grid[newX][newY] == 0 && !visited[newX][newY]) {

                        visited[newX][newY] = true;
                        queue.offer(new Position(newX, newY, current.moves + 1));
                    } else {
                        // If we encounter an obstacle, we stop exploring in this direction
                        break;
                    }
                }
            }
        }

        // If we never reach the bottom-right corner, return -1
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0},
                {0, 1, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 0}
        };
        int k = 3;

        int result = minMoves(grid, k);
        System.out.println("Minimum moves to reach the end: " + result);
    }
}

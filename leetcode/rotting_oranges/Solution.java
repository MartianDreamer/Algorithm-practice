package leetcode.rotting_oranges;

import java.util.LinkedList;
import java.util.List;

class Solution {
    public int orangesRotting(int[][] grid) {
        List<Integer> rottingOrange = findRottingOrange(grid);
        int gridHeight = grid.length;
        int gridWidth = grid[0].length;
        if (rottingOrange.get(0) < 0 && rottingOrange.size() == 1) {
            return -1;
        } else if (rottingOrange.get(0) == gridHeight * gridWidth && rottingOrange.size() == 1) {
            return 0;
        }
        int[] level = new int[gridHeight * gridWidth];
        int[] parent = new int[gridHeight * gridWidth];
        int vLevel = 0;
        for (int i = 0; i < gridHeight * gridWidth; i++) {
            level[i] = -1;
            parent[i] = -1;
        }
        for (Integer number : rottingOrange) {
            level[number] = 0;
        }
        List<Integer> frontier = rottingOrange;
        int time = 0;
        while (!frontier.isEmpty()) {
            List<Integer> next = new LinkedList<>();
            vLevel++;
            for (Integer number : frontier) {
                int[] children;
                if (number % gridWidth == gridWidth - 1) {
                    int[] temp = { number - 1, number - gridWidth, number + gridWidth };
                    children = temp;
                } else if (number % gridWidth == 0) {
                    int[] temp = { number + 1, number - gridWidth, number + gridWidth };
                    children = temp;
                } else {
                    int[] temp = { number + 1, number - 1, number - gridWidth, number + gridWidth };
                    children = temp;
                }
                for (int child : children) {
                    if (child < gridHeight * gridWidth && child >= 0 && grid[child / gridWidth][child % gridWidth] == 1
                            && level[child] == -1) {
                        grid[child / gridWidth][child % gridWidth] = 2;
                        next.add(child);
                        level[child] = vLevel;
                        parent[child] = number;
                        time = vLevel > time ? vLevel : time;
                    }
                }
            }
            frontier = next;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    time = -1;
                    break;
                }
            }
        }
        return time;
    }

    public List<Integer> findRottingOrange(int[][] grid) {
        int sum = 0;
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                sum += grid[i][j];
                if (grid[i][j] == 2) {
                    result.add(i * grid[0].length + j);
                }
            }
        }
        if (result.isEmpty()) {
            result.add(-1);
        }
        if (sum == 0) {
            result.set(0, grid.length * grid[0].length);
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][] { { 2, 1, 1 }, { 1, 1, 1 }, { 0, 1, 2 } };
        Solution solution = new Solution();
        int time = solution.orangesRotting(grid);
        System.out.println(time);
    }
}
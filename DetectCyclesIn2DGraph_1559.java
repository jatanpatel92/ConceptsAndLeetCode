/*
1559. Detect Cycles in 2D Grid
Medium

Given a 2D array of characters grid of size m x n, you need to find if there exists any cycle consisting of the same value in grid.

A cycle is a path of length 4 or more in the grid that starts and ends at the same cell. From a given cell, you can move to one of the cells adjacent to it - in one of the four directions (up, down, left, or right), if it has the same value of the current cell.

Also, you cannot move to the cell that you visited in your last move. For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.

Return true if any cycle of the same value exists in grid, otherwise, return false.



Example 1:

Input: grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
Output: true
Explanation: There are two valid cycles shown in different colors in the image below:

Example 2:

Input: grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
Output: true
Explanation: There is only one valid cycle highlighted in the image below:

Example 3:

Input: grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
Output: false



Constraints:

    m == grid.length
    n == grid[i].length
    1 <= m, n <= 500
    grid consists only of lowercase English letters.

    [["e","g","a","e","h","f","d","h","e","f"],
     ["d","b","d","b","e","e","h","c","c","d"],
     ["h","c","d","e","g","c","a","e","a","b"],
     ["h","b","h","c","g","g","c","a","a","e"],
     ["h","c","d","b","c","b","h","b","d","h"],
     ["c","g","a","f","d","a","b","g","c","h"],
     ["h","c","f","f","h","g","h","e","g","h"],
     ["e","e","g","h","d","f","e","a","c","e"],
     ["a","a","g","g","b","h","c","h","b","d"],
     ["f","g","a","a","e","g","b","e","b","f"],
     ["a","b","e","g","h","b","f","a","b","d"],
     ["a","h","d","d","c","e","h","d","b","c"],
     ["a","g","d","d","h","d","c","b","b","h"],
     ["g","e","a","b","f","g","a","e","a","h"],
     ["g","a","b","e","f","f","a","e","g","e"],
     ["a","b","g","c","g","c","g","g","c","g"],
     ["e","e","g","g","a","a","h","a","c","h"],
     ["c","c","d","h","b","e","f","g","e","a"],
     ["f","h","f","c","g","g","g","f","g","h"]]

*/
class Solution {
    public boolean containsCycle(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return false;
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        boolean[][] recursiveStack = new boolean[m][n];
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                Pair<Integer, Integer> source = new Pair<>(i, j);
                if(detectCycleDFS(grid, source, visited, recursiveStack, null))
                    return true;
            }
        }
        return false;
    }
    private boolean detectCycleDFS(char[][] grid, Pair<Integer, Integer> source, boolean[][] visited, boolean[][] recursiveStack, Pair<Integer, Integer> parent){
        if(!visited[source.getKey()][source.getValue()]){
            visited[source.getKey()][source.getValue()] = true;
            recursiveStack[source.getKey()][source.getValue()] = true;
            for(Pair<Integer, Integer> neighbour : getValidNeighbours(grid, source, parent)){
                if(detectCycleDFS(grid, neighbour, visited, recursiveStack, source))
                    return true;
            }
            recursiveStack[source.getKey()][source.getValue()] = false;
            return false;
        }
        else if(recursiveStack[source.getKey()][source.getValue()]){
            return true;
        }
        return false;
    }
    private List<Pair<Integer, Integer>> getValidNeighbours(char[][] grid, Pair<Integer, Integer> source, Pair<Integer, Integer> parent){
        int m = grid.length;
        int n = grid[0].length;
        List<Pair<Integer, Integer>> list = new LinkedList<>();
        int i = source.getKey();
        int j = source.getValue();
        if(j+1>=0 && j+1<n && grid[i][j] == grid[i][j+1] && (parent == null || parent.getValue()!=j+1)){
            list.add(new Pair<Integer, Integer>(i, j+1));
        }
        if(i+1>=0 && i+1<m && grid[i][j] == grid[i+1][j] && (parent == null || parent.getKey()!=i+1)){
            list.add(new Pair<Integer, Integer>(i+1, j));
        }
        if(j-1>=0 && j-1<n && grid[i][j] == grid[i][j-1] && (parent == null || parent.getValue()!=j-1)){
            list.add(new Pair<Integer, Integer>(i, j-1));
        }
        if(i-1>=0 && i-1<m && grid[i][j] == grid[i-1][j] && (parent == null || parent.getKey()!=i-1)){
            list.add(new Pair<Integer, Integer>(i-1, j));
        }
        return list;
    }
}

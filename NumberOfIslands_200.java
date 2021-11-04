/*
200. Number of Islands
Medium

10498

274

Add to List

Share
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.



Example 1:

Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1
Example 2:

Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 300
grid[i][j] is '0' or '1'.
Accepted
1,238,453
Submissions
2,393,933
*/

class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int numOfIslands = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == '1' && !visited[i][j]){
                    numOfIslands++;
                    bfsFindIslands(grid, i, j, visited);
                }
            }
        }
        return numOfIslands;
    }
    private void bfsFindIslands(char[][] grid, int i, int j, boolean[][] visited){
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        Pair<Integer, Integer> source = new Pair<>(i, j);
        queue.add(source);
        visited[i][j] = true;
        while(!queue.isEmpty()){
            Pair<Integer, Integer> current = queue.poll();
            List<Pair<Integer, Integer>> neighbours = getValidNeighbours(grid, current);
            for(Pair<Integer, Integer> neighbour : neighbours){
                if(!visited[neighbour.getKey()][neighbour.getValue()]){
                    visited[neighbour.getKey()][neighbour.getValue()] = true;
                    queue.add(neighbour);
                }
            }
        }
    }
    private List<Pair<Integer, Integer>> getValidNeighbours(char[][] grid, Pair<Integer, Integer> source){
        int m = grid.length;
        int n = grid[0].length;
        int i = source.getKey();
        int j = source.getValue();
        List<Pair<Integer, Integer>> neighbours = new LinkedList<>();
        if(j+1<n && j+1>=0 && grid[i][j+1] == '1'){
            neighbours.add(new Pair<>(i, j+1));
        }
        if(j-1<n && j-1>=0 && grid[i][j-1] == '1'){
            neighbours.add(new Pair<>(i, j-1));
        }
        if(i+1<m && i+1>=0 && grid[i+1][j] == '1'){
            neighbours.add(new Pair<>(i+1, j));
        }
        if(i-1<m && i-1>=0 && grid[i-1][j] == '1'){
            neighbours.add(new Pair<>(i-1, j));
        }
        return neighbours;
    }
}

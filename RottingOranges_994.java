/*
994. Rotting Oranges
Medium

4555

236

Add to List

Share
You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.



Example 1:


Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.


Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.
*/


class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int minTime = Integer.MAX_VALUE;
        boolean allZeroes = true;
        List<Pair<Integer, Integer>> sources = new ArrayList<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j]==2){
                    sources.add(new Pair<>(i ,j));
                    allZeroes = false;
                }
                else if(grid[i][j]==1){
                    allZeroes = false;
                }
            }
        }
        if(!sources.isEmpty())
            minTime = Math.min(minTime, getRottingTime(grid, m, n, sources));
        return minTime == Integer.MAX_VALUE?(allZeroes?0:-1):minTime;
    }
    private int getRottingTime(int[][] rottingGrid, int m, int n, List<Pair<Integer, Integer>> sources){
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        Pair<Integer, Integer>[][] parent = new Pair[m][n];
        for(Pair<Integer, Integer> source : sources){
            queue.add(source);
            parent[source.getKey()][source.getValue()] = new Pair<Integer, Integer>(-1, -1);
            visited[source.getKey()][source.getValue()] = true;
        }
        Pair<Integer, Integer> current = null;
        while(!queue.isEmpty()){
            current = queue.poll();
            List<Pair<Integer, Integer>> neighbours = getValidNeighbours(rottingGrid, m, n,
                                                                         current.getKey(), current.getValue());
            for(Pair<Integer, Integer> neighbour : neighbours){
                if(!visited[neighbour.getKey()][neighbour.getValue()]){
                    if(rottingGrid[neighbour.getKey()][neighbour.getValue()] != 0){
                        queue.add(neighbour);
                        rottingGrid[neighbour.getKey()][neighbour.getValue()] = 2;
                        parent[neighbour.getKey()][neighbour.getValue()] = current;
                        visited[neighbour.getKey()][neighbour.getValue()] = true;
                    }
                }
            }
        }
        for(int p=0; p<m; p++){
            for(int q=0; q<n; q++){
                if(rottingGrid[p][q] == 1){
                    return -1;
                }
            }
        }
        int distance = 0;
        while(current != null && parent[current.getKey()][current.getValue()]!=null && parent[current.getKey()][current.getValue()].getKey()!=-1
              && parent[current.getKey()][current.getValue()].getValue()!=-1){
            distance++;
            current = parent[current.getKey()][current.getValue()];
        }
        return distance;
    }
    private List<Pair<Integer, Integer>> getValidNeighbours(int[][] rottingGrid, int m, int n, int i, int j){
        List<Pair<Integer, Integer>> neighbours = new ArrayList<>();
        if(j+1<n && j+1>=0 && rottingGrid[i][j+1] == 1){
            neighbours.add(new Pair<Integer, Integer>(i, j+1));
        }
        if(i+1<m && i+1>=0 && rottingGrid[i+1][j] == 1){
            neighbours.add(new Pair<Integer, Integer>(i+1, j));
        }
        if(i-1<m && i-1>=0 && rottingGrid[i-1][j] == 1){
            neighbours.add(new Pair<Integer, Integer>(i-1, j));
        }
        if(j-1<n && j-1>=0 && rottingGrid[i][j-1] == 1){
            neighbours.add(new Pair<Integer, Integer>(i, j-1));
        }
        return neighbours;
    }
}

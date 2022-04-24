/*
1091. Shortest Path in Binary Matrix
Medium

Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

    All the visited cells of the path are 0.
    All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).

The length of a clear path is the number of visited cells of this path.



Example 1:

Input: grid = [[0,1],[1,0]]
Output: 2

Example 2:

Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4

Example 3:

Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1



Constraints:

    n == grid.length
    n == grid[i].length
    1 <= n <= 100
    grid[i][j] is 0 or 1
*/

class Solution {
    static int[][] grid;
    static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};
    public int shortestPathBinaryMatrix(int[][] grid) {
        this.grid = grid;
        int m = grid.length;
        int n = grid[0].length;
        Pair source = new Pair(0, 0);
        Pair destination = new Pair(m-1, n-1);
        if(grid[0][0] != 0 || grid[m-1][n-1] != 0){
            return -1;
        }
        boolean pathFound = false;
        Queue<Pair> queue = new LinkedList<>();
        Map<Pair, Pair> parent = new HashMap<>();
        queue.offer(source);
        // Mark visited
        grid[source.i][source.j] = 1;
        parent.put(source, null);
        Pair current = source;
        while(!queue.isEmpty()){
            current = queue.poll();
            if(current.i == destination.i && current.j == destination.j){
                pathFound = true;
                break;
            }
            for(Pair neighbor:getValidNeighbors(current)){
                queue.add(neighbor);
                // Mark visited
                grid[neighbor.i][neighbor.j] = 1;
                parent.put(neighbor, current);
            }
        }
        if(!pathFound)
            return -1;
        int distance = 0;
        while(current!=null){
            current = parent.get(current);
            distance++;
        }
        return distance;
    }
    public List<Pair> getValidNeighbors(Pair source){
        List<Pair> neighbors = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        for(int[] dir: directions){
            int i = source.i+dir[0];
            int j = source.j+dir[1];
            if(i>=0 && i<m && j>=0 && j<n && grid[i][j] == 0)
                neighbors.add(new Pair(i, j));
        }
        return neighbors;

    }
    class Pair{
        int i;
        int j;
        public Pair(int i1, int j1){
            i = i1;
            j = j1;
        }
        @Override
        public String toString(){
            return "("+i+", "+j+")";
        }
    }
}

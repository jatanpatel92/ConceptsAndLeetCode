class Solution {
    public boolean containsCycle(char[][] grid) {
        //if(grid == null || grid.length==0 || grid[0].length == 0) return false;
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] visited = new boolean[row][col];
        Pair<Integer, Integer> parent = new Pair<>(-1, -1);
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                Pair<Integer, Integer> source = new Pair<>(i, j);
                if(detectCycleDFS(grid, visited, source, parent))
                    return true;
            }
        }
        return false;
    }

    private boolean detectCycleDFS(char[][] grid, boolean[][] visited, Pair<Integer, Integer> source, Pair<Integer, Integer> parent){
        if(visited[source.getKey()][source.getValue()])
                return true;
        visited[source.getKey()][source.getValue()] = true;
        for(Pair<Integer, Integer> neighbor:getValidNeighbors(grid, source, parent)){
            if(detectCycleDFS(grid, visited, neighbor, source)){
                return true;
            }
        }
        visited[source.getKey()][source.getValue()] = false;
        return false;
    }

    private List<Pair<Integer, Integer>> getValidNeighbors(char[][] grid, Pair<Integer, Integer> source, Pair<Integer, Integer> parent){
        int row = grid.length;
        int col = grid[0].length;
        int i = source.getKey();
        int j = source.getValue();
        int pi = parent.getKey();
        int pj = parent.getValue();
        List<Pair<Integer, Integer>> neighbors = new LinkedList<>();
        int[][] directions = new int[][] {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        for(int d=0; d<4; d++){
            int r = directions[d][0];
            int c = directions[d][1];
            if(isOnGrid(i+r, j+c, row, col) && !(i+r==pi && j+c==pj) &&  grid[i][j] == grid[i+r][j+c] ){
                neighbors.add(new Pair<Integer, Integer>(i+r, j+c));
            }
        }
        return neighbors;
    }

    private boolean isOnGrid(int x, int y, int row, int col){
        return x>=0 && y>=0 && x<row && y<col;
    }
}

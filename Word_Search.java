class Solution {
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(board[i][j] == word.charAt(0)){
                    Pair source = new Pair(i, j);
                    if(searchWord(board, word, source)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean searchWord(char[][] board, String word, Pair source){
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        return searchWordDFS(board, word, source, 1, visited);
    }
    public boolean searchWordDFS(char[][] board, String word, Pair source, int index, boolean[][] visited){
        //System.out.println(source +" "+ index + " "+ visited);
        if(index>=word.length()) return true;
        visited[source.key][source.value] = true;
        List<Pair> neighbors = getValidNeighbors(board, source);
        for(Pair neighbor:neighbors){
            if(!visited[neighbor.key][neighbor.value] && board[neighbor.key][neighbor.value] == word.charAt(index)){
                if(searchWordDFS(board, word, neighbor, index+1, visited)){
                    return true;
                }
            }
        }
        visited[source.key][source.value] = false;
        return false;
    }
    public List<Pair> getValidNeighbors(char[][] board, Pair source){
        int[][] directions = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        List<Pair> neighbors = new LinkedList<>();
        for(int[] dir: directions){
            int newKey = source.key + dir[0];
            int newValue = source.value + dir[1];
            if(isOnBoard(board, newKey, newValue)){
                neighbors.add(new Pair(newKey, newValue));
            }
        }
        return neighbors;
    }
    public boolean isOnBoard(char[][] board, int i, int j){
        int m = board.length;
        int n = board[0].length;
        return i>=0 && i<m && j>=0 && j<n;
    }
    class Pair{
        int key;
        int value;
        int hash;
        public Pair(int x1, int y1){
            key = x1;
            value = y1;
            hash = Objects.hash(key, value);
        }
        @Override
        public String toString(){
            return "("+key+","+value+")";
        }
        /*
        @Override
        public int hashCode(){
            return hash;
        }
        @Override
        public boolean equals(Object p){
            if(this == p) return true;
            if(p == null || p.getClass()!=this.getClass()) return false;
            Pair pair = (Pair) p;
            return key == pair.key && value == pair.value;
        }
        */
    }
}

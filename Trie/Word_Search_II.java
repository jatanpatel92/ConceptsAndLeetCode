/*
212. Word Search II
Hard

Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.



Example 1:

Input: board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]], words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]

Example 2:

Input: board = [["a","b"],["c","d"]], words = ["abcb"]
Output: []



Constraints:

    m == board.length
    n == board[i].length
    1 <= m, n <= 12
    board[i][j] is a lowercase English letter.
    1 <= words.length <= 3 * 104
    1 <= words[i].length <= 10
    words[i] consists of lowercase English letters.
    All the strings of words are unique.


*/
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        for(String word: words){
            trie.insert(word);
        }
        Set<String> result = new HashSet<>();
        int m = board.length;
        int n = board[0].length;
        for(int i = 0; i<m ; i++){
            for(int j = 0; j<n; j++){
                if(trie.root.children.containsKey(board[i][j])){
                    Pair source = new Pair(i, j);
                    backtrack(board, source, trie.root, result, new StringBuffer());
                }
            }
        }
        return new LinkedList<>(result);
    }
    private void backtrack(char[][] board, Pair source, TrieNode parent, Set<String> result, StringBuffer word){
        char key = board[source.key][source.value];
        TrieNode node = parent.children.get(key);
        word.append(key);
        board[source.key][source.value] = '#';
        if(node.end){
            result.add(word.toString());
        }
        for(Pair neighbor: validNeighbors(board, source)){
            if(node.children.containsKey(board[neighbor.key][neighbor.value])){
                backtrack(board, neighbor, node, result, word);
            }
        }
        board[source.key][source.value] = word.charAt(word.length()-1);
        word.deleteCharAt(word.length()-1);
        if(node.children.isEmpty())
            parent.children.remove(key);
    }
    private List<Pair> validNeighbors(char[][] board, Pair source){
        int m = board.length;
        int n = board[0].length;
        List<Pair> neighbors = new LinkedList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for(int[] dir:directions){
            int key = source.key+dir[0];
            int value = source.value+dir[1];
            if(isOnBoard(m, n, key, value))
                neighbors.add(new Pair(key, value));
        }
        return neighbors;
    }
    private boolean isOnBoard(int m, int n, int i, int j){
        return i>=0 && i<m && j>=0 && j<n;
    }
    class Pair{
        int key;
        int value;
        public Pair(int i, int j){
            key = i;
            value = j;
        }
        @Override
        public String toString(){
            return "("+key + ", " + value+")";
        }
    }
    class Trie{
        TrieNode root;
        public Trie(){
            root = new TrieNode();
        }
        public void insert(String word){
            TrieNode current = root;
            for(char key:word.toCharArray()){
                if(!current.children.containsKey(key)){
                    current.children.put(key, new TrieNode());
                }
                current = current.children.get(key);
            }
            current.end = true;
            //current.word = word;
        }
        @Override
        public String toString(){
            return root.toString();
        }
    }
    class TrieNode{
        Map<Character, TrieNode> children;
        boolean end;
       // String word;
        public TrieNode(){
            children = new HashMap<>();
            end = false;
           // word = null;
        }
        @Override
        public String toString(){
            return "isEnd : "+end + " links: "+children;
        }
    }
}
/*class TrieNode {
  HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
  String word = null;
  public TrieNode() {}
}

class Solution {
  char[][] _board = null;
  ArrayList<String> _result = new ArrayList<String>();

  public List<String> findWords(char[][] board, String[] words) {

    // Step 1). Construct the Trie
    TrieNode root = new TrieNode();
    for (String word : words) {
      TrieNode node = root;

      for (Character letter : word.toCharArray()) {
        if (node.children.containsKey(letter)) {
          node = node.children.get(letter);
        } else {
          TrieNode newNode = new TrieNode();
          node.children.put(letter, newNode);
          node = newNode;
        }
      }
      node.word = word;  // store words in Trie
    }

    this._board = board;
    // Step 2). Backtracking starting for each cell in the board
    for (int row = 0; row < board.length; ++row) {
      for (int col = 0; col < board[row].length; ++col) {
        if (root.children.containsKey(board[row][col])) {
          backtracking(row, col, root);
        }
      }
    }

    return this._result;
  }

  private void backtracking(int row, int col, TrieNode parent) {
    Character letter = this._board[row][col];
    TrieNode currNode = parent.children.get(letter);

    // check if there is any match
    if (currNode.word != null) {
      this._result.add(currNode.word);
      currNode.word = null;
    }

    // mark the current letter before the EXPLORATION
    this._board[row][col] = '#';

    // explore neighbor cells in around-clock directions: up, right, down, left
    int[] rowOffset = {-1, 0, 1, 0};
    int[] colOffset = {0, 1, 0, -1};
    for (int i = 0; i < 4; ++i) {
      int newRow = row + rowOffset[i];
      int newCol = col + colOffset[i];
      if (newRow < 0 || newRow >= this._board.length || newCol < 0
          || newCol >= this._board[0].length) {
        continue;
      }
      if (currNode.children.containsKey(this._board[newRow][newCol])) {
        backtracking(newRow, newCol, currNode);
      }
    }

    // End of EXPLORATION, restore the original letter in the board.
    this._board[row][col] = letter;

    // Optimization: incrementally remove the leaf nodes
    if (currNode.children.isEmpty()) {
      parent.children.remove(letter);
    }
  }
}
*/

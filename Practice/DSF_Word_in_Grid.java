/*
After catching your classroom students cheating before, you realize your students are getting craftier and hiding words in 2D grids of letters. The word may start anywhere in the grid, and consecutive letters can be either immediately below or immediately to the right of the previous letter.

Given a grid and a word, write a function that returns the location of the word in the grid as a list of coordinates. If there are multiple matches, return any one.

grid1 = [
	['c', 'c', 'x', 't', 'i', 'b'],
	['c', 'c', 'a', 't', 'n', 'i'],
	['a', 'c', 'n', 'n', 't', 't'],
	['t', 'c', 's', 'i', 'p', 't'],
	['a', 'o', 'o', 'o', 'a', 'a'],
	['o', 'a', 'a', 'a', 'o', 'o'],
	['k', 'a', 'i', 'c', 'k', 'i'],
]
word1 = "catnip"
word2 = "cccc"
word3 = "s"
word4 = "bit"
word5 = "aoi"
word6 = "ki"
word7 = "aaa"
word8 = "ooo"

grid2 = [['a']]
word9 = "a"

find_word_location(grid1, word1) => [ (1, 1), (1, 2), (1, 3), (2, 3), (3, 3), (3, 4) ]
find_word_location(grid1, word2) =>
       [(0, 1), (1, 1), (2, 1), (3, 1)]
    OR [(0, 0), (1, 0), (1, 1), (2, 1)]
    OR [(0, 0), (0, 1), (1, 1), (2, 1)]
    OR [(1, 0), (1, 1), (2, 1), (3, 1)]
find_word_location(grid1, word3) => [(3, 2)]
find_word_location(grid1, word4) => [(0, 5), (1, 5), (2, 5)]
find_word_location(grid1, word5) => [(4, 5), (5, 5), (6, 5)]
find_word_location(grid1, word6) => [(6, 4), (6, 5)]
find_word_location(grid1, word7) => [(5, 1), (5, 2), (5, 3)]
find_word_location(grid1, word8) => [(4, 1), (4, 2), (4, 3)]
find_word_location(grid2, word9) => [(0, 0)]

r = number of rows
c = number of columns
w = length of the word


*/

/*
After catching your classroom students cheating before, you realize your students are getting craftier and hiding words in 2D grids of letters. The word may start anywhere in the grid, and consecutive letters can be either immediately below or immediately to the right of the previous letter.

Given a grid and a word, write a function that returns the location of the word in the grid as a list of coordinates. If there are multiple matches, return any one.

grid1 = [
	['c', 'c', 'x', 't', 'i', 'b'],
	['c', 'c', 'a', 't', 'n', 'i'],
	['a', 'c', 'n', 'n', 't', 't'],
	['t', 'c', 's', 'i', 'p', 't'],
	['a', 'o', 'o', 'o', 'a', 'a'],
	['o', 'a', 'a', 'a', 'o', 'o'],
	['k', 'a', 'i', 'c', 'k', 'i'],
]
word1 = "catnip"
word2 = "cccc"
word3 = "s"
word4 = "bit"
word5 = "aoi"
word6 = "ki"
word7 = "aaa"
word8 = "ooo"

grid2 = [['a']]
word9 = "a"

find_word_location(grid1, word1) => [ (1, 1), (1, 2), (1, 3), (2, 3), (3, 3), (3, 4) ]
find_word_location(grid1, word2) =>
       [(0, 1), (1, 1), (2, 1), (3, 1)]
    OR [(0, 0), (1, 0), (1, 1), (2, 1)]
    OR [(0, 0), (0, 1), (1, 1), (2, 1)]
    OR [(1, 0), (1, 1), (2, 1), (3, 1)]
find_word_location(grid1, word3) => [(3, 2)]
find_word_location(grid1, word4) => [(0, 5), (1, 5), (2, 5)]
find_word_location(grid1, word5) => [(4, 5), (5, 5), (6, 5)]
find_word_location(grid1, word6) => [(6, 4), (6, 5)]
find_word_location(grid1, word7) => [(5, 1), (5, 2), (5, 3)]
find_word_location(grid1, word8) => [(4, 1), (4, 2), (4, 3)]
find_word_location(grid2, word9) => [(0, 0)]

r = number of rows
c = number of columns
w = length of the word


*/

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class WordInGrid {
  public static void main(String[] argv) {
    char[][] grid1 = {
      {'c', 'c', 'x', 't', 'i', 'b'},
      {'c', 'c', 'a', 't', 'n', 'i'},
      {'a', 'c', 'n', 'n', 't', 't'},
      {'t', 'c', 's', 'i', 'p', 't'},
      {'a', 'o', 'o', 'o', 'a', 'a'},
      {'o', 'a', 'a', 'a', 'o', 'o'},
      {'k', 'a', 'i', 'c', 'k', 'i'}
    };
    String word1 = "catnip";
    String word2 = "cccc";
    String word3 = "s";
    String word4 = "bit";
    String word5 = "aoi";
    String word6 = "ki";
    String word7 = "aaa";
    String word8 = "ooo";

    char[][] grid2 = {{'a'}};
    String word9 = "a";

    List<Pair> result1 = getListFromStack(getWord(grid1, word1));
    System.out.println(result1);
    List<Pair> result2 = getListFromStack(getWord(grid1, word2));
    System.out.println(result2);
    List<Pair> result3 = getListFromStack(getWord(grid1, word3));
    System.out.println(result3);
    List<Pair> result4 = getListFromStack(getWord(grid1, word4));
    System.out.println(result4);
    List<Pair> result5 = getListFromStack(getWord(grid1, word5));
    System.out.println(result5);
    List<Pair> result6 = getListFromStack(getWord(grid1, word6));
    System.out.println(result6);
    List<Pair> result7 = getListFromStack(getWord(grid1, word7));
    System.out.println(result7);
    List<Pair> result8 = getListFromStack(getWord(grid1, word8));
    System.out.println(result8);

    List<Pair> result9 = getListFromStack(getWord(grid2, word9));
    System.out.println(result9);
  }
  private static List<Pair> getListFromStack(Stack<Pair> stack) {
	List<Pair> list = new LinkedList<>();
	while(!stack.isEmpty()) list.add(stack.pop());
	return list;
}
public static class Pair{
    int key;
    int value;
    public Pair(int x, int y){
      key = x;
      value = y;
    }
    @Override
    public String toString(){
        return "("+key+","+value+")";
    }
  }
  private static Stack<Pair> getWord(char[][] grid, String word){
      int row = grid.length;
      int col = grid[0].length;

      for(int i=0; i<row; i++){
        for(int j=0; j<col; j++){
          if(grid[i][j] == word.charAt(0)){
              System.out.println("Debug:"+word.charAt(0)+"->"+i+","+j);
              Pair source = new Pair(i, j);
              Stack<Pair> stack = new Stack<Pair>();
              boolean[][] visited = new boolean[row][col];
              if(getWordDFS(grid, word, source, stack, 1, visited)){
            	  stack.push(source);
            	  System.out.println("Debug stack: "+stack);
            	  return stack;
              }
          }
        }
      }
      return new Stack<Pair>();
  }
  private static boolean getWordDFS(char[][] grid, String word, Pair source, Stack<Pair> stack, int index, boolean[][] visited){
      if(index>=word.length()) {
    	  return true;
      }
      visited[source.key][source.value] = true;
      List<Pair> neighbors = getValidNeighbors(grid, source, word.charAt(index));
      for(Pair neighbor:neighbors){
        if(!visited[neighbor.key][neighbor.value]){
            if(getWordDFS(grid, word, neighbor, stack, index+1, visited)) {
            	stack.push(neighbor);
            	return true;
            }
        }
      }
      return false;
  }
  private static List<Pair> getValidNeighbors(char[][] grid, Pair source, char c){
    int row = grid.length;
    int col = grid[0].length;
    int[][] dir = new int[][]{{0, 1}, {1, 0}};
    List<Pair> neighbors = new LinkedList<>();
    int i = source.key;
    int j = source.value;
    for(int d=0; d<dir.length; d++){
        if(isOnGrid(i+dir[d][0], j+dir[d][1], row, col) && c == grid[i+dir[d][0]][j+dir[d][1]]){
          neighbors.add(new Pair(i+dir[d][0], j+dir[d][1]));
        }
    }
    return neighbors;
  }
  private static boolean isOnGrid(int x, int y, int row, int col){
    return x>=0 && x<row && y>=0 && y<col;
  }
}

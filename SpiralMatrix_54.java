/*
54. Spiral Matrix
Medium

Given an m x n matrix, return all elements of the matrix in spiral order.



Example 1:

Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]

Example 2:

Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]



Constraints:

    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 10
    -100 <= matrix[i][j] <= 100


*/
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        if(matrix == null || matrix.length==0) return new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        // Set Global rows and cols
        int row = matrix.length;
        int col = matrix[0].length;
        // Set current row and col
        int r = 0, c = 0;
        // visit each element of the matrix in spiral pattern
        while(r<row && c<col){
            for(int j=c; j<col; j++){
                list.add(matrix[r][j]);
            }
            r++; // current row is visited
            for(int i=r; i<row; i++){
                list.add(matrix[i][col-1]);
            }
            col--; // last col is visited
            if(r<row){
                for(int j = col-1; j>=c; j--){
                    list.add(matrix[row-1][j]);
                }
                row--; // last row is visited
            }
            if(c<col){
                for(int i=row-1;i>=r;i--){
                    list.add(matrix[i][c]);
                }
                c++; // current col is visited
            }
        }
        return list;
    }
}

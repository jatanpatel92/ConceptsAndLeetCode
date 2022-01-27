/*
Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.



Example 1:

Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]

Example 2:

Input: n = 1
Output: [[1]]



Constraints:

    1 <= n <= 20


*/
class Solution {
    public int[][] generateMatrix(int n) {
        if(n<=0) return null;
        int row = n, col =n; // Global rows and cols
        int r=0, c=0; // current row and col
        int num = 1;
        int[][] matrix = new int[row][col];
        // generate spiral matrix by visiting nxn elements
        while(r<row && c<col){
            // traverse current row
            for(int j=c; j<col; j++){
                matrix[r][j] = num;
                num++;
            }
            r++;
            // traverse last col
            for(int i=r; i<row; i++){
                matrix[i][col-1] = num;
                num++;
            }
            col--;
            // traverse last row
            if(r<row){
                for(int j=col-1;j>=c;j--){
                    matrix[row-1][j] = num;
                    num++;
                }
                row--;
            }
            // traverse current col
            if(c<col){
                for(int i=row-1;i>=r;i--){
                    matrix[i][c] = num;
                    num++;
                }
                c++;
            }
        }
        return matrix;
    }
}

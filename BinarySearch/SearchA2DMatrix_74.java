/*
74. Search a 2D Matrix
Medium

Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:

    Integers in each row are sorted from left to right.
    The first integer of each row is greater than the last integer of the previous row.



Example 1:

Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true

Example 2:

Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false



Constraints:

    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 100
    -104 <= matrix[i][j], target <= 104


*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length==0 || matrix[0].length==0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        int start = 0;
        int end = row*col-1;
        return binarySearch(matrix, start, end, target, row, col);
    }
    public boolean binarySearch(int[][] a, int start, int end, int target, int row, int col){
        int left = start;
        int right = end;
        if(left>=0 && right<=row*col-1 && left<=right){
            int mid = (right+left)/2;
            int pivotElement = a[mid/col][mid%col];
            if(pivotElement==target){
                return true;
            }
            else if(pivotElement<target){
                return binarySearch(a, mid+1, right, target, row, col);
            }
            else{
                return binarySearch(a, left, mid-1, target, row, col);
            }
        }
        return false;
    }
}
// mlog(n) complexity
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length==0 || matrix[0].length==0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        for(int r=0; r<row; r++){
            if(binarySearch(matrix[r], 0, col-1, target)){
                return true;
            }
        }
        return false;
    }
    public boolean binarySearch(int[] a, int start, int end, int target){
        int left = start;
        int right = end;
        if(left>=0 && right<a.length && left<=right){
            int mid = (right+left)/2;
            if(a[mid]==target){
                return true;
            }
            else if(a[mid]<target){
                return binarySearch(a, mid+1, right, target);
            }
            else{
                return binarySearch(a, left, mid-1, target);
            }
        }
        return false;
    }
}

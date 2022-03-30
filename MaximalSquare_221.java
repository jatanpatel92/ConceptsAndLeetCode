/*
221. Maximal Square
Medium

Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.



Example 1:

Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4

Example 2:

Input: matrix = [["0","1"],["1","0"]]
Output: 1

Example 3:

Input: matrix = [["0"]]
Output: 0

*/
//RECURSIVE SOLUTION:

class Solution {
    Integer[][] mat;
    public int maximalSquare(char[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;
        int res=Integer.MIN_VALUE;
        mat=new Integer[n][m];

        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(matrix[i][j]=='1')
                    res=Math.max(res,solve(matrix,i,j));
            }
        }
        return res*res;
    }
    int solve(char[][] matrix,int i,int j)
    {
        if(i<0 || i>=matrix.length||j<0||j>=matrix[0].length||matrix[i][j]=='0')
            return 0;
        if(mat[i][j]!=null)
            return mat[i][j];
        return Math.min(solve(matrix,i,j+1),Math.min(solve(matrix,i+1,j),solve(matrix,i+1,j+1)))+1;
    }
}
//Although the abve solution is correct but it give TLE in some test cases because the complexity of a recursive solution is near about O(2^N).

// DP SOLUTION:
class Solution {
    public int maximalSquare(char[][] matrix) {
        int n=matrix.length;
        int m=matrix[0].length;
        int res=Integer.MIN_VALUE;
        int dp[][]=new int[n+1][m+1];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<m;j++)
            {
                if(matrix[i][j]=='1')
                {
                    dp[i+1][j+1]=Math.min(dp[i][j],Math.min(dp[i+1][j],dp[i][j+1]))+1;
                    res=Math.max(res,dp[i+1][j+1]);
                }
            }
        }
        return res*res;
    }
}

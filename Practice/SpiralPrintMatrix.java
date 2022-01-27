public class SpiralPrintMatrix {

	public static void main(String[] args) {
			int[][] matrix1 = new int[][]
      {
          { 10, 20, 30, 40 },
          { 15, 25, 35, 45 },
          { 27, 29, 37, 48 },
          { 32, 33, 39, 50 }
      };
			int[][] matrix2 = new int[][]
      {
          { 1, 2, 3, 4 },
          { 5, 6, 7, 8 },
          { 9, 10, 11, 12 },
          { 13, 14, 15, 16 }
      };
      int[][] matrix3 = new int[][]
      {
          { 1, 2, 3},
          { 4, 5, 6 },
          { 7, 8, 9 }
      };
      int[][] matrix4 = new int[][]
      {
          { 1, 2, 3, 4, 0 },
          { 5, 6, 7, 8, 0 },
          { 9, 10, 11, 12, 0 },
          { 13, 14, 15, 16, 0 }
      };
      int[][] matrix5 = new int[][]
      {
          { 1, 2, 3, 4, 0, 100 },
          { 5, 6, 7, 8, 0, 100 },
          { 9, 10, 11, 12, 0, 100 }
      };
      spiralPrintMatrix(matrix1);
      System.out.println();
      spiralPrintMatrix(matrix2);
      System.out.println();
      spiralPrintMatrix(matrix3);
      System.out.println();
      spiralPrintMatrix(matrix4);
      System.out.println();
      spiralPrintMatrix(matrix5);
	}
	public static void spiralPrintMatrix(int[][] arr) {
		 int m = arr.length;
		 int n = arr[0].length;
		 int row = 0;
		 int col = 0;
		 while(row<m && col<n) {
			 for(int c=col;c<n; c++) {
				 System.out.print(arr[row][c]+"->");
			 }
			 row++;
			 for(int r=row;r<m;r++) {
				 System.out.print(arr[r][n-1]+"->");
			 }
			 n--;
			 if(row<m && col<n) {
				 for(int c=n-1;c>=col;c--) {
					 System.out.print(arr[m-1][c]+"->");
				 }
				 m--;
				 for(int r=m-1; r>=row; r--) {
					 System.out.print(arr[r][col]+"->");
				 }
				 col++;
		 	}
		 }
	}
}

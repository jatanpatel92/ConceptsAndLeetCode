public class SankePrintMatrix {
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
          snakePrintMatrix(matrix1);
          System.out.println();
          snakePrintMatrix(matrix2);
          System.out.println();
          snakePrintMatrix(matrix3);
          System.out.println();
          snakePrintMatrix(matrix4);
          System.out.println();
          snakePrintMatrix(matrix5);
    }
	public static void snakePrintMatrix(int[][] arr) {
		int row = arr.length;
		int col = arr[0].length;
		int i=0, j=0;
        while(i<row){
            if(j==0){
              for(int c=0; c<col; c++){
                System.out.print(arr[i][c]+"->");
              }
              j = col;
            }
            else if(j==col){
               for(int c=col-1; c>=0; c--){
                System.out.print(arr[i][c]+"->");
              }
              j = 0;
            }
            i++;
        }
	}

}
/*
 * OutPut
10->20->30->40->45->35->25->15->27->29->37->48->50->39->33->32->
1->2->3->4->8->7->6->5->9->10->11->12->16->15->14->13->
1->2->3->6->5->4->7->8->9->
1->2->3->4->0->0->8->7->6->5->9->10->11->12->0->0->16->15->14->13->
1->2->3->4->0->100->100->0->8->7->6->5->9->10->11->12->0->100->
*/
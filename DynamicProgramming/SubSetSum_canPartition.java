class SubsetSum {

  public boolean canPartition(int[] num, int sum) {
    return canPartitionRecursive(num, 0, sum);
  }
  private boolean canPartitionRecursive(int[] num, int index, int sum){
    //System.out.println("Index: "+index+" Sum: "+sum);
    if(index>=num.length) return false;
    if(sum == 0)
      return true;
    if(canPartitionRecursive(num, index+1, sum))
      return true;
    return num[index]<=sum && canPartitionRecursive(num, index+1, sum-num[index]);
  }

  public static void main(String[] args) {
    SubsetSum ss = new SubsetSum();
    int[] num = { 1, 2, 3, 7 };
    System.out.println(ss.canPartition(num, 6));
    num = new int[] { 1, 2, 7, 1, 5 };
    System.out.println(ss.canPartition(num, 10));
    num = new int[] { 1, 3, 4, 8 };
    System.out.println(ss.canPartition(num, 6));
  }
}

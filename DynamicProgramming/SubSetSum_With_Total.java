// With Recursion
// Given an array the element can construct the result or the result can be constructed without the element
// With static global count
class SubsetSum {
  static int count = 0;
  static int countSubsets(int[] num, int sum) {
    //Set<Set<Integer>> indexSets = new HashSet<>();
    count = 0;
    int index = 0;
    int currentSum = 0;
    countSubSetsRecursive(num, index, currentSum, sum);
    return count;
  }
  static void countSubSetsRecursive(int[] nums, int i, int sumSoFar, int sum){
    if(i>=nums.length) return;
    //System.out.println("index: "+i+ " Element: "+nums[i]+" sumSoFar: "+sumSoFar);
    int updatedSum = sumSoFar + nums[i];
    if(updatedSum == sum){
      count++;
    }
    //System.out.println("count: "+count);
    if(updatedSum<sum)
      countSubSetsRecursive(nums, i+1, updatedSum, sum);
    countSubSetsRecursive(nums, i+1, sumSoFar, sum);
  }
  public static void main(String[] args) {
    SubsetSum ss = new SubsetSum();
    int[] num = { 1, 1, 2, 3 };
    System.out.println(ss.countSubsets(num, 4));
    num = new int[] { 1, 2, 7, 1, 5 };
    System.out.println(ss.countSubsets(num, 9));
  }
}

// with local count so Memo can be added later
class SubsetSum {
  static int countSubsets(int[] num, int sum) {
    //Set<Set<Integer>> indexSets = new HashSet<>();
    int index = 0;
    int currentSum = 0;
    return countSubSetsRecursive(num, index, currentSum, sum);
  }
  static int countSubSetsRecursive(int[] nums, int i, int sumSoFar, int sum){
    if(i>=nums.length) return 0;
    //System.out.println("index: "+i+ " Element: "+nums[i]+" sumSoFar: "+sumSoFar);
    int updatedSum = sumSoFar + nums[i];
    if(updatedSum == sum){
      return 1;
    }
    //System.out.println("count: "+count);
    int countWith = 0, countWithOut = 0;
    if(updatedSum<sum)
      countWith = countSubSetsRecursive(nums, i+1, updatedSum, sum);
    countWithOut = countSubSetsRecursive(nums, i+1, sumSoFar, sum);
    return countWith+countWithOut;
  }
  public static void main(String[] args) {
    SubsetSum ss = new SubsetSum();
    int[] num = { 1, 1, 2, 3 };
    System.out.println(ss.countSubsets(num, 4));
    num = new int[] { 1, 2, 7, 1, 5 };
    System.out.println(ss.countSubsets(num, 9));
  }
}

// with Memo
class SubsetSum {
  static Integer[][] dp;
  static int countSubsets(int[] num, int sum) {
    //Set<Set<Integer>> indexSets = new HashSet<>();
    dp = new Integer[num.length][sum+1];
    int index = 0;
    int currentSum = 0;
    return countSubSetsRecursive(num, index, currentSum, sum);
  }
  static int countSubSetsRecursive(int[] nums, int i, int sumSoFar, int sum){
    if(i>=nums.length) return 0;
    //System.out.println("index: "+i+ " Element: "+nums[i]+" sumSoFar: "+sumSoFar);
    if(dp[i][sumSoFar]!=null){
      return dp[i][sumSoFar];
    }
    int updatedSum = sumSoFar + nums[i];
    if(updatedSum == sum){
      return 1;
    }
    //System.out.println("count: "+count);
    int countWith = 0, countWithOut = 0;
    if(updatedSum<sum)
      countWith = countSubSetsRecursive(nums, i+1, updatedSum, sum);
    countWithOut = countSubSetsRecursive(nums, i+1, sumSoFar, sum);
    dp[i][sumSoFar] = countWith+countWithOut;
    return dp[i][sumSoFar];
  }
  public static void main(String[] args) {
    SubsetSum ss = new SubsetSum();
    int[] num = { 1, 1, 2, 3 };
    System.out.println(ss.countSubsets(num, 4));
    num = new int[] { 1, 2, 7, 1, 5 };
    System.out.println(ss.countSubsets(num, 9));
  }
}

// Bottom - Up
// subtract the number from Sum
// Base case Count(i, 0) = 1, Count(0, s) = 1 if(num[0] == s)
// Count(i, sum) = Count(i-1, sum) + Count(i-1, sum-num[i])
class SubsetSum {
  static int countSubsets(int[] num, int sum) {
   int[][] dp = new int[num.length][sum+1];
   // populate the sum=0 columns, as we will always have an empty set for zero sum
   for(int i=0; i<dp.length; i++){
     dp[i][0] = 1;
   }
   // with only one number, we can form a subset only when the required sum is equal to its value
   for(int s=1; s <= sum ; s++) {
      dp[0][s] = (num[0] == s ? 1 : 0);
  }
  // process all subsets for all sums
   for(int i=1; i<dp.length; i++){
     for(int j=1; j<sum+1; j++){
       dp[i][j] = dp[i-1][j];
       if(j>=num[i])
        dp[i][j] += dp[i-1][j-num[i]];
     }
   }
   return dp[num.length-1][sum];
  }

  public static void main(String[] args) {
    SubsetSum ss = new SubsetSum();
    int[] num = { 1, 1, 2, 3 };
    System.out.println(ss.countSubsets(num, 4));
    num = new int[] { 1, 2, 7, 1, 5 };
    System.out.println(ss.countSubsets(num, 9));
  }
}

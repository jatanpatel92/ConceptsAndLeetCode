/*
Target Sum (hard)#

You are given a set of positive numbers and a target sum ‘S’. Each number should be assigned either a ‘+’ or ‘-’ sign. We need to find the total ways to assign symbols to make the sum of the numbers equal to the target ‘S’.
Example 1:#

Input: {1, 1, 2, 3}, S=1
Output: 3
Explanation: The given set has '3' ways to make a sum of '1': {+1-1-2+3} & {-1+1-2+3} & {+1+1+2-3}

Example 2:#

Input: {1, 2, 7, 1}, S=9
Output: 2
Explanation: The given set has '2' ways to make a sum of '9': {+1+2+7-1} & {-1+2+7+1}
*/

// Recursion
class TargetSum {
  static int ways;
  public int findTargetSubsets(int[] num, int s) {
    ways = 0;
    recursion(num, s, 0, 0);
    return ways;
  }
  public void recursion(int[] num, int sum, int index, int sumSoFar){
    if(index==num.length){
      if(sumSoFar == sum) ways++;
      return;
    }
    int sameSignSum = num[index] + sumSoFar;
    int signSum = (-1 * num[index]) + sumSoFar;
    recursion(num, sum, index+1, sameSignSum);
    recursion(num, sum, index+1, signSum);
  }

  public static void main(String[] args) {
    TargetSum ts = new TargetSum();
    int[] num = {1, 1, 2, 3};
    System.out.println(ts.findTargetSubsets(num, 1));
    num = new int[]{1, 2, 7, 1};
    System.out.println(ts.findTargetSubsets(num, 9));
  }
}

// Recursion with ways recursive
class TargetSum {
  public int findTargetSubsets(int[] num, int s) {
    return findWaysRecursive(num, 0, s, 0);
  }
  public int findWaysRecursive(int[] num, int index, int s, int sumSoFar){
    if(index == num.length){
      if(sumSoFar == s) return 1;
      return 0;
    }
    return findWaysRecursive(num, index+1, s, sumSoFar+num[index]) +
            findWaysRecursive(num, index+1, s, sumSoFar+(-1*num[index]));
  }

  public static void main(String[] args) {
    TargetSum ts = new TargetSum();
    int[] num = {1, 1, 2, 3};
    System.out.println(ts.findTargetSubsets(num, 1));
    num = new int[]{1, 2, 7, 1};
    System.out.println(ts.findTargetSubsets(num, 9));
  }
}

// Recursion with Memoization
import java.util.*;
class TargetSum {
  class Pair{
    int i;
    int j;
    int hashCd;
    public Pair(int x, int y){
      i = x;
      j = y;
      hashCd = Objects.hash(i, j);
    }
    @Override
    public int hashCode(){
      return hashCd;
    }
    @Override
    public boolean equals(Object p){
      return p == this;
    }
  }
  static Map<Pair, Integer> memo;
  public int findTargetSubsets(int[] num, int s) {
    memo = new HashMap<>();
    return findWaysRecursive(num, 0, s, 0);
  }
  public int findWaysRecursive(int[] num, int index, int s, int sumSoFar){
    if(index == num.length){
      if(sumSoFar == s) return 1;
      return 0;
    }
    Pair key = new Pair(index, sumSoFar);
    if(memo.containsKey(key))
      return memo.get(key);
    memo.put(key, findWaysRecursive(num, index+1, s, sumSoFar+num[index]) +
            findWaysRecursive(num, index+1, s, sumSoFar+(-1*num[index])));
    return memo.get(key);
  }

  public static void main(String[] args) {
    TargetSum ts = new TargetSum();
    int[] num = {1, 1, 2, 3};
    System.out.println(ts.findTargetSubsets(num, 1));
    num = new int[]{1, 2, 7, 1};
    System.out.println(ts.findTargetSubsets(num, 9));
  }
}

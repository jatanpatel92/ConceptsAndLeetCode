/*
487. Max Consecutive Ones II
Medium

Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.



Example 1:

Input: nums = [1,0,1,1,0]
Output: 4
Explanation: Flip the first zero will get the maximum number of consecutive 1s. After flipping, the maximum number of consecutive 1s is 4.

Example 2:

Input: nums = [1,0,1,1,0,1]
Output: 4



Constraints:

    1 <= nums.length <= 105
    nums[i] is either 0 or 1.

*/


class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int k = 1;
        int left = 0;
        int right = 0;
        int max = 0;
        int countOnes = 0;
        while(right < nums.length){
            countOnes+=nums[right];
            if(right-left+1-countOnes>k){
                countOnes -= nums[left];
                left++;
            }
            right++;
            max = Math.max(max, right-left);
        }
        return max;
    }
}

/*
442. Find All Duplicates in an Array
Medium

Given an integer array nums of length n where all the integers of nums are in the range [1, n] and each integer appears once or twice, return an array of all the integers that appears twice.

You must write an algorithm that runs in O(n) time and uses only constant extra space.



Example 1:

Input: nums = [4,3,2,7,8,2,3,1]
Output: [2,3]

Example 2:

Input: nums = [1,1,2]
Output: [1]

Example 3:

Input: nums = [1]
Output: []

*/
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        Set<Integer> result = new HashSet<>();
        int i = 0;
        while(i<nums.length){
            int index = nums[i]-1;
            int expectedNum = i+1;
            if(nums[i] != expectedNum){
                if(nums[i]!=nums[index]){
                    swap(nums, index, i); // loop until corret element is found at i
                }
                else{
                    result.add(nums[i]); // found duplicate
                    i++;
                }
            }
            else{
                i++;
            }
        }
        return new LinkedList<>(result);
    }
    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

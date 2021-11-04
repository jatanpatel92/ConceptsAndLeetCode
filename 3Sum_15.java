/*
15. 3Sum
Medium

Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.

Notice that the solution set must not contain duplicate triplets.



Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]

Example 2:

Input: nums = []
Output: []

Example 3:

Input: nums = [0]
Output: []



Constraints:

    0 <= nums.length <= 3000
    -105 <= nums[i] <= 105

Accepted
1,535,719
Submissions
5,190,699
*/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        if(nums.length < 3) return new ArrayList(result);
        int target = 0;
        Map<Integer, Integer> indexTwoSumMap = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            int twoSum = target - nums[i];
            if(!indexTwoSumMap.containsKey(twoSum))
                indexTwoSumMap.put(twoSum, i);
        }
        for(Map.Entry<Integer, Integer> entry : indexTwoSumMap.entrySet()){
            int index = entry.getValue();
            int twoSum = entry.getKey();
            Map<Integer, Integer> map = new HashMap<>();
            for(int i=0; i<nums.length; i++){
                if(i!=index){
                    if(!map.containsKey(nums[i])){
                        map.put(twoSum-nums[i], i);
                    }
                    else if(map.containsKey(nums[i]) && map.get(nums[i]) != index){
                        List<Integer> triplets = new ArrayList<>();
                        triplets.add(nums[index]);
                        triplets.add(nums[map.get(nums[i])]);
                        triplets.add(nums[i]);
                        Collections.sort(triplets);
                        if(!result.contains(triplets))
                            result.add(triplets);
                    }
                }
            }
        }
        return new ArrayList(result);
    }
}

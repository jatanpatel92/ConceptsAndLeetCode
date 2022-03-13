/*
340. Longest Substring with At Most K Distinct Characters
Medium

Given a string s and an integer k, return the length of the longest substring of s that contains at most k distinct characters.



Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: The substring is "ece" with length 3.

Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: The substring is "aa" with length 2.



Constraints:

    1 <= s.length <= 5 * 104
    0 <= k <= 50


*/
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k==0) return 0;
    		int left = 0;
    		int right = 0;
    		int n = s.length();
    		int result = 0;
    		Map<Character, Integer> lastIndexOfChar = new HashMap<>();
    		while(right<n) {
    			char c = s.charAt(right);
    			if(lastIndexOfChar.size()<k || (lastIndexOfChar.size()==k && lastIndexOfChar.containsKey(c))) {
    				lastIndexOfChar.put(c, right);
    				right++;
    				result = Math.max(result, right-left);
    			}
    			else {
    				char removeChar = getMinIndexChar(lastIndexOfChar);
    				left = lastIndexOfChar.remove(removeChar);
    				left++;
    			}
    		}
    		return result;
      }
      private static char getMinIndexChar(Map<Character, Integer> map) {
    		int minIndex = Integer.MAX_VALUE;
    		char minIndexChar = ' ';
    		for(Character c : map.keySet()) {
    			if(minIndex>map.get(c)) {
    				minIndex = map.get(c);
    				minIndexChar = c;
    			}
    		}
    		return minIndexChar;
  	}
}

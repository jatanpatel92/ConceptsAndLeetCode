/*
3. Longest Substring Without Repeating Characters
Medium

17907

830

Add to List

Share
Given a string s, find the length of the longest substring without repeating characters.



Example 1:

Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
Example 4:

Input: s = ""
Output: 0


Constraints:

0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.

*/


class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;
        int left = 0;
        int right = 0;
        String longestNonRepeatingSubString = s.substring(left, right+1);
        Set<Character> set = new HashSet<>();
        while(right<s.length()){
            char c = s.charAt(right);
            if(!set.contains(c)){
                set.add(c);
                right++;
                if(right-left>longestNonRepeatingSubString.length())
                    longestNonRepeatingSubString = s.substring(left, right);
            }
            else{
                set.remove(s.charAt(left));
                left++;
            }
        }
        return longestNonRepeatingSubString.length();
    }
}

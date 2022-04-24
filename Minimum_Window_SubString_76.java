/*
76. Minimum Window Substring
Hard

Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".

The testcases will be generated such that the answer is unique.

A substring is a contiguous sequence of characters within the string.



Example 1:

Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:

Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.

Example 3:

Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.



Constraints:

    m == s.length
    n == t.length
    1 <= m, n <= 105
    s and t consist of uppercase and lowercase English letters.

*/
class Solution {
    public String minWindow(String s, String t) {
        if(s == null || t == null || s.isEmpty() || t.isEmpty() || s.length() < t.length()) return "";
        Map<Character, Integer> patternMap = new HashMap<>();
        Map<Character, Integer> freqMap = new HashMap<>();
        for(Character key:t.toCharArray()){
            patternMap.put(key, patternMap.getOrDefault(key, 0)+1);
        }
        int requiredMatches = patternMap.keySet().size();
        int matched = 0;
        int right = 0;
        int left = 0;
        int n = s.length();
        String result = "";
        while(right<n){
            char rightChar = s.charAt(right);
            freqMap.put(rightChar, freqMap.getOrDefault(rightChar, 0)+1);
            if(patternMap.containsKey(rightChar) && patternMap.get(rightChar).compareTo(freqMap.get(rightChar))==0)
                matched++;
            while(left<=right && matched == requiredMatches){
                if(result == "" || result.length()>(right-left+1)){
                    result = s.substring(left, right+1);
                }
                char leftChar = s.charAt(left);
                freqMap.put(leftChar, freqMap.get(leftChar)-1);
                if(patternMap.containsKey(leftChar) && patternMap.get(leftChar).compareTo(freqMap.get(leftChar))==1)
                    matched--;
                if(freqMap.get(leftChar) == 0){
                    freqMap.remove(leftChar);
                }
                left++;
            }
            right++;
        }
        return result;
    }
}

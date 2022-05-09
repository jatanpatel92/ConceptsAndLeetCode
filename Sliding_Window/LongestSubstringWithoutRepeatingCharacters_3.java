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
/*
With Frequency Map
import java.util.*;

class NoRepeatSubstring {
  public static int findLength(String str) {
    int left = 0;
	int right = 0;
	int maxLength = 0;
	Map<Character, Integer> freqMap = new HashMap<>();
	for(right = 0; right<str.length(); right++){
		char key = str.charAt(right);
		freqMap.put(key, freqMap.getOrDefault(key, 0)+1);
		while(freqMap.get(key)>1){
			char leftChar = str.charAt(left);
			freqMap.put(leftChar, freqMap.get(leftChar)-1);
			if(freqMap.get(leftChar)==0){
				freqMap.remove(leftChar);
			}
			left++;
		}
		maxLength = Math.max(maxLength, right-left+1);
	}
	return maxLength;
  }
}

*/
/*
With Index Map
import java.util.*;

class NoRepeatSubstring {
  public static int findLength(String str) {
    int left = 0;
	int right = 0;
	int maxLength = 0;
	Map<Character, Integer> indexMap = new HashMap<>();
	for(right = 0; right<str.length(); right++){
		char key = str.charAt(right);
		if(indexMap.containsKey(key)){
			left = indexMap.get(key);
			left++;
		}
		indexMap.put(key, right);
		maxLength = Math.max(maxLength, right-left+1);
	}
	return maxLength;
  }
}

*/

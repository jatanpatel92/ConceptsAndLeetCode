// Given a string s, find the length of the longest substring without repeating characters.

// Example 1:
// Input: s = "abcabcbb"
// Output: 3
// Explanation: The answer is "abc", with the length of 3.

// Example 2:
// Input: s = "bbbbb"
// Output: 1
// Explanation: The answer is "b", with the length of 1.

// Example 3:
// Input: s = "pwwkew"
// Output: 3
// Explanation: The answer is "wke", with the length of 3.
// Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
/*
 * 				pwwkew
 * 			l    3
 * 			r	   3
 * 		set: {w, k, e }
 * 	  result: 2
 * /
class Solution {
    public int lengthOfLongestSubstring(String s) {
        /*Hello World!*/
      	int left = 0;
      	int right = 0;
      	int n = s.length();
      	int result = Integer.MIN_VALUE;
      	Set<Character> set = new HashSet<>();
      	while(right<n){
          	char c = s.charAt(right);
          	if(!set.contains(c)){
              	set.add(c);
              	right++;
              	if(right-left>result){
                  result = right-left;
                }
            }
            else{
              /*
               while(s.charAt(left)!=c){
                 left++;
               }
               */
               set.remove(s.charAt(left));
               left++;
            }
        }
       return result;
    }
}

// Followup:
// Given a string s, find the length of the longest substring t that contains at most 2 distinct characters.
// Example 1:
// Input: "eceba"
// Output: 3
// Explanation: t is "ece" which its length is 3.
// Example 2:
// Input: "ccaabbb"
// Output: 5
// Explanation: t is "aabbb" which its length is 5.
/*
 * 				ccaabbb
 * 			l   2
 * 			r        7
 * 	 list { a, b }
 * 	 result: 5
 * */
 class Solution {
     public int lengthOfLongestSubstringTwoDistinct(String s) {
         int left = 0;
       	int right = 0;
       	int n = s.length();
         int result = Integer.MIN_VALUE;
       	List<Character> list = new LinkedList<>();
       	while(right<n){
           	char c = s.charAt(right);
           	if(!list.contains(c) && list.size()<2){
               	list.add(c);
                 right++;
                 if(right-left>result){
                     result = right-left;
                 }
             }
           	else if(list.contains(c) && list.size()<=2){
               	right++;
                 if(right-left>result){
                     result = right-left;
                 }
             }
           	else if(!list.contains(c) && list.size()==2){
                char leftChar = s.charAt(left);
                left++;
                if(s.substring(left, right).indexOf(leftChar)==-1)
                 list.remove(list.indexOf(leftChar));
             }

         }
      	return result;
     }
 }

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
          int result = Integer.MIN_VALUE;
        	List<Character> list = new LinkedList<>();
        	while(right<n){
            	char c = s.charAt(right);
            	if(!list.contains(c) && list.size()<k){
                	list.add(c);
                  right++;
                  if(right-left>result){
                      result = right-left;
                  }
              }
            	else if(list.contains(c) && list.size()<=k){
                	right++;
                  if(right-left>result){
                      result = right-left;
                  }
              }
            	else if(!list.contains(c) && list.size()==k){
                 char leftChar = s.charAt(left);
                 left++;
                 if(s.substring(left, right).indexOf(leftChar)==-1)
                  list.remove(list.indexOf(leftChar));
              }

          }
       	return result;
     }
 }

/*
5. Longest Palindromic Substring
Medium

13632

805

Add to List

Share
Given a string s, return the longest palindromic substring in s.



Example 1:

Input: s = "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"
Example 3:

Input: s = "a"
Output: "a"
Example 4:

Input: s = "ac"
Output: "a"


Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.
*/
class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length()==1) return s;
        int left = 0;
        int right = s.length()-1;
        String resultLPS = s.substring(0, 1);
        for(int i = 0; i<s.length(); i++){
            for(int j = s.length()-1; j>=i; j--){
                if(s.charAt(i) == s.charAt(j) && (j-i+1)>resultLPS.length() && isPalindrome(s, i+1, j-1)){
                   resultLPS = s.substring(i, j+1);
                }
            }
        }
        return resultLPS;
    }
    static boolean isPalindrome(String s, int start, int end){
        if(start>=end) return true;
        while(start<end){
            if(s.charAt(start)!=s.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }
}

/*
30. Substring with Concatenation of All Words
Hard

You are given a string s and an array of strings words of the same length. Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any intervening characters.

You can return the answer in any order.



Example 1:

Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
The output order does not matter, returning [9,0] is fine too.

Example 2:

Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
Output: []

Example 3:

Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]

*/
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new LinkedList<>();
        int size = words.length;
        int len = words[0].length();
        int left = 0;
        int right = 0;
        int n = s.length();
        int k = size*len;
        Map<String, Integer> wordPattern = new HashMap<>();
        for(String word:words){
            wordPattern.put(word, wordPattern.getOrDefault(word, 0)+1);
        }
        while(right<n){
            if(right-left+1 == k){
                Map<String, Integer> wordCount = new HashMap<>();
                for(int i = left; i<Math.min(left+k, n); i+=len){
                    String key = s.substring(i, i+len);
                    if(wordPattern.containsKey(key))
                        wordCount.put(key, wordCount.getOrDefault(key, 0)+1);
                    else
                        break;
                }
                if(wordCount.equals(wordPattern))
                    result.add(left);
                left++;
            }
            right++;
        }
        return result;
    }
}

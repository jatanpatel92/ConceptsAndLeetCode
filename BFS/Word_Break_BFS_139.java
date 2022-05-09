/*
139. Word Break
Medium

10039

445

Add to List

Share
Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

Note that the same word in the dictionary may be reused multiple times in the segmentation.



Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false


Constraints:

1 <= s.length <= 300
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 20
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
*/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int start = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.offer(start);
        while(!queue.isEmpty()){
            System.out.println(queue);
            start = queue.poll();
            if(!visited[start]){
                for(int end = start+1; end<=s.length(); end++){
                    if(set.contains(s.substring(start, end))){
                        System.out.println(start+","+ end);
                        System.out.println(s.substring(start, end));
                        queue.offer(end);
                        if(end == s.length())
                            return true;
                    }
                }
            }
            visited[start] = true;
        }
        return false;
    }
}

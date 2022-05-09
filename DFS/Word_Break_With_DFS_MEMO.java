class Solution {
    static Map<Integer, Boolean> memo;
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        memo = new HashMap<>();
        return wordBreakDFS(s, 0, set);
    }
    private boolean wordBreakDFS(String s, int start, Set<String> set){
        if(start == s.length()){
            return true;
        }
        if(memo.containsKey(start)){
            return memo.get(start);
        }
        for(int end = start+1; end<=s.length(); end++){
            String key = s.substring(start, end);
            if(set.contains(key)){
                if(wordBreakDFS(s, end, set)){
                    memo.put(start, true);
                    return true;
                }
            }
        }
        memo.put(start, false);
        return false;
    }
}

class Solution {
    public String longestCommonPrefix(String[] strs) {
        Trie trie = new Trie();
        for(String word: strs){
            trie.insert(word);
        }
        return trie.getLongestCommonPrefix();
    }
    class Trie{
        TrieNode root;
        public Trie(){
            root = new TrieNode();
        }
        public void insert(String word){
            TrieNode current = root;
            for(char key:word.toCharArray()){
                if(!current.children.containsKey(key)){
                    current.children.put(key, new TrieNode());
                }
                current = current.children.get(key);
            }
            current.end = true;
        }
        public String getLongestCommonPrefix(){
            StringBuffer prefix = new StringBuffer();
            TrieNode current = root;
            while(!current.end && current.children.size()==1){
                char key = current.children.keySet().iterator().next();
                current = current.children.get(key);
                prefix.append(key);
            }
            return prefix.toString();
        }
    }
    class TrieNode{
        Map<Character, TrieNode> children;
        boolean end;
        public TrieNode(){
            children = new HashMap<>();
            end = false;
        }
    }
}

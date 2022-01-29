/*
211. Design Add and Search Words Data Structure
Medium

Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

    WordDictionary() Initializes the object.
    void addWord(word) Adds word to the data structure, it can be matched later.
    bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.



Example:

Input
["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
[[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
Output
[null,null,null,null,false,true,true,true]

Explanation
WordDictionary wordDictionary = new WordDictionary();
wordDictionary.addWord("bad");
wordDictionary.addWord("dad");
wordDictionary.addWord("mad");
wordDictionary.search("pad"); // return False
wordDictionary.search("bad"); // return True
wordDictionary.search(".ad"); // return True
wordDictionary.search("b.."); // return True



Constraints:

    1 <= word.length <= 500
    word in addWord consists lower-case English letters.
    word in search consist of  '.' or lower-case English letters.
    At most 50000 calls will be made to addWord and search.


*/
class WordDictionary {
    TrieNode head;
    public WordDictionary() {
        head = new TrieNode();
    }

    public void addWord(String word) {
        if(word == null || word.isEmpty()) return;
        TrieNode current = head;
        int len = word.length();
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(!current.hasChild(c)){
                current.putChild(c, new TrieNode());
            }
            current = current.getChild(c);
        }
        current.setEnd();
    }

    public boolean search(String word) {
        if(word == null || word.isEmpty()) return true;
        TrieNode current = head;
        return searchDFS(word, current);
    }

    private boolean searchDFS(String word, TrieNode current){
        for(int i=0; i<word.length(); i++){
            char c = word.charAt(i);
            if(!current.hasChild(c) && c == '.'){
                for(Character child: current.getChildren()){
                    if(searchDFS(word.substring(i+1), current.getChild(child))){
                        return true;
                    }
                }
                return false;
            }
            else if(!current.hasChild(c)){
                return false;
            }
            current = current.getChild(c);
        }
        return current.isEnd();
    }
}

class TrieNode{
    Map<Character, TrieNode> children;
    boolean end;
    public TrieNode(){
        children = new HashMap<>();
    }
    public boolean hasChild(char c){
        return children.containsKey(c);
    }
    public TrieNode getChild(char c){
        return hasChild(c)?children.get(c):null;
    }
    public void putChild(char c, TrieNode node){
        children.put(c, node);
    }
    public void setEnd(){
        end = true;
    }
    public boolean isEnd(){
        return end;
    }
    public Set<Character> getChildren(){
        return children.keySet();
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */

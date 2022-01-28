/*
208. Implement Trie (Prefix Tree)
Medium

A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

    Trie() Initializes the trie object.
    void insert(String word) Inserts the string word into the trie.
    boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
    boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.



Example 1:

Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True



Constraints:

    1 <= word.length, prefix.length <= 2000
    word and prefix consist only of lowercase English letters.
    At most 3 * 104 calls in total will be made to insert, search, and startsWith.


*/
class Trie {

   TrieNode head;

	public Trie() {
		head = new TrieNode();
	}

	public void insert(String word) {
		TrieNode current = head;
		for(int i=0; i<word.length(); i++) {
			char child = word.charAt(i);
			if(!current.hasChild(child)) {
				current.put(child, new TrieNode());
			}
			current = current.getChild(child);
		}
		current.setEnd();
	}

	public boolean search(String word) {
		TrieNode current = head;
		for(int i=0; i<word.length(); i++) {
			char c = word.charAt(i);
			if(!current.hasChild(c)) {
				return false;
			}
			current = current.getChild(c);
		}
		return current.isEnd();
	}

	public boolean startsWith(String prefix) {
		TrieNode current = head;
		for(int i = 0; i<prefix.length(); i++) {
			char c = prefix.charAt(i);
			if(!current.hasChild(c))
				return false;
			current = current.getChild(c);
		}
		return true;
	}
}

public class TrieNode {
	Map<Character, TrieNode> children;
	boolean end;
	public TrieNode() {
		children = new HashMap<>();
	}

	public boolean hasChild(char c) {
		return children.containsKey(c);
	}

	public TrieNode getChild(char c) {
		return hasChild(c)?children.get(c):null;
	}

	public void put(char c, TrieNode child) {
		children.put(c, child);
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd() {
		end = true;
	}
}
/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

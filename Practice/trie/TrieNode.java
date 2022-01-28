/**
 * DSA - TRIE NODE
 */
package dsa.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jatan
 *
 */
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
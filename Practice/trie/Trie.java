/**
 * DSA - TRIE
 */
package dsa.trie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @author jatan
 *
 */
public class Trie {
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
	
	public void printTrie() {
		TrieNode current = head;
		printTrieDFS(current, 0);
	}
	
	private void printTrieDFS(TrieNode current, int level) {
		//if(current.isLeafNode()) return;
		System.out.println();
		for(Character c:current.children.keySet()) {
			System.out.print(level+" "+ c + " " + current.getChild(c).isEnd());
			printTrieDFS(current.getChild(c), level+1);
		}
	}
	
	public void printTrieBFS() {
		TrieNode current = head;
		Queue<TrieNode> queue = new LinkedList<>();
		Map<TrieNode, TrieNode> parent = new HashMap<>();
		parent.put(current, null);
		queue.add(current);
		while(!queue.isEmpty()) {
			current = queue.poll();
			for(Character c: current.children.keySet()) {
				queue.offer(current.getChild(c));
				parent.put(current.getChild(c), current);
				System.out.println( c + " " + current.getChild(c).isEnd());
			}
		}
		
	}
}

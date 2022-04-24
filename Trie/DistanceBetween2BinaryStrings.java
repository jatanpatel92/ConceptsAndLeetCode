/*
 * The distance between 2 binary strings is the sum of their lengths after removing the common prefix. For example: the common prefix of 1011000 and 1011110 is 1011 so the distance is len("000") + len("110") = 3 + 3 = 6.

Given a list of binary strings, pick a pair that gives you maximum distance among all possible pair and return that distance.
 */
public class BinaryStringDistance {

	public static void main(String[] args) {
		BinaryTrie trie = new BinaryTrie();
		trie.add("1011100");
		trie.add("1011011");
		trie.add("1001111");
		System.out.println(trie.maxDistance());
		trie.add("1101111");
		System.out.println(trie.maxDistance());
	}
	static class BinaryTrie{
		BinaryTrieNode head;
		public BinaryTrie(){
			head = new BinaryTrieNode();
		}
		void add(String binary) {
			BinaryTrieNode current = head;
			for(char key : binary.toCharArray()) {
				int digit = key - '0';
				if(current.children[digit]==null) {
					current.children[digit] = new BinaryTrieNode();
				}
				current = current.children[digit];
			}
			current.end = true;
		}
		int maxDistance() {
			//assignDepthDFS(head);
			return maxDistanceDFS(head);
		}
		int maxDistanceDFS(BinaryTrieNode root) {
			if(root == null)
				return 0;
			if(root.children[0]!=null && root.children[1]!=null) {
				return maxDepthDFS(root.children[0]) + maxDepthDFS(root.children[1]);
			}
			if(root.children[0]!=null)
				return maxDistanceDFS(root.children[0]);
			return maxDistanceDFS(root.children[1]);
		}
		int maxDepthDFS(BinaryTrieNode root) {
			if(root == null)
				return 0;
			int max = 0;
			if(root.children[0]!=null && root.children[1]!=null) {
				max = 1 + Math.max(maxDepthDFS(root.children[0]), maxDepthDFS(root.children[1]));
			}
			else if(root.children[0]!=null)
				max = 1+ maxDepthDFS(root.children[0]);
			else
				max = 1 +maxDepthDFS(root.children[1]);
			root.maxDepth = max;
			return max;
		}
	}
	static class BinaryTrieNode{
		BinaryTrieNode[] children;
		int maxDepth;
		boolean end;
		public BinaryTrieNode() {
			children = new BinaryTrieNode[2];
			end = false;
		}
	}

}

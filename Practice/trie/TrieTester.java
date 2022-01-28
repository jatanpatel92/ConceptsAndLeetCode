package dsa.trie;

public class TrieTester {

	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.insert("dog");
		trie.insert("dogie");
		trie.insert("cat");
		trie.insert("car");
		trie.insert("cards");
		trie.insert("card");
		trie.insert("cot");
		trie.insert("cots");
		trie.insert("trie");
		trie.insert("try");
		trie.insert("tried");
		trie.insert("tries");
		trie.insert("triangle");
		trie.insert("triangles");
		trie.insert("ab");
		System.out.println("Print Trie DFS:");
		trie.printTrie();
		System.out.println("Print Trie BFS:");
		trie.printTrieBFS();
		System.out.println(trie.search("do"));
		System.out.println(trie.search("dog"));
		System.out.println(trie.search("dogies"));
		System.out.println(trie.startsWith("do"));
		System.out.println(trie.search("a"));
		System.out.println(trie.startsWith("a"));
		
	}

}

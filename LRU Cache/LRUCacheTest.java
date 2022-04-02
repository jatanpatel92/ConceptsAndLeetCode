package com.interview.question.implementations;

public class LRUCacheTest {

	public static void main(String[] args) {
		LRUCache lc = new LRUCache(4);
		lc.put(1, "jal");
		lc.put(2, "jatan");
		lc.put(3,"ghost");
		lc.put(4, "price");
		lc.get(1);
		lc.get(2);
		lc.put(5, "alex");
		lc.display();

	}

}
